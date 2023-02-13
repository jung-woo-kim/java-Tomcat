package webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestHandler implements Runnable{
    Socket connection;
    private static final Logger log = Logger.getLogger(RequestHandler.class.getName());

    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()){
            DataOutputStream dos = new DataOutputStream(out);
            byte[] data = "My Web Server".getBytes();
            response200Header(dos,data.length);
            responseBody(dos,data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBody) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBody + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.log(Level.SEVERE,e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body);
            dos.flush();
            dos.close();
        } catch (IOException e) {
            log.log(Level.SEVERE,e.getMessage());
        }
    }
}
