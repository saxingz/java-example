package org.saxing.nettystudy.a01_basic_io;

public class ServerBoot {

    private static final int PORT = 8081;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
    }

}
