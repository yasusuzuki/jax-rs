
import java.time.LocalDateTime;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Dependent
@Path("/gas")
public class GASSimulator {
	@Inject
	private Comments comments;

    @GET
    @Path("/loadMySpreadSheet")
    @Produces("application/json")
    public JsonObject loadMySpreadSheet() {
    	
    	System.out.println(LocalDateTime.now() + " loadMySpreadSheet is called " + comments);
    	StringBuffer buff = new StringBuffer();
    	JsonObject jsonObject = Json.createObjectBuilder()
    			.add("title", "これはタイトル")
    			.add("summary", "これはサマリ")
    			.add("comments", comments.toString()).build();    	
        return jsonObject;
    }
    
    @POST
    @Path("/updateMySpreadSheet")
    @Produces("application/json")
    public JsonObject updateMySpreadSheet(@FormParam("input") String input) {
    	JsonObject jsonObject = Json.createObjectBuilder()
    			.add("status", "success").build();
    	comments.add(input);
    	System.out.println(LocalDateTime.now() + " updateMySpreadSheet is called " + comments);

        return jsonObject;
    }
    
    @GET
    @Path("/processFileUpload/{input}")
    @Produces("application/json")
    public JsonObject processFileUpload(@PathParam("input") String input) {
    	System.out.println(LocalDateTime.now() + " processFileUpload is called " + input);
    	JsonObject jsonObject = Json.createObjectBuilder()
    			.add("title", "これはタイトル")
    			.add("summary", "これはサマリ")
    			.add("comments", "こめんと１").build();
        return jsonObject;
    }
}

