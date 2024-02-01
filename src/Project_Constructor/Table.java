package Project_Constructor;


public class Table {
	private int no;
	private String day;
	public Table() {
	}
	public Table(int no, String day) {
		super();
		this.no = no;
		this.day = day;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}



	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "Table [no=" + no + ", day=" +day + "]";
	}

}
