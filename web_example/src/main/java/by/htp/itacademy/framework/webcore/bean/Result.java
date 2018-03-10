package by.htp.itacademy.framework.webcore.bean;

import by.htp.itacademy.framework.webcore.util.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
	private HttpStatus httpStatus;
    private String errorMsg;
    private Object data;
}
