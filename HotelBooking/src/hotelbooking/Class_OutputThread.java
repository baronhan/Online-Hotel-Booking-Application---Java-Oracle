package hotelbooking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Class_OutputThread extends Thread {
    Socket socket; //socket is joining to the communication
    JTextArea txtArea; //txt contains communicated message
    BufferedReader bf; //input buffer of the socket
    String sender; //sender, a site of the communication
    String receiver; //receiver, other site of the communication
    
    public Class_OutputThread(Socket s, JTextArea txtArea, String sender, String receiver) {
        super();
        this.socket = s;
        this.txtArea = txtArea;
        this.sender = sender;
        this.receiver = receiver;
        
        try {
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Network Error!");
            System.exit(0);
        }
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                if (socket != null) {
                    String msg = ""; //get data from the input stream
                    if ((msg = bf.readLine()) != null && msg.length() > 0) {
                        txtArea.append("\n" + receiver + ": " + msg);
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                // Handle the exception appropriately, e.g., log it or show a message
            }
        }
    }
}
