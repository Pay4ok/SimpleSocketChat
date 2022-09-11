import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    public static ServerSocket serverSocket;

    public Server() {
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Enter port: ");
        serverSocket = new ServerSocket(new Scanner(System.in).nextInt());
        ConnectionThread connectionThread = new ConnectionThread(serverSocket);
        connectionThread.start();
        System.out.println("Server started");

        while(true) {
            System.out.print("");

            for(int i = 0; i < ConnectionThread.clients.size(); ++i) {
                InputStreamReader reader = new InputStreamReader(((ClientInfo)ConnectionThread.clients.get(i)).socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                if (bufferedReader.ready()) {
                    String str = bufferedReader.readLine();
                    System.out.println("client: " + str);
                }
            }
        }
    }
}

class ConnectionThread extends Thread {
    public static int clientCount = 0;
    public static Socket clientSocket;
    public static ArrayList<ClientInfo> clients = new ArrayList();
    public static ServerSocket serverSocket;

    public ConnectionThread(ServerSocket serverSocket) {
        ConnectionThread.serverSocket = serverSocket;
    }

    public void run() {
        while(true) {
            try {
                clientSocket = serverSocket.accept();
                ClientInfo clientInfo = new ClientInfo(++clientCount, clientSocket);
                clients.add(clientInfo);
                System.out.println("Client connected");
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }
    }
}

