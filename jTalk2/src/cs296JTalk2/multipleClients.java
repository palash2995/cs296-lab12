package cs296JTalk2;

import java.io.IOException;
import java.net.Socket;

public class multipleClients extends Thread{
	
	JServer j;
    Socket s;
	
	public multipleClients(JServer j1, Socket s1){
		j=j1;
        s=s1;
	}
	public void run(){
		try {
                System.out.println("Connection from "+s);
				new JChatComm(j,s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
