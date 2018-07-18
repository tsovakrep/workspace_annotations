package framework.csscore;

import java.io.IOException;
import java.io.InputStream;

public interface HttpInputMessage extends HttpMessage {

	/**
	 * Return the body of the message as an input stream.
	 * @return the input stream body (never {@code null})
	 * @throws IOException in case of I/O Errors
	 */
	InputStream getBody() throws IOException;

}

