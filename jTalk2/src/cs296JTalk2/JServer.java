package cs296JTalk2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JServer extends Thread{
	private ServerSocket ss;
	private boolean connectionStatus = false; 
	public JServer(int port) throws IOException{
		acceptConnections( port );
	}
	public void acceptConnections(int port) throws IOException{
		ss = new ServerSocket(port);
		System.out.println("Listening on port "+ss);
		while(true)
        {
			Socket s = ss.accept();
			multipleClients m = new multipleClients(this,s);
			m.start();
			//if(!connectionStatus){
			//	changeStatus();
         }		
			
		
	}
	
	public void changeStatus(){
		connectionStatus = !(connectionStatus);
	}

}
