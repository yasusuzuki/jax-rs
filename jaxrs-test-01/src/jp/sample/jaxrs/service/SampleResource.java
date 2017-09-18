package jp.sample.jaxrs.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/sample")
public interface SampleResource {

    @GET
    @Path("/hello/{message}")
    String sayHello(@PathParam("message") String message);
}