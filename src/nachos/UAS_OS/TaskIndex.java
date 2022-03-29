package nachos.UAS_OS;

import java.util.Vector;

public class TaskIndex implements Runnable{
	int index;
	Vector<MyTask> listTask;

	public TaskIndex(int index) {
		// TODO Auto-generated constructor stub
		this.index = index;
		listTask = new Vector<MyTask>();
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public Vector<MyTask> getListTask() {
		return listTask;
	}

	public void addlistTask(MyTask task) {
		listTask.add(task);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
