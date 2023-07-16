import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to Chat Server");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String inputLine;
            while ((inputLine = consoleReader.readLine()) != null) {
                writer.println(inputLine);

                String serverResponse = reader.readLine();
                System.out.println("Server: " + serverResponse);

                if (inputLine.equalsIgnoreCase("close")) {
                    System.out.println("Closing the connection...");
                    break;
                }
            }

            reader.close();
            writer.close();
            consoleReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
