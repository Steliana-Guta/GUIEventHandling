import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Tools {

    private static JLabel resultLabel;

    public static String doSearch( JLabel result) {
        InetAddress host = null;
        try {
            host = InetAddress.getLocalHost();

            resultLabel.setText(
                    String.format("Address: %s" +
                            "   Hostname: %s", host.getHostAddress(), host.getHostName()));


        }
        catch (UnknownHostException e) {
            System.out.println("Unable to resolve name and address");
        }
        return resultLabel.getText();
    }

}
