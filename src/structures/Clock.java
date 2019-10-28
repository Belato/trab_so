package structures;

public class Clock {
	private int lastReference;

	private Line[] Lines;

	public Clock(int lastReference, int size) {
		super();
		this.lastReference = lastReference;
		Lines = new Line[size];
	}

	private void used(int lineNumber) {
		Line l = Lines[lineNumber];
		if (l != null) {
			l.setUsed(1);
		}
	}

	private void adc(int lineNumber) {
		Line l = new Line(lineNumber, 1);
		lastReference = lineNumber;
		Lines[lineNumber] = l;
	}

	private int remove() {
		int count = lastReference;
		for (int i = 0; i < Lines.length; i++) {
			Line l;
			if (count + i < Lines.length) {
				l = Lines[count + i];
			} else {
				l = Lines[count + i - Lines.length];
			}
			if (l != null) {
				if (l.getUsed() == 0) {
					int j = l.getLineNumber();
					Lines[j] = null;
					return j;
				}
                else{
                    l.setUsed(0);
                }
			}
		}
		return count;
	}

}
