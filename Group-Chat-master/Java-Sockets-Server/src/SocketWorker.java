/*
 * socketWorker.java ha il compito di gestire la connessione al socket da parte di un Client.
 * Elabora il testo ricevuto che in questo caso viene semplicemente mandato indietro con l'aggiunta 
 * di una indicazione che e' il testo che viene dal Server.
 */
import java.net.*;
import java.io.*;

/**
 *
 * @author Prof. Matteo Palitto
 */
class SocketWorker implements Runnable {
  private Socket client;
  public String Nickname=null;
  public String currentGroupChat="";
  BufferedReader in = null;
  PrintWriter out = null;

    //Constructor: inizializza le variabili
    SocketWorker(Socket client) {
        this.client = client;
        System.out.println("Connesso con: " + client);
    }

    // Questa e' la funzione che viene lanciata quando il nuovo "Thread" viene generato
    public void run(){
        try{
          // connessione con il socket per ricevere (in) e mandare(out) il testo
          in = new BufferedReader(new InputStreamReader(client.getInputStream()));
          out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
          System.out.println("Errore: in|out fallito");
          System.exit(-1);
        }
        
        String line = "";
        int clientPort = client.getPort(); //il "nome" del mittente (client)
        while(line != null){
          try{
            line = in.readLine();
            if(Nickname==null)
            {
                Nickname=line;
            }else{
                manageCommand(line);
            }
           } catch (Exception e) {
            System.out.println(Nickname + " si e' disconnesso");
            ServerTestoMultiThreaded.SocketList.remove(this);
            break;
           }
        }
    }
    
    public void manageCommand(String line)
    {
        if(line.equals("/nickname")){
            System.out.println("Client list requested. Sent to: "+Nickname);
            for(int i=0;i<ServerTestoMultiThreaded.SocketList.size();i++)
                {
                    out.println("Server-->" + Nickname + ">> " + ServerTestoMultiThreaded.SocketList.get(i).Nickname);
                }
                return;
            }
        if(currentGroupChat.equals("")){
            if(line.length()>5){
            if(line.substring(0, 5).equals("/join"))
            {
                currentGroupChat = line.substring(6, line.length());
                out.println("Joined to "+line.substring(6, line.length())+" successfully");
                return;
            }
            }
            //Manda lo stesso messaggio appena ricevuto con in aggiunta il "nome" del client
            out.println("Server-->" + Nickname + ">> " + line);
            //scrivi messaggio ricevuto su terminale
            System.out.println(Nickname + ">> " + line);
        }
        else
        {
            if(line.equals("/quit"))
            {
                currentGroupChat="";
                return;
            }
            if(line.contains("/invite"))
            {
                String nickname = line.substring(8,line.length());
                for(int i=0;i<ServerTestoMultiThreaded.SocketList.size();i++)
                {
                    if(!ServerTestoMultiThreaded.SocketList.get(i).Nickname.equals(Nickname))
                    {
                        if(!ServerTestoMultiThreaded.SocketList.get(i).currentGroupChat.equals(currentGroupChat) && ServerTestoMultiThreaded.SocketList.get(i).currentGroupChat.equals("")){
                        ServerTestoMultiThreaded.SocketList.get(i).currentGroupChat = currentGroupChat;
                        ServerTestoMultiThreaded.SocketList.get(i).sendMessage("You joined "+currentGroupChat);
                        }
                        else
                        {
                        sendMessage(nickname+" can't join in this groupchat");
                        }
                    }
                    
                }
            }
            for(int i=0;i<ServerTestoMultiThreaded.SocketList.size();i++)
            {
                if(!ServerTestoMultiThreaded.SocketList.get(i).Nickname.equals(Nickname) && ServerTestoMultiThreaded.SocketList.get(i).currentGroupChat.equals(currentGroupChat))
                    {
                        ServerTestoMultiThreaded.SocketList.get(i).sendMessage(Nickname+">"+line);
                    }
            }
                        
         }
		 
    }
                
    
    
    
    public void sendMessage(String message)
    {
        out.println(message);
    }
}
