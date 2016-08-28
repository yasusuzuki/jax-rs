package jaxrs_test;
 

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RSではこのクラスがないと、動かない
 *
 */
@ApplicationPath("/api")
public class RestApplication extends Application {
    // nodef

}