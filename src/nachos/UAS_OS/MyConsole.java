package nachos.UAS_OS;

import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;


public class MyConsole {
	SerialConsole sercon;
	Semaphore semaphore = new Semaphore(0);;
	char temp;

	public MyConsole(SerialConsole sercon) {
		// TODO Auto-generated constructor stub
		this.sercon = sercon;
		
		Runnable recv = new Runnable() {
			public void run() {
				temp = (char) sercon.readByte();
				semaphore.V();
			}
		};
		
		Runnable send = new Runnable() {
			
			public void run() {
				semaphore.V();
			}
		};
		
		this.sercon.setInterruptHandlers(recv, send);
	}
	
	public void write(String str){
		for(int i=0; i<str.length(); i++){
			this.sercon.writeByte(str.charAt(i));
			semaphore.P();
		}
	}
	
	public void writeln(String str){
		write(str+'\n');
	}
	
	public String read(){
		String result = "";
		do{
			semaphore.P();
			if(temp!='\n'){
				result+=temp;
			}
		}while(temp!='\n');
		return result;
	}
	
	public int readInt(){
		int result = 0;
		try {
			result = Integer.parseInt(read());
		} catch (Exception e) {
			// TODO: handle exception
			result = 0;
		}
		return result;
	}

}
