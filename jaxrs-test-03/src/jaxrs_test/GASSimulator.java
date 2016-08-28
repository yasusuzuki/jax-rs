package jaxrs_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringTokenizer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.wink.common.model.multipart.BufferedInMultiPart;
import org.apache.wink.common.model.multipart.InPart;

/* JAX-RSのリソースクラスのデフォルトスコープは@RequestScopedに近いが、
 * 明示的にCDIの@RequestScopedを指定しないCDI管理下になってくれない
 * @Injectが機能するためには、CDI管理下にする必要がある。
*/
@RequestScoped  
@Path("/gas")
public class GASSimulator {
	@Inject
	private TaskRepository task;

    @GET
    @Path("/loadMySpreadSheet")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadMySpreadSheet() {
    	System.out.println(LocalDateTime.now() + " loadMySpreadSheet is called ");
    	return Response.ok(task.getTask()).build();
    }
    
    @POST
    @Path("/updateMySpreadSheet")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMySpreadSheet(@FormParam("input") String input) {
    	task.getTask().addComment(input);
    	System.out.println(LocalDateTime.now() + " updateMySpreadSheet is called " + input);
        return Response.ok().build();
    }
    
    @POST
    @Path("/processFileUpload")     
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response processFileUpload(@Context ServletContext context, BufferedInMultiPart bimp) throws Exception {
    	String filename = "dummy";
		try {
			for (InPart part : bimp.getParts()) {
				MultivaluedMap<String, String> headers = part.getHeaders();
				List<String> list = headers.get("Content-Disposition");
				for (String value : list) {
					System.out.println("Key : " + value);
					String[] tokenlist = value.split(";\\s*");
					for(String token : tokenlist ) {
						System.out.println("next " + token );
						if ( token.startsWith("filename=")){
							int beginIndex = token.indexOf("\"");
							int endIndex = token.lastIndexOf("\"");
							filename = token.substring(beginIndex+1, endIndex);
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		InPart inPart = bimp.getParts().get(0);
		InputStream ins = inPart.getInputStream();
    	
		String filePath = context.getRealPath("/")+ "\\" + filename;
		try (FileOutputStream out = new FileOutputStream(filePath); ){
			int read=0;
			byte[] bytes = new byte[1024];
			while((read = ins.read(bytes))!= -1){
				out.write(bytes, 0, read);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
				
		System.out.println(LocalDateTime.now() + " processFileUpload is called " + filePath );
        return Response.ok().build();    
    }  
} 

