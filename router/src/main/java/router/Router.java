package router;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Router {

    // private static ExecutorService taskExecutor = Executors.newCachedThreadPool(Executors.defaultThreadFactory());

    static HashMap<Integer, Socket> markets = new HashMap<Integer, Socket>();
    static HashMap<Integer, Socket> brokers = new HashMap<Integer, Socket>();
    static ServerSocket marketSocket;
    static ServerSocket brokerSocket;
    static PrintWriter marketWriter;
    static PrintWriter brokerWriter;
    static int brokerId = 0;
    static int marketId = 0;

    public static void main (String[] arg) throws IOException {
        try {
            marketSocket = new ServerSocket(5000);
            System.out.println("Waiting for market client..");
            new ServerHandler(true);
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            brokerSocket = new ServerSocket(5001);
            System.out.println("Waiting for broker client..");
            new ServerHandler(false);
        } catch (Exception e){
            e.printStackTrace();
        }
//        taskExecutor.execute(new ServerHandler(false));
//        taskExecutor.execute(new ServerHandler(true));
//        Runnable runnable = new ServerHandler(true);
//        new Thread(runnable);
//        runnable = new ServerHandler(false);
//        new Thread(runnable);
    }
}
