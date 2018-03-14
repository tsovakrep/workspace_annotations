package framework.webcore.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import framework.util.FrameworkConstant;

public class JSONUtil {

	private static final Gson gson = new Gson();

	public static String getJsonString(HttpServletRequest request) {
		InputStream body = null;
		StringBuilder buf = new StringBuilder(512);
		try {
			body = request.getInputStream();

			int b;
			while ((b = body.read()) != -1) {
				buf.append((char) b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
	
	public static void writeJSON(HttpServletRequest request, HttpServletResponse response, String result) {
        try {
            response.setCharacterEncoding(FrameworkConstant.ENCODING);
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(result);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	public static String toJSON(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T fromJSON(String json, Class<T> cls) {
		return gson.fromJson(json, cls);
	}
}
