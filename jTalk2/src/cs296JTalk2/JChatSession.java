package cs296JTalk2;

import java.util.ArrayList;
import java.util.List;

public class JChatSession {
	List<CompleteMsg> list;
	public JChatSession(){
		list=new ArrayList<CompleteMsg>();
	}
	public void printLog(){
		for (CompleteMsg msg : list){
			msg.print();
		}
	}
	public int getMessagesNumber(){
		return this.list.size();
	}
}