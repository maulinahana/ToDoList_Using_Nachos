package nachos.UAS_OS;

import nachos.threads.Scheduler;
import nachos.threads.ThreadQueue;

public class MyScheduler extends Scheduler{

	@Override
	public ThreadQueue newThreadQueue(boolean transferPriority) {
		// TODO Auto-generated method stub
		return new MyQueue();
	}

}
