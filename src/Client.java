import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.print("Enter host: ");
        String host = new Scanner(System.in).nextLine();
        System.out.print("Enter port: ");
        int port = new Scanner(System.in).nextInt();
        Socket clientConnection = new Socket(host, port);
        PrintWriter writer = new PrintWriter(clientConnection.getOutputStream());
        System.out.println("Connected to server");

        while(true) {
            System.out.print("you: ");
            String str = (new Scanner(System.in)).nextLine();
            writer.println(str);
            writer.flush();
        }
    }
}


class ClientInfo implements Serializable {
    public int id;
    public Socket socket;

    public ClientInfo(int id, Socket socket) {
        this.id = id;
        this.socket = socket;
    }
}

