package market;

import java.nio.charset.StandardCharsets;

public class Checksum {

    public Checksum(){

    }

    static String encode (String message){
        return message + "Checksum:" + convert(message);
    }

    static String decode(String message){
        return message.replace(message.substring(message.indexOf("Checksum:")), "");
    }

    private static String convert(String message) {

        byte[] messageBytes = message.getBytes(StandardCharsets.US_ASCII);

        int sum = 0;
        for (int i = 0; i < message.length(); i++) {
            sum += messageBytes[i];
        }

        return String.valueOf(sum);
    }

    static boolean compare(String message) {
        String checksum = message.substring(message.indexOf("Checksum:") + 9);
        message = message.replace(message.substring(message.indexOf("Checksum:")), "");

        return convert(message).equals(checksum);
    }
}
