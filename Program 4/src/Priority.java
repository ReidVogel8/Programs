
public class Priority implements Comparable<Priority> {

	private String word;
	private int edit;
	
	public Priority(String word, int edit) {
		this.word = word;
		this.edit = edit;
	}

	public String getWord() {
		return this.word;
	}
	
	public int getEdit() {
		return this.edit;
	}

	@Override
	public int compareTo(Priority o) {
		int num = Integer.valueOf(edit).compareTo(o.getEdit());
		return num;
	}
	
}
