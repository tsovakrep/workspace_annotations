package framework.webcore.bean;

import framework.webcore.util.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
	private HttpStatus httpStatus;
    private String errorMsg;
    private Object data;
}
