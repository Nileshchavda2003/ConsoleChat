import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Chat Server started on port 12345");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println("Client: " + inputLine);

                if (inputLine.equalsIgnoreCase("close")) {
                    System.out.println("Closing the connection...");
                    break;
                }

                System.out.print("Server: ");
                String outputLine = consoleReader.readLine();
                writer.println(outputLine);
            }

            reader.close();
            writer.close();
            consoleReader.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
