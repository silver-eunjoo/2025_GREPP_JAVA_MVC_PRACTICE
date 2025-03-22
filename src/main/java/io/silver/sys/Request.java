package io.silver.sys;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private UriParser parser;
    private Map<String, Object> parameters = new HashMap<>();

    public Request(String command) {
        parser = new UriParser(command);
        parameters = this.parser.getParameter();
    }

    public boolean isValid() {
        return this.parser.isValid();
    }

    public String getControllerCode() {
        return this.parser.getControllerCode();
    }

    public String getFunction() {
        return this.parser.getFunction();
    }

}
