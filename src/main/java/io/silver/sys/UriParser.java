package io.silver.sys;

import java.util.HashMap;
import java.util.Map;

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


        String[] uriSplit = uri.split("\\?", 2);

        if(uriSplit.length == 2) { // 파라미터가 있을 때
            setParameters(uriSplit[1]);
        }

        // 파라미터가 없을 때
        String[] uriFront = uriSplit[0].split("/");

        // ["", "posts", "add"]
        if(uriFront.length != 3) {
            isValid = false;
            return uri;
        }

        controllerCode = uriFront[1];
        function = uriFront[2];

        return uri;
    }

    private void setParameters(String uriBack) {
        //"param1=value1&param2=value"
        try{
            if(uriBack.contains("&")){ //파라미터가 여러가지 일 때
                String[] split = uriBack.split("&");
                for (String s : split) {
                    String[] params = s.split("=",2);

                    if(params[1].equals("")) {
                        throw new IllegalArgumentException("잘못된 파라미터 값이 입력되었습니다.URL을 확인해주세요.");
                    }

                    parameter.put(params[0], params[1]);
                }
            } else {
                String[] split = uriBack.split("=", 2);

                if(split[1].equals("")) {
                    throw new IllegalArgumentException("잘못된 파라미터 값이 입력되었습니다.URL을 확인해주세요.");
                }

                parameter.put(split[0], split[1]);
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            this.isValid = false;
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

    public Map<String, Object> getParameter() {
        return parameter;
    }
}
