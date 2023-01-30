import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String welcome = "Welcome!";
    String hi = "Hello World!";
    String stringList = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Altair's Website: %s", welcome);
        } else if (url.getPath().equals("/hello")) {
            return String.format("Altair's Website: %s", hi);
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    stringList += parameters[1] + "\n";
                    return stringList;
                }
            }
            return "404 Not Found!";
        }
    }
}
public class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
