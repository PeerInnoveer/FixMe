package broker;

import java.net.*;
import java.io.*;

public class Broker {

    public static void main (String[] arg) {
        try {
            Socket socket = new Socket("localhost", 5001);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("New broker client connected.");
            printWriter.flush();
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String string = bufferedReader.readLine();
            System.out.println(string);
            String id = bufferedReader.readLine();
            System.out.println("Enter message:");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            String marketId;
            while ((line = buffer.readLine()) != null) {
                printWriter.println(Checksum.encode(line));
                printWriter.println(id);
                printWriter.flush();
                string = bufferedReader.readLine();
                marketId = bufferedReader.readLine();
                System.out.println("Market " + marketId + ": " + Checksum.decode(string));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
