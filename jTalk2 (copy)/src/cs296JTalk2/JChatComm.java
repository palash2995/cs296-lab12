package cs296JTalk2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class JChatComm extends JChatSession implements JTalkChat,Runnable{
	private Socket socket;
	private JServer server;
	private Thread read;
	private Thread send;
	private int identity;
	private boolean status = false ;
	
	public void setup() throws IOException{
		status=true;
		receiveMessage();
		sendMessage();	
	}
	public JChatComm(JServer server1,Socket socket1) throws IOException{
		server=server1;
		socket=socket1;
		identity = 1;
		new Gui();
		setup();
	}
	public JChatComm(Socket socket1,CompleteMsg l1, CompleteMsg l2) throws IOException{
		this.logMessage(l1);
		this.logMessage(l2);
		socket=socket1;
		identity = 0;
		new Gui();
		setup();
	}
	public void sendMessage() throws IOException{
		send = Thread.currentThread();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		   while (!(line.equalsIgnoreCase("End Chat") == true && identity == 0)&&status){
			   ObjectOutputStream ous =  new ObjectOutputStream(socket.getOutputStream());
		       line = in.readLine();
		       line = line.trim();
		       line = line.substring(0,Math.min(140, line.length()));
		       if((line.equals("Y")||line.equals("y"))&&this.getMessagesNumber()==1){
		    	   line = "Sure. Let us begin.";
		       }
		       Date date=new Date();
//		       DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//		       dos.writeUTF(line);
		       JPacket packet=new JPacket(line,date);
//		       System.out.println(packet.getMessage());
		       if(status){
		    	   ous.writeObject(packet);
		    	   CompleteMsg message=new CompleteMsg(line,date,"You","Other");
		    	   this.logMessage(message);
		       }
		       //do something
		   }
		   status = false;
		   read.interrupt();
		   in.close();
	}
	public void receiveMessage(){	
		read = new Thread( this );
		read.start();
	}
	public void run(){
		try {
			while(true){
				try {
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					Object packet1 = new Object();
					JPacket packet;
					packet1=ois.readObject();
					packet = (JPacket) packet1;
					if(!(packet.getMessage() == null)){
//						JPacket packet = (JPacket) packet1;
						CompleteMsg message=new CompleteMsg(packet.getMessage(),packet.getDate(),"Other","You");
//						System.out.println(packet.getMessage());
						if(!(message == null)&&status){
							this.logMessage(message);
							message.print();
							if(packet.getMessage().equals("Free for a chat?") && this.getMessagesNumber()==1){
								System.out.println("Press Y to begin or N to decline.");
							}
							if(packet.getMessage().equalsIgnoreCase("End Chat") == true && identity == 1){
								this.endChat();
								break;
							}
						}else{
							break;
						}
						
					}
				}
				catch (ClassNotFoundException e) {
					this.endChat();
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			this.endChat();
		} catch (IOException e1) {
				this.endChat();
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
	}
	public void logMessage(CompleteMsg message){
		this.list.add(message);
	}
	public void endChat(){
		status = false;
		if(identity == 1){
			server.changeStatus();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		read.interrupt();
		send.interrupt();
	}
}