package io.silver.sys;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class UriParser {

    private boolean isValid = true;
    private String controllerCode;
    private String function;
    private String URI;
    private Map<String, Object> parameter = new HashMap<>();

    public UriParser(String uri) {
        this.URI = uri;
        parse(URI);
    }


    // /posts/add?param1=value1&param2=value2
    // valid한지 확인한 후 uri 리턴
    public String parse(String uri) {

        if(!uri.startsWith("/")) {
            isValid = false;
            return uri;
        }

        try{
            String[] split = uri.split("//?", 2);

            if(split.length == 2) { // 파라미터가 있을 때
                setParameters(split[1]);
                return uri;
            }

            // 파라미터가 없을 때
            String[] uriFront = split[0].split("/");

            // ["", "posts", "add"]
            if(uriFront.length != 3) {
                throw new IllegalArgumentException("uri가 잘못되었습니다. 다시 입력해주세요");
            }

            controllerCode = uriFront[1];
            function = uriFront[2];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("잘못된 URL 형식입니다. 다시 입력해주시기 바랍니다.");
        }

        return uri;
    }

    private void setParameters(String uriBack) {
        //"param1=value1&param2=value"
        if(uriBack.contains("&")){ //파라미터가 여러가지 일 때
            String[] split = uriBack.split("&");
            for (String s : split) {
                String[] params = s.split("=");
                parameter.put(params[0], params[1]);
            }
        } else {
            String[] split = uriBack.split("=", 2);
            parameter.put(split[0], split[1]);
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public String getControllerCode() {
        return controllerCode;
    }

    public String getFunction() {
        return function;
    }

    public String getURI() {
        return URI;
    }

    public Map<String, Object> getParameter() {
        return parameter;
    }
}
