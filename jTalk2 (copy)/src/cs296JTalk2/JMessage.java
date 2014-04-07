package cs296JTalk2;

import java.io.Serializable;

public class JMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String message;
	public JMessage(String Msg){
		message=Msg;
	}
	public String getMessage(){
		return message;
	}
	public JMessage(){	
	}
}