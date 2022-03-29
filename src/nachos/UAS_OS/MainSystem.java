package nachos.UAS_OS;

import nachos.machine.Machine;
import nachos.machine.OpenFile;
import nachos.UAS_OS.TaskIndex;
import nachos.threads.KThread;

import java.util.Vector;


public class MainSystem {
	int index = 0;
	int waktu=0;
	
	MyConsole con;
	Vector<TaskIndex> listTaskIndex;
	private String tempTitle;
	private String tempDescription;
	private String tempType;
	
	public void clear(){
    	for(int i=0; i<25; i++) con.writeln("");
    }
	
	public TaskIndex addTaskIndex(int index){
    	for (TaskIndex taskIndex : listTaskIndex) 
			if(taskIndex.getIndex() == index) return taskIndex;
    	
    	TaskIndex taskIndex = new TaskIndex(index);
    	listTaskIndex.add(taskIndex);
    	return taskIndex;
    }
	
	public void addTask(){
    	clear();
    	String title = "";
    	String desc = "";
    	String type = "";
    	String status = "Not Done";
    	
    	index++;
    	TaskIndex current = addTaskIndex(index);
    	
    	
    	do{
    		con.write("Input Task Title[must be 5 - 20 characters]: ");
    		title = con.read();
    	}while(title.length()<5 || title.length()>20);    
    	
    	do{
    		con.write("Input Task Description [more than 1 word]: ");
        	desc = con.read();
    	}while(!desc.contains(" ") && (!(desc.length()<10)));
    	
    	do{
    		con.write("Input Task Type [Important | Unimportant]: ");
    		type = con.read();
    	}while(!type.equals("Important") && !type.equals("Unimportant"));
 	
    	MyTask task = new MyTask(title, desc, type, status);
    	current.addlistTask(task);
    	
    	con.write("Success!");
    }
	
	public void viewTask(){
    	clear();
    	if(listTaskIndex.size()!=0){
    		System.out.println("Task List : ");
        	System.out.println("============\n");
        	for (TaskIndex y : listTaskIndex) {
            	System.out.println("No. " + y.getIndex());
        		System.out.println("============");
        		
        		for (MyTask x : y.getListTask()) {
            		System.out.println("Title: " + x.getTaskName());
            		System.out.println("Description : " + x.getTaskDescription());
            		System.out.println("Type : " + x.getTaskType());
            		System.out.println("Status : " + x.getTaskStatus() + "\n");
    			}
        	}
    	}
    		else
    		con.write("There are No Task!" + "\n Press Enter to continue..." );
    	con.read();
    }
	
	 public void completeTask(){
	    	clear();
	    	int task;
	    	
	    	viewTask();
	    	do{
	    		con.write("Input Task Number [1..." + listTaskIndex.size() + "]");
	        	task = con.readInt();
	    	}while(task < 0);
	    	
	    	Vector<KThread> listThread = new Vector<KThread>();
	    	
	    	for(TaskIndex x : listTaskIndex){
	    			listThread.add(new KThread(x));
	    	}
			
	    	if(listThread.size()>0){
	    		
	    		for (TaskIndex y : listTaskIndex) {
	    			if (y.getIndex()==task-1)
	        		for (MyTask x : y.getListTask()){
	            		tempTitle = x.getTaskName();
	            		tempDescription =x.getTaskDescription();
	            		tempType = x.getTaskType();
	    			}
	        	}
	        	
	    		//Remove task
	    		new KThread(listTaskIndex.remove(task-1)).fork();
	    		con.write("Task with index "+task+" has been mark as 'done'!");
	    		
	    		TaskIndex current = addTaskIndex(task);
	    		MyTask temptask = new MyTask(tempTitle, tempDescription, tempType, "Done");
	        	current.addlistTask(temptask);
	    		
	    	}
	    	else {
	    		con.writeln("There are no task data!");
				con.write("Press Enter to continue...");
				con.read();
	    	}
	    	
	    }

	 public void exportTask(){
		 String data = "";
		 
		 OpenFile of = Machine.stubFileSystem().open("task.txt", true);
		 
		 for (TaskIndex y : listTaskIndex) {
         	data += "No. " + y.getIndex()+ "\n============\n";
			 for (MyTask x : y.getListTask()) {
  				data += "Title: " + x.getTaskName()+"\n"
						+ "Description : " + x.getTaskDescription()+"\n"
						+ "Type : " + x.getTaskType()+"\n"
						+ "Status : " + x.getTaskStatus()+"\n";
			}
		 }
			
			
			byte[] writeBuff = data.getBytes();
			of.write(writeBuff, 0, writeBuff.length);
			of.close();
			}
	 
	public MainSystem() {
		
		// Fungsi Untuk Timer
		Runnable handler = new Runnable() {
			
			@Override
			public void run() {
				waktu++;
			}
		};
		Machine.timer().setInterruptHandler(handler);
		
		con = new MyConsole(Machine.console());
		listTaskIndex = new Vector<TaskIndex>();
		
int menu = -1;
		
    	do{ //Tampilan Menu Awal
    		clear();
    		con.writeln("ToDo List");
			con.writeln("============\n");
			con.writeln("1. Insert Task");
			con.writeln("2. View Task");
			con.writeln("3. Complete Task");
			con.writeln("4. Exit");
    		do{
				con.write(">> ");
				menu = con.readInt();
        	}while(menu<1 || menu>4);
    		switch(menu){
	    		case 1 : addTask();
	    			break;
	    		case 2 : viewTask();
	    			break;
	    		case 3 : completeTask();
    				break;
    		}
    	}while(menu!=4 );
    	
    	clear();
    	System.out.println("Your Application Has Been Running For : " + waktu/1000 + " second(s)");
    	System.out.println("Thankyou for using this application!");
    	exportTask();
	}

}
