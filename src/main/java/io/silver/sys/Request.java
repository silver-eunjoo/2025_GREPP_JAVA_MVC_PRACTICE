package io.silver.sys;

public class Request {

    private UriParser parser;

    public Request(String command) {
        parser = new UriParser(command);
    }

    public boolean isValid() {
        return this.parser.isValid();
    }

}
