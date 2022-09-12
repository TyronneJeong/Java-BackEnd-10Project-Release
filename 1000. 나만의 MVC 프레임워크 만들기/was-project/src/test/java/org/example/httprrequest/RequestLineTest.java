package org.example.httprrequest;

import org.example.httprequest.RequestLine;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestLineTest {

    @Test
    void name() {
        RequestLine requestLine = new RequestLine("GET /calculator?operand1=4&operator=*&operand2=6 HTTP/1.1");
        assertThat(requestLine).isNotNull();
        assertThat(requestLine).isEqualTo(new RequestLine("GET", "/calculator", "operand1=4&operator=*&operand2=6"));
    }
}
