/**
 * 
 */
package mypack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.Singular;

/**
 * @author edoardo
 *
 */
public class Main {

	@Singular("MyTrafficDatas")
	static private final List<TrafficData> trafficDatas = new ArrayList<>();
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		// Since Java 9
		//
		Runtime.Version version = Runtime.version();
		
		System.out.println("Current Java version: " + version);
		System.out.println("----------------------------------------------------------------");
				
		var filePath = System.getProperty("user.dir") + "/src/main/resources/reports/ipaddr.csv";
		
		System.out.println(filePath);
		System.out.println("----------------------------------------------------------------");

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

		trafficDatas.forEach((trafficdata) -> {
		      System.out.println(trafficdata);
		});
	}
}