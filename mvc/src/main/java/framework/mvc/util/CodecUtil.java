package framework.mvc.util;

import framework.FrameworkConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CodecUtil {

    public static String decodeUrl(String url) {
        String targetUrl = null;
        try {
            targetUrl = URLDecoder.decode(url, FrameworkConstant.ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("????????????", e);
        }
        return targetUrl;
    }

    public static String encodeUrl(String url) {
        String target = null;
        try {
            target = URLEncoder.encode(url, FrameworkConstant.ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("", e);
        }
        return target;
    }

}
