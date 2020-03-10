package market;

import java.net.*;
import java.io.*;

public class Market {

    public static void main (String[] arg) throws IOException {
        try {
            Socket socket = new Socket("localhost",5000);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("New market client connected.");
            printWriter.flush();
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String string = bufferedReader.readLine();
            System.out.println(string);
            String marketId = bufferedReader.readLine();
            String Id = null;
            while (true) {
                string = bufferedReader.readLine();
                Id = bufferedReader.readLine();
                if (Checksum.compare(string) && (Checksum.decode(string).equals("buy") || Checksum.decode(string).equals("sell"))) {
                    System.out.println("Broker " + Id + ": " + Checksum.decode(string));
                    printWriter.println(Checksum.encode("accepted"));
                } else {
                    printWriter.println(Checksum.encode("rejected"));
                }
                    printWriter.println(marketId);
                    printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}