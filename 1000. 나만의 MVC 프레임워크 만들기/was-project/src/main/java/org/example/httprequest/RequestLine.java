package org.example.httprequest;

import java.util.Objects;

public class RequestLine {

    public static final String GET = "GET";
    private final String method;
    private final String urlPath;
    private QueryStrings quesriString;

    /**
     * GET /calculator?operand1=4&operator=*&operand2=6 HTTP/1.1
     */
    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];    // GET

        String[] urlPathTokens = tokens[1].split("\\?"); // /calculator ? operand1=4&operator=*&operand2=6
        this.urlPath = urlPathTokens[0];

        if (urlPathTokens.length == 2) {
            this.quesriString = new QueryStrings(urlPathTokens[1]);
        }
    }

    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.quesriString = new QueryStrings(queryString);
    }

    public boolean isGetRequest() {
        return this.method.equals(GET);
    }

    public boolean matchPath(String path) {
        return this.urlPath.equals(path);
    }

    public QueryStrings getQueryStrings() {
        return this.quesriString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(quesriString, that.quesriString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, quesriString);
    }
}
