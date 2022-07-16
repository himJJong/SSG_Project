package com.ll.exam.App;

public class Rq {
    String url;
    String path;
    String queryString;

    public Rq(String url) {
        this.url = url;
        String[] urlBits = url.split("\\?", 2);
        this.path = urlBits[0];
        if(urlBits.length==2) {
            this.queryString = urlBits[1];
        }
    }

    public int getIntParam(String paramName, int defaultValue) {


        if(queryString==null){
            return defaultValue;
        }

        String[] bits = queryString.split("&");

        for (String urlBit : bits) {
            String[] paramNameAndValue = urlBit.split("=", 2);
            String paramName_ = paramNameAndValue[0];
            String paramValue = paramNameAndValue[1];

            if (paramName.equals(paramName_)) {
                return Integer.parseInt(paramValue);
            }
        }
        return defaultValue;
    }

    public String getPath() {

        return path;
    }
}
