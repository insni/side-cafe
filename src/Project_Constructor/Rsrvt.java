package Project_Constructor;

public class Rsrvt {
	private String id;
	private String name;
	private String goods;
	private int price;
	private int count;
	private int no;
	private String rsrvtStartDateTime;
	private String rsrvtEndsDateTime;
	private int rmnngMinutes;
	
	public Rsrvt() {
	
	}

	public Rsrvt(String id, String name, String goods, int price, int count, int no, String rsrvtStartDateTime,
			String rsrvtEndsDateTime, int rmnngMinutes) {
		this.id = id;
		this.name = name;
		this.goods = goods;
		this.price = price;
		this.count = count;
		this.no = no;
		this.rsrvtStartDateTime = rsrvtStartDateTime;
		this.rsrvtEndsDateTime = rsrvtEndsDateTime;
		this.rmnngMinutes = rmnngMinutes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getRsrvtStartDateTime() {
		return rsrvtStartDateTime;
	}

	public void setRsrvtStartDateTime(String rsrvtStartDateTime) {
		this.rsrvtStartDateTime = rsrvtStartDateTime;
	}

	public String getRsrvtEndsDateTime() {
		return rsrvtEndsDateTime;
	}

	public void setRsrvtEndsDateTime(String rsrvtEndsDateTime) {
		this.rsrvtEndsDateTime = rsrvtEndsDateTime;
	}

	public int getRmnngMinutes() {
		return rmnngMinutes;
	}

	public void setRmnngMinutes(int rmnngMinutes) {
		this.rmnngMinutes = rmnngMinutes;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", name=" + name + ", goods=" + goods + ", price=" + price + ", count=" + count
				+ ", no=" + no + ", rsrvtStartDateTime=" + rsrvtStartDateTime + ", rsrvtEndsDateTime=" + rsrvtEndsDateTime
				+ ", rmnngMinutes=" + rmnngMinutes + "]";
	}


	
}