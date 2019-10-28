package implementation;

public class Process {

	private int id, pagesQt;
	private String status;
	private PageTable pageTable;
	private int waitingTimer;

	Process(int id, int size, int pageSize){
		waitingTimer = 0;
		this.id = id;
		status = "new";
		float div = (1.0f * size) / pageSize;
		pagesQt = (int) Math.ceil(div);
		pageTable = new PageTable(pagesQt, id);
	}

	// Getters

	public int getId() {
		return id;
	}

	public int getPagesQt() {
		return pagesQt;
	}

	public PageTable getPageTable(){ return pageTable; }

	public String getStatus() {
		return status;
	}

	public int getTimeBlocked(){return waitingTimer;}

	// Setters

	void setStatus(String status){	// Only the OS should be able to do it.
		this.status = status;
	}

	void setWaitingTimer(int time){ waitingTimer = time; }

	// Others

	boolean isBlocked(){
		return status.equals("blocked") || status.equals("blocked-suspend");
	}

	void increaseWaitingTimer(){ waitingTimer++; }

	void resetWaitingTimer(){ waitingTimer = 0; }
}