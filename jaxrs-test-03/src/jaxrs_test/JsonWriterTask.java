package jaxrs_test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
/**
 * 戻り値がTask、もしくはreturn Response.ok(Task t).build();としたときに
 * このインターセプタがよばれる
 * @author yasu
 *
 */
@Provider
public class JsonWriterTask implements MessageBodyWriter<Task> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(Task t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(Task t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
		PrintWriter pw = new PrintWriter(entityStream);
		pw.println("YAHOO!!" + mediaType);
		pw.flush();
		System.out.println("aa");
		
	}

}
