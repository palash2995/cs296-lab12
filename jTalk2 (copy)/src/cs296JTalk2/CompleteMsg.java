package cs296JTalk2;

import java.util.Date;

public class CompleteMsg extends JPacket {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String who;
	private String toWhom;
	public CompleteMsg(String msg,Date dt,String who1,String whom1){
		super(msg,dt);
		who=who1;
		toWhom=whom1;
	}
	public String WhoSaid(){
		return who;
	}
	public String toWhom(){
		return toWhom;
	}
	public Date getDate(){
		return this.date;
	}
	public void print(){
		System.out.println((this.date.toString())+" "+who+" - "+message);
	}
}