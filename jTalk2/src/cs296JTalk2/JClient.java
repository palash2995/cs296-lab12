package cs296JTalk2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class JClient implements Runnable {
	private Socket socket;
	private Thread mainThread;
	private int a=1;
	public JClient(String host, int port) throws ClassNotFoundException{
		callServer(host,port);
	}
	private void callServer(String host,int port) throws ClassNotFoundException{
		try{
			socket = new Socket (host, port);
            System.out.println("Connected to socket "+socket);
            Date date=new Date();
            JPacket packet=new JPacket("Free for a chat?",date);
            System.out.println("Free for a chat?");
            ObjectOutputStream ous =  new ObjectOutputStream(socket.getOutputStream());
            ous.writeObject(packet);
            CompleteMsg message=new CompleteMsg("Free for a chat?",date,"me","other");
            mainThread = Thread.currentThread();
            Thread alpha = new Thread(this);
            alpha.start();
            ObjectInputStream oin =  new ObjectInputStream(socket.getInputStream());
            date = new Date();
            Object tmp = oin.readObject();
			packet = (JPacket) tmp;
            CompleteMsg response=new CompleteMsg("Free for a chat?",date,"me","other");
            new JChatComm(socket,message,response);
            if (packet.getMessage().equals("Sure. Let us begin.")){
            	a++;
            	System.out.println(packet.getMessage());
                new JChatComm(socket,message,response);
            }
            else {
                System.out.println("Server Busy :(");
                alpha.interrupt();
            }
            } 
        catch (IOException ie){
            System.out.println(ie);
        }

	}
	public void run(){
		try {
		    Thread.sleep(10000);
		    if(a==1){
		    	mainThread.interrupt();
		    	System.out.println("Server Busy :(");
		    	socket.close();
		    }
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		
	}

}