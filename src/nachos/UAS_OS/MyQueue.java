package nachos.UAS_OS;

import java.util.Vector;
import nachos.threads.KThread;
import nachos.threads.ThreadQueue;

public class MyQueue extends ThreadQueue{
	private Vector<KThread> queue = new Vector<KThread>();

	public MyQueue() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void waitForAccess(KThread thread) {
		// TODO Auto-generated method stub
		queue.add(thread);
	}

	@Override
	public KThread nextThread() {
		// TODO Auto-generated method stub
		if (queue.size()>0)
			return queue.remove(0);
		return null;
	}

	@Override
	public void acquire(KThread thread) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

}
