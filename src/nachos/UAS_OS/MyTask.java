package nachos.UAS_OS;

public class MyTask {
	private String taskName;
	private String taskDescription;
	private String taskType;
	private String taskStatus;
	
	public MyTask(String taskName, String taskDescription, String taskType, String taskStatus) {
		// TODO Auto-generated constructor stub
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskType = taskType;
		this.taskStatus= taskStatus;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus= taskStatus;
	}
}
