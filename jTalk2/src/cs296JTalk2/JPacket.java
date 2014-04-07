package cs296JTalk2;

import java.io.Serializable;
import java.util.Date;

public class JPacket extends JMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Date date=new Date();
	public JPacket(String message,Date dt){
		super(message);
		date=dt;
	}
	public JPacket(){
	}
	public Date getDate(){
		return date;
	}
}