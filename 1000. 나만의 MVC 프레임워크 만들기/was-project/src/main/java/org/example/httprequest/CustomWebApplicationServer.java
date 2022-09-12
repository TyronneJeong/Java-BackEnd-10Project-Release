package org.example.httprequest;

import org.example.calculator.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {

    private final int port;

    private final ExecutorService executorService = Executors.newFixedThreadPool(3); // 10개까지 가능한 스레드 풀

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomerApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomerApplicationServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null) { // 얼마나 빠른 속도로 반복하고 있는지는 가끔 궁금
                logger.info("[CustomerWebApplicationServer] client connected!");

                // [AS-IS]
                // 새로운 스레드 생성 후 사용자 요청 처리
                // 새로운 스레드 생성은 스택 프레임 생성 작업으로 인해 성능 저하가 발생된다.
                // 스레드별 요청 처리를 위해 ContextSwitching 이 발생되며 이 또한 오버헤드 원인이다.
                // 때문에 스레드 풀을 이용하여 선택적 스레드 생성을 컨트롤 한다.
                // new Thread(new ClientRequestHandler(clientSocket)).start();

                // [TO-BE]
                // n개의 제한된 스레드 풀 내에서 새로운 스레드를 생성 시킨다.
                // 스레드 풀의 순번 대로 사용 후 다시 처음 시퀀스로 돌아가 미사용 스레드를 배정하게 된다.
                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }
    }
}
