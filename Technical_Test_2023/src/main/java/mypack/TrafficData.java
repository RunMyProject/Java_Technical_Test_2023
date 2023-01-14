package mypack;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class TrafficData {
	private String ip_address;
	private Integer number_of_requests;
	private Float percentage_amount_requests;
	private Integer total_bytes_sent;
	private Float percentage_total_amount_bytes;
}
