package Project_Constructor;


public class Seat {
	private int no;
    private String rsrvtStartDatetime;
    private String rsrvtEndsDatetime;
    private int rmnngMinutes;
	
    public Seat(int no, String rsrvtStartDatetime, String rsrvtEndsDatetime, int rmnngMinutes) {
    	this.no = no;
		this.rsrvtStartDatetime = rsrvtStartDatetime;
		this.rsrvtEndsDatetime = rsrvtEndsDatetime;
		this.rmnngMinutes = rmnngMinutes;
	}
	public Seat() {
	
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getRsrvtStartDatetime() {
		return rsrvtStartDatetime;
	}
	public void setRsrvtStartDatetime(String rsrvtStartDatetime) {
		this.rsrvtStartDatetime = rsrvtStartDatetime;
	}
	public String getRsrvtEndsDatetime() {
		return rsrvtEndsDatetime;
	}
	public void setRsrvtEndsDatetime(String rsrvtEndsDatetime) {
		this.rsrvtEndsDatetime = rsrvtEndsDatetime;
	}
	public int getRmnngMinutes() {
		return rmnngMinutes;
	}
	public void setRmnngMinutes(int rmnngMinutes) {
		this.rmnngMinutes = rmnngMinutes;
	}
	@Override
	public String toString() {
		return "Seat [no=" + no + ", rsrvtStartDatetime=" + rsrvtStartDatetime + ", rsrvtEndsDatetime="
				+ rsrvtEndsDatetime + ", rmnngMinutes=" + rmnngMinutes + "]";
	}
	
    
}
