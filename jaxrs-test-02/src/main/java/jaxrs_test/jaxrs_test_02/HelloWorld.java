package jaxrs_test.jaxrs_test_02;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorld {

    @GET
    @Path("/echo/{input}")
    @Produces("text/plain")
    public String ping(@PathParam("input") String input) {
    	System.out.println("HelloWorld.ping is called " + input);
        return input;
    }

    @GET
    @Path("/echoJson/{input}")
    @Produces("application/json")
    public JsonObject pingJson(@PathParam("input") String input) {
    	System.out.println("HelloWorld.pingJson is called " + input);
    	JsonObject jsonObject = Json.createObjectBuilder().add("x", "123").build();
        return jsonObject;
    }
    
    
    /**
     * 
     * @param input
     * @return
     */
    @GET
    @Path("/echoJsonBinding/{input}")
    @Produces("application/json")
    public Response pingJsonBinding(@PathParam("input") String input) throws MalformedURLException {
    	System.out.println("HelloWorld.pingJsonBinding is called " + input);
    	JsonBean jsonBean = new JsonBean();
    	jsonBean.setVal1("a");
    	jsonBean.setVal2("b");
    	jsonBean.setVal4( new ArrayList<String>( Arrays.asList("a","b","c") ));
    	jsonBean.setVal5( new URL("http://localhost"));
    	return Response.ok(jsonBean).build();
    }
    
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/jsonBean")
    public Response modifyJson(JsonBean input) {
        input.setVal2(input.getVal1());
        return Response.ok().entity(input).build();
    }
}

