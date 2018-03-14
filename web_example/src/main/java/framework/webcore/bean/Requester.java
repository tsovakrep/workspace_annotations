package framework.webcore.bean;

import framework.webcore.util.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Requester {
	
	private String requestUrls;
    private HttpMethod requestMethod;

}