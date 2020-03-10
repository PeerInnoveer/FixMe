package router;

import java.io.*;
import java.net.*;

public class ServerHandler implements Runnable {

    public ServerHandler(boolean serverFlag) throws IOException {
        if (serverFlag) {
            try {
                Socket socket = Router.marketSocket.accept();
                Router.markets.put(++Router.marketId, socket);
                System.out.print(socket);
                Router.marketWriter = new PrintWriter(socket.getOutputStream());
                Router.marketWriter.println("Connected to router with market ID: " + Router.marketId);
                Router.marketWriter.println(Router.marketId);
                Router.marketWriter.flush();
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String string = bufferedReader.readLine();
                System.out.println(string);
                System.out.println("Market ID: " + Router.marketId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Socket broker = Router.brokerSocket.accept();
                Router.brokers.put(++Router.brokerId, broker);
                PrintWriter brokerWriter = new PrintWriter(broker.getOutputStream());
                brokerWriter.println("Connected to router with broker ID: " + Router.brokerId);
                brokerWriter.println(Router.brokerId);
                brokerWriter.flush();
                InputStreamReader brokerStreamReader = new InputStreamReader(broker.getInputStream());
                BufferedReader brokerReader = new BufferedReader(brokerStreamReader);
                InputStreamReader marketStreamReader = new InputStreamReader(Router.markets.get(Router.marketId).getInputStream());
                BufferedReader marketReader = new BufferedReader(marketStreamReader);
                String string = brokerReader.readLine();
                System.out.println(string);
                System.out.println("Broker ID: " + Router.brokerId);
                String Id = null;
                int count = 0;
                while (!string.equals("exit")) {
                    string = brokerReader.readLine();
                    Id = brokerReader.readLine();
                    System.out.println("Broker " + Id + ": " + string);
                    Router.marketWriter.println(string);
                    Router.marketWriter.println(Id);
                    Router.marketWriter.flush();
                    string = marketReader.readLine();
                    Id = marketReader.readLine();
                    brokerWriter.println(string);
                    brokerWriter.println(Id);
                    brokerWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while(true){

        }
    }
}
