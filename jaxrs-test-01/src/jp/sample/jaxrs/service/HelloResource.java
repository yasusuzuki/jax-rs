package jp.sample.jaxrs.service;
import org.springframework.stereotype.Component;

@Component
public class HelloResource implements SampleResource {

    /**
     * {@inheritDoc}
     */
    @Override
    public String sayHello(String message) {
    	System.out.println("Received Request" + message);
        return String.format("Hello, %s", message);
    }

}