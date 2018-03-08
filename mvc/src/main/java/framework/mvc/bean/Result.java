package framework.mvc.bean;

/**
 * @author tf
 * @create 2017-07-01 17:30
 **/
public class Result {
    private Integer code;
    private String errorMsg;
    private Object data;

    public Result(Integer code, String errorMsg, Object data) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
