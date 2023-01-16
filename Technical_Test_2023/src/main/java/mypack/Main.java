/**
 * 
 */
package mypack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Singular;

/**
 * @author edoardo
 *
 */
public class Main {

	@Singular("MyTrafficDatas")
	static private final List<TrafficData> trafficDatas = new ArrayList<>();
	
	static private final String contentHeader = "TIMESTAMP;BYTES;STATUS;REMOTE_ADDR;\n";

	static boolean csv_json=false;

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		boolean debug=false;
		
		if(args.length>0) {			
			debug = args[0].compareToIgnoreCase("/d")==0;
			if(args.length==2) {
				csv_json=args[1].compareToIgnoreCase("/json")==0;
			}
		}

		System.out.println("DEBUG: " + debug);
		
		// Since Java 9
		Runtime.Version version = Runtime.version();
		
		if(debug) {
			System.out.println("Current Java version: " + version);
			System.out.println("----------------------------------------------------------------");
		}
		
		var filePath = System.getProperty("user.dir") + "/src/main/resources/reports/ipaddr.csv";
		
		if(debug) {
			System.out.println("Path to read: " + filePath);
			System.out.println("----------------------------------------------------------------");
		}
		
		var fileEntries = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		    String line;
		    while ((line = br.readLine()) != null)
		        fileEntries.add(line);
		}

		fileEntries.remove(0); // remove the csv header entry
		
		Files.lines(Paths.get(filePath))
        .skip(0) // ignore the first entry
        .filter(line -> line.startsWith("1"))
        .forEach ((line) -> {
		    var values = line.split(";");
			trafficDatas.add(TrafficData.builder()
					.ip_address(values[0])
					.number_of_requests(Integer.parseInt(values[1]))
					.percentage_amount_requests(Float.parseFloat(values[2]))
					.total_bytes_sent(Integer.parseInt(values[3]))
					.percentage_total_amount_bytes(Float.parseFloat(values[4]))
					.build());
        });

		
		if(debug) {
			trafficDatas.forEach((trafficdata) -> {
			      System.out.println(trafficdata);
			});
			System.out.println("----------------------------------------------------------------");
		}
		
		trafficDatas.sort(Comparator.comparing(TrafficData::getNumber_of_requests).reversed());

		if(debug) {
			trafficDatas.forEach((trafficdata) -> {
			      System.out.println(trafficdata);
			});
		}
		
		var filePathToWrite = System.getProperty("user.dir") + "/src/main/resources/logfiles/requests.log"+(csv_json ? ".json" : "");

		if(debug) {
			System.out.println("----------------------------------------------------------------");
			System.out.println("Path to write: " + filePathToWrite);
		}

		Path pathToWrite = Path.of(filePathToWrite);

		try(RandomAccessFile stream = new RandomAccessFile(pathToWrite.toFile(),"rw"); FileChannel channel = stream.getChannel();) {
		  
	        if(!csv_json) {
				byte[] strBytes = contentHeader.getBytes();
				ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
	
				buffer.put(strBytes);
				buffer.flip();
				channel.write(buffer);
	        } else {
				byte[] strBytes = "[\n".getBytes();
				ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
	
				buffer.put(strBytes);
				buffer.flip();
				channel.write(buffer);
	        }
			/* 
			 * NOTE:
			 * 
			 * **************************************************************************************
			 * String is immutable whereas StringBuffer and StringBuilder are mutable classes. 
			 * StringBuffer is thread-safe and synchronized whereas StringBuilder is not. 
			 * That's why StringBuilder is faster than StringBuffer. 
			 * String concatenation operator (+) internally uses StringBuffer or StringBuilder class. 	
			 * **************************************************************************************
			 */			
			
			StringBuilder stringBuilder = new StringBuilder();

			trafficDatas.forEach((trafficdata) -> {
				
		        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		        if(!csv_json) {
			        stringBuilder.append(timestamp);
			        stringBuilder.append(";");
			        stringBuilder.append(trafficdata.getTotal_bytes_sent());
			        stringBuilder.append(";OK;");
			        stringBuilder.append(trafficdata.getIp_address());		        
			        stringBuilder.append(";");
			        stringBuilder.append(System.getProperty("line.separator"));
		        } else {
			        stringBuilder.append("\t{\n\t\ttimestamp: \"");
			        stringBuilder.append(timestamp);
			        stringBuilder.append("\",\n\t\tbytes: ");
			        stringBuilder.append(trafficdata.getTotal_bytes_sent());
			        stringBuilder.append(",\n\t\tstatus: \"OK\",\n\t\tremote_addr: \"");
			        stringBuilder.append(trafficdata.getIp_address());		        
			        stringBuilder.append("\"\n\t},\n");
		        }
		        
				byte[] rowBytes = stringBuilder.toString().getBytes();
				ByteBuffer byteBuffer = ByteBuffer.allocate(rowBytes.length);

				byteBuffer.put(rowBytes);
				byteBuffer.flip();			
				try {
					channel.write(byteBuffer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				stringBuilder.setLength(0);
			});

	        if(csv_json) {
				byte[] strBytes = "]\n".getBytes();
				ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
	
				buffer.put(strBytes);
				buffer.flip();
				channel.write(buffer);
	        }

		}

		if(debug) {
			System.out.println("----------------------------------------------------------------");
		}
		
		System.out.println("done.");
	}
}