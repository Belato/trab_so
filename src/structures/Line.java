package structures;

public class Line {
    private int LineNumber;
    private int used;
    
    public Line(int lineNumber, int used) {
		super();
		LineNumber = lineNumber;
		this.used = used;
	}
	public int getLineNumber() {
		return LineNumber;
	}
	public void setLineNumber(int lineNumber) {
		LineNumber = lineNumber;
	}
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}
	
}
