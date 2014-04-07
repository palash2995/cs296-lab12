package cs296JTalk2;

import java.io.IOException;
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
	private Gui gui;
	private final static String newline = "\n";
	public void setup(Gui gui) throws IOException{
		status=true;
		receiveMessage(gui);
		sendMessage(gui,new CompleteMsg("",new Date(),"You","Other"));	
	}
	public JChatComm(JServer server1,Socket socket1) throws IOException{
		gui=new Gui(this);
		new CreateAndShowGUI(gui);
		server=server1;
		socket=socket1;
		identity = 1;
		setup(gui);
	}
	public JChatComm(Socket socket1,CompleteMsg l1, CompleteMsg l2) throws IOException{
		gui=new Gui(this);
		new CreateAndShowGUI(gui);
		this.logMessage(l1);
		this.logMessage(l2);
		socket=socket1;
		identity = 0;
		setup(gui);
	}
	public void sendMessage(Gui gui, CompleteMsg msg) throws IOException{
		send = Thread.currentThread();
		String in = msg.getMessage();
		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		   while (!(line.equalsIgnoreCase("End Chat") == true && identity == 0)&&status){
			   ObjectOutputStream ous =  new ObjectOutputStream(socket.getOutputStream());
		       line = in;
		       line = line.trim();
		       line = line.substring(0,Math.min(140, line.length()));
		       if((line.equals("Y")||line.equals("y"))&&this.getMessagesNumber()==1){
		    	   line = "Sure. Let us begin.";
		       }
		       Date date=msg.getDate();
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
		   //in.close();
	}
	public void receiveMessage(Gui gui){	
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
							this.gui.textArea.append("Other: "+message.getMessage()+newline);
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