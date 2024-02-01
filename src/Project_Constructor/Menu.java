package Project_Constructor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Menu {
	private SimpleStringProperty idntfNmbr ;
	private SimpleStringProperty goods;
    private SimpleStringProperty price;
    private SimpleStringProperty count;
	
    public Menu(String goods, String price,
			String count) {
    	this.goods = new SimpleStringProperty(goods);
		this.price = new SimpleStringProperty(price);
		this.count = new SimpleStringProperty(count);
	}
    

	public Menu(String idntfNmbr, String goods, String price,
			String count) {
		this.idntfNmbr = new SimpleStringProperty(idntfNmbr);
		this.goods = new SimpleStringProperty(goods);
		this.price = new SimpleStringProperty(price);
		this.count = new SimpleStringProperty(count);
	}
	public Menu(String goods) {
		this.goods = new SimpleStringProperty(goods);
	}
	public StringProperty getGoodsProperty() {
    	return goods;
    }
	
	public StringProperty getPriceProperty() {
    	return price;
    }
	
	public StringProperty getCountProperty() {
    	return count;
    }
	
	public StringProperty getIdntfNmbrProperty() {
    	return idntfNmbr;
    }

	public String getGoods() {
		return goods.get();
	}
	
	public String getCount() {
		return count.get();
	}

	public void setGoods(String goods) {
		this.goods.set(goods);
	}

	public String getPrice() {
		return price.get();
	}

	public void setPrice(String price) {
		this.price.set(price);
	}
	public String getIdntfNmbr() {
		return idntfNmbr.get();
	}
	public void setIdntfNmbr(String idntfNmbr) {
		this.idntfNmbr.set(idntfNmbr);
	}


	public void setCount(String count) {
		this.count.set(count);
	}


	@Override
	public String toString() {
		return "Menu [idntfNmbr=" + idntfNmbr + ", goods=" + goods + ", price=" + price + ", count=" + count + "]";
	}

	
}