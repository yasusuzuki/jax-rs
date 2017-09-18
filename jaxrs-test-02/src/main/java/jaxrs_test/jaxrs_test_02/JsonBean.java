package jaxrs_test.jaxrs_test_02;

import java.net.URL;
import java.util.ArrayList;

//import org.codehaus.jackson.annotate.JsonProperty;

//import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonBean {
    private String val1;
    private String val2;
    private String val3;
    private ArrayList<String> val4;
    private URL val5;
    
 	public URL getVal5() {
		return val5;
	}

	public void setVal5(URL val5) {
		this.val5 = val5;
	}

	public ArrayList<String> getVal4() {
		return val4;
	}

	public void setVal4(ArrayList<String> val4) {
		this.val4 = val4;
	}

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    //@JsonProperty("val2")
    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }
    public String getVal3() {
 		return val3;
 	}

 	public void setVal3(String val3) {
 		this.val3 = val3;
 	}


}
