package Project_Constructor;

public class RsrvtDateTime {
	String dateTime;
	//	08:00~23:00
	
	public RsrvtDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public RsrvtDateTime() {
		
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	@Override
	public String toString() {
		return "rsrvtDateTime [dateTime=" + dateTime + "]";
	}
	
}
