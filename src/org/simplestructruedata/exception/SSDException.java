package org.simplestructruedata.exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SSDException extends RuntimeException {
	
	private static final long serialVersionUID = 2966485954767238164L;
	
	private List<String> 			messages = new ArrayList<String>();
	
	public SSDException() { }

	public SSDException(String message) {
		this.appendMessage(message);
	}
	
	public SSDException(Exception e) {
		this.appendMessage(e.getMessage());
	}
	
	public void appendMessage(String message) {
		this.messages.add(message);
	}
	
	 @Override
    public String getMessage() {
		StringBuffer mensagem = new StringBuffer();
    	Iterator<String> iter = this.messages.iterator();
    	while(iter.hasNext()){
    		String msg = iter.next();
    		mensagem.append(msg);
    		if(iter.hasNext()){
    			mensagem.append("\n");    			
    		}
    	}
        return mensagem.toString();
    }

}
