package Project_Constructor;

import javafx.beans.property.SimpleStringProperty;

public class ReservationProperty {
	private SimpleStringProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty goods;
	private SimpleStringProperty price;
	private SimpleStringProperty count;
	private SimpleStringProperty no;
	private SimpleStringProperty rsrvtStartDateTime;
	private SimpleStringProperty rsrvtEndsDateTime;
	private SimpleStringProperty rmnngMinutes;

	public ReservationProperty() {

	}

	public ReservationProperty(int no, String id, String name, String goods, int count, String rsrvtStartDateTime,
			String rsrvtEndsDateTime, int price,int rmnngMinutes) {
		this.no = new SimpleStringProperty(String.valueOf(no));
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.goods = new SimpleStringProperty(goods);
		this.count = new SimpleStringProperty(String.valueOf(count));
		this.rsrvtStartDateTime = new SimpleStringProperty(rsrvtStartDateTime);
		this.rsrvtEndsDateTime = new SimpleStringProperty(rsrvtEndsDateTime);
		this.rmnngMinutes = new SimpleStringProperty(String.valueOf(rmnngMinutes));
		this.price = new SimpleStringProperty(String.valueOf(price));
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	
	public String getGoods(){
		return goods.get();
	}

	public void setGoods(String goods) {
		this.goods.set(goods);
	}
	public String getCount(){
		return count.get();
	}

	public void setCount(int count) {
		this.count.set(String.valueOf(count));
	}

	public String getNo() {
		return no.get();
	}

	public void setNo(int no) {
		this.no.set(String.valueOf(no));
	}

	public String getRsrvtStartDateTime() {
		return rsrvtStartDateTime.get();
	}

	public void setRsrvtStartDateTime(String rsrvtStartTime) {
		this.rsrvtStartDateTime.set(rsrvtStartTime);
	}

	public String getRsrvtEndsDateTime() {
		return rsrvtEndsDateTime.get();
	}

	public void setRsrvtEndsDateTime(String rsrvtEndsTime) {
		this.rsrvtEndsDateTime.set(rsrvtEndsTime);
	}

	public String getRmnngMinutes() {
		return rmnngMinutes.get();
	}

	public void setRmnngMinutes(int rmnngMinutes) {
		this.rmnngMinutes.set(String.valueOf(rmnngMinutes));
	}

	public String getPrice() {
		return price.get();
	}

	public void setPrice(int price) {
		this.price.set(String.valueOf(price));
	}

	@Override
	public String toString() {
		return "ReservationProperty [id=" + id + ", name=" + name + ", goods=" + goods + ", price=" + price + ", count="
				+ count + ", no=" + no + ", rsrvtStartTime=" + rsrvtStartDateTime + ", rsrvtEndsTime=" + rsrvtEndsDateTime
				+ ", rmnngMinutes=" + rmnngMinutes + "]";
	}


}
