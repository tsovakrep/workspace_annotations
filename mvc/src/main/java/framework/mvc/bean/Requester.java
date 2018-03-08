package framework.mvc.bean;

import framework.mvc.util.RequestMethod;

import java.util.Arrays;

public class Requester {
    private String requestUrls;
    private RequestMethod[] requestMethods;

    public Requester(String requestUrls, RequestMethod[] requestMethods) {
        this.requestUrls = requestUrls;
        this.requestMethods = requestMethods;
    }

    public String getRequestUrls() {
        return requestUrls;
    }

    public void setRequestUrls(String requestUrls) {
        this.requestUrls = requestUrls;
    }

    public RequestMethod[] getRequestMethods() {
        return requestMethods;
    }

    public void setRequestMethods(RequestMethod[] requestMethods) {
        this.requestMethods = requestMethods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Requester requester = (Requester) o;

        if (requestUrls != null ? !requestUrls.equals(requester.requestUrls) : requester.requestUrls != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(requestMethods, requester.requestMethods);
    }

    @Override
    public int hashCode() {
        int result = requestUrls != null ? requestUrls.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(requestMethods);
        return result;
    }
}
