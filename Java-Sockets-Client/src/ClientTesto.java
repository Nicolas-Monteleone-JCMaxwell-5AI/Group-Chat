/*
 * ClientTesto.java e' il programma per connettersi ad un Server usando i socket
 * ed inviare il testo ricevuto dalla linea di comando.
 * Utilizza una "Thread" per l'ascolto dei messaggi provenienti dal Server.
 */

/**
 *
 * @author Prof. Matteo Palitto
 */
import java.net.*;
import java.io.*;

public class ClientTesto {
    public static String groupChat="";
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
	// verifica correttezza dei parametri
	if (args.length != 3) {
            System.out.println("Uso: java client-Testo <indirizzo IP Server> <Porta Server>");
            return;
        }	
	String hostName = args[0];
	int portNumber = Integer.parseInt(args[1]);
	try {
            // prendi l'indirizzo IP del server dalla linea di comando
            InetAddress address = InetAddress.getByName(hostName);
			
            // creazione socket 
            Socket clientSocket = new Socket(address, portNumber);
		
            // visualizza istruzioni
            System.out.println("Client-Testo: usa Ctrl-C per terminare, ENTER per spedire la linea di testo.\n");
			
            // connessione concorrente al socket per ricevere i dati da Server
            listener l;
            try {
                l = new listener(clientSocket);
                Thread t = new Thread(l);
                t.start();
            } catch (Exception e) { System.out.println("Connessione NON riuscita con server: "); }
		
            // connessione al socket (in uscita client --> server)
            PrintWriter out =  new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(args[2]);
            // connessione allo StdIn per inserire il testo dalla linea di comando
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            
            //leggi da linea di comando il testo da spedire al Server
            System.out.print(">"); //visualizza il prompt
            while ((userInput = stdIn.readLine()) != null) {
            	// scrittura del messaggio da spedire nel socket 
		out.println(userInput);
                if(userInput.length()>5){
                if(userInput.substring(0, 5).equals("/join"))
                {
                    groupChat=userInput.substring(6, userInput.length());
                }
                }
                if(userInput.equals("/quit"))
                {
                    groupChat="";
		}
                System.out.println("Messaggio spedito al server: " + userInput);
                System.out.print(groupChat+">"); //visualizza il prompt
            }
            // chiusura socket
            clientSocket.close();
            System.out.println("connessione terminata!");
	}
        catch (IOException e) { System.out.println("Connessione terminata dal server: "); e.printStackTrace(); }
    }
    
}
