package Project_Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import Project_Constructor.Menu;
import Project_Constructor.Rsrvt;
import Project_Constructor.RsrvtDateTime;
import Project_Constructor.Seat;
import Project_DAO.MenuDao;
import Project_DAO.RsrvtDao;
import Project_DAO.RsrvtDateTimeDao;
import Project_DAO.SeatDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

public class OrderController implements Initializable {
	@FXML
	private TableColumn<Menu, String> menunameTable;
	@FXML
	private TableColumn<Menu, String> priceTable;
	@FXML
	private TableColumn<Menu, String> ordernameTable;
	@FXML
	private TableColumn<Menu, String> ordernumTable;
	@FXML
	private TableColumn<Menu, String> orderpriceTable;
	@FXML
	private TableColumn<Menu, String> orderquantityTable;
	@FXML
	private ImageView menuimage;
	@FXML
	private TableView<Menu> menulist;
	@FXML
	private TableView<Menu> orderlist;
	@FXML
	private Button addBtn;
	@FXML
	private Button cancleBtn;
	@FXML
	private Button payBtn;
	@FXML
	private Button backBtn;
	@FXML
	public Label total;
	@FXML
	private Label total2;
	@FXML
	private Label rsrvtTime; // ���ð�
	@FXML
	private Label rsrvtEndsTime; // ��������ð�
	@FXML
	private ChoiceBox<String> selectTime;
	@FXML
	private Label selectedTableLabel;
	@FXML
	private Label selectedMenuLabel;
	@FXML
	private Label exceptionLabel;
	@FXML
	private Button tableReserveBtn;
	@FXML
	private Button reserveListBtn;
	@FXML
	private Button userUpdate;

	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	MenuDao menuDao = new MenuDao();
	RsrvtDao reservationDao = new RsrvtDao();

	Menu menu;
	static ObservableList<Menu> orderList = FXCollections.observableArrayList();
	ObservableList<String> selectedTime = FXCollections.observableArrayList();
	/* tab_coffe, tab_food, tab_drink */
	@FXML
	private Tab tab_coffe;
	@FXML
	private Tab tab_food;
	@FXML
	private Tab tab_drink;
	// tab_food's children
	@FXML
	ImageView menuimage2;
	@FXML
	private TableColumn<Menu, String> menunameTable2;
	@FXML
	private TableColumn<Menu, String> priceTable2;
	@FXML
	private TableView<Menu> menulist2;

	// tab_drink's children
	@FXML
	ImageView menuimage3;
	@FXML
	private TableColumn<Menu, String> menunameTable3;
	@FXML
	private TableColumn<Menu, String> priceTable3;
	@FXML
	private TableView<Menu> menulist3;
	/* ��Ÿ */
	ObservableList<Menu> temp = FXCollections.observableArrayList();
	private int pymntAmnt; // �����ݾ�
	LocalDateTime rsrvtTimeInfo; // ������ ��¥,�ð�����
	LocalDateTime rsrvtTimeEndInfo; // ������ ���� ����Ǵ� �ð�
	LocalDateTime rsrvtInfo; //// ����ð��� ��,�� ������ �������
	SeatDao seatDao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.seatDao = new SeatDao();
		selectTime.setStyle("-fx-alignment : CENTER"); // ����ð� ��� ����
		selectedTime.clear(); // ����ð� �ʱ�ȭ
		selectTime.setItems(selectedTime); // ����ð�(���̽��ڽ�)�� ����ð� �߰�
		// ����ð� 08~23 �߰�
		for (int i = 0; i < TableController.rsrvtDateTimeList.size(); i++) {
			if (i == TableController.rsrvtDateTimeList.size() - 1) // 23:00�� ������������.
				continue;
			selectedTime.add(TableController.rsrvtDateTimeList.get(i).getDateTime().substring(11, 16));
		}
		orderlist.setItems(temp); // �ֹ� ��Ʈ�ѷ��� �ֹ����� �߰�
		// �¼��� ����� �ð��� ����ð����� �����Ѵ�.

		removeOvrlpTime();
		/* Ŀ���� */
		tab_coffe.setOnSelectionChanged(event -> {
			menunameTable.setCellValueFactory(cellData -> cellData.getValue().getGoodsProperty());
			priceTable.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
			priceTable.setStyle("-fx-alignment : CENTER");
			menulist.setItems(menuDao.selectAllCoffe());

			menulist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Menu>() {
				@Override
				public void changed(ObservableValue<? extends Menu> observable, Menu oldValue, Menu newValue) {
					if (newValue != null) {
						URL imageURL = getClass().getResource("../images/" + newValue.getGoods() + ".jpg");
						boolean haveImage = havingImage(imageURL);
						if (haveImage) {
							menuimage.setImage(new Image(imageURL.toString()));
						}

					}

				}

			});

		});

		/* end of tab_coffe */

		/* tab_food */
		tab_food.setOnSelectionChanged(event -> {

			menunameTable2.setCellValueFactory(cellData -> cellData.getValue().getGoodsProperty());
			priceTable2.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
			priceTable2.setStyle("-fx-alignment : CENTER");
			menulist2.setItems(menuDao.selectAllFood());

			menulist2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Menu>() {

				@Override
				public void changed(ObservableValue<? extends Menu> observable, Menu oldValue, Menu newValue) {
					if (newValue != null) {
						URL imageURL = getClass().getResource("../images/" + newValue.getGoods() + ".jpg");
						boolean haveImage = havingImage(imageURL);
						if (haveImage) {
							menuimage2.setImage(new Image(imageURL.toString()));
						}

					}

				}
			});
		});

		/* end of tab_food */

		/* tab_drink */
		tab_drink.setOnSelectionChanged(event -> {

			menunameTable3.setCellValueFactory(cellData -> cellData.getValue().getGoodsProperty());
			priceTable3.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
			priceTable3.setStyle("-fx-alignment : CENTER");
			menulist3.setItems(menuDao.selectAllDrink());

			menulist3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Menu>() {
				@Override
				public void changed(ObservableValue<? extends Menu> observable, Menu oldValue, Menu newValue) {
					if (newValue != null) {
						URL imageURL = getClass().getResource("../images/" + newValue.getGoods() + ".jpg");
						boolean havingImage = havingImage(imageURL);
						if (havingImage) {
							menuimage3.setImage(new Image(imageURL.toString()));
						}
					}

				}
			});
		});

		/* end of tab_drink */
		addBtn.setOnAction(event ->

		{
			ordernameTable.setCellValueFactory(cellData -> cellData.getValue().getGoodsProperty());
			orderpriceTable.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
			orderpriceTable.setStyle("-fx-alignment : CENTER");
			orderquantityTable.setCellValueFactory(cellData -> cellData.getValue().getCountProperty());
			orderquantityTable.setStyle("-fx-alignment : CENTER");

			addOrder(event);
			setInfo();

		});

		cancleBtn.setOnAction(event -> {
			ordernameTable.setCellValueFactory(cellData -> cellData.getValue().getGoodsProperty());
			orderpriceTable.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
			orderpriceTable.setStyle("-fx-alignment : CENTER");
			orderquantityTable.setCellValueFactory(cellData -> cellData.getValue().getCountProperty());
			delOrder(event);
			setInfo();
		});

		/* �����ϱ� �̺�Ʈ */
		payBtn.setOnAction(event -> {
			pay(event);
			removeOvrlpTime();
		});
	}

	ObservableList<Menu> orderMenuListTemp = FXCollections.observableArrayList();
	ObservableList<Menu> orderMenuList = FXCollections.observableArrayList();
	int count = 0;
	int price = 0;

	public void addOrder(ActionEvent e) {

		orderMenuList.clear();
		if (menulist.getSelectionModel().getSelectedItem() != null) {
			System.out.println(menulist.getSelectionModel().getSelectedItem().getGoods());
			orderMenuListTemp.add(menulist.getSelectionModel().getSelectedItem());
		} else if (menulist2.getSelectionModel().getSelectedItem() != null) {
			System.out.println(menulist2.getSelectionModel().getSelectedItem().getGoods());
			orderMenuListTemp.add(menulist2.getSelectionModel().getSelectedItem());
		} else if (menulist3.getSelectionModel().getSelectedItem() != null) {
			System.out.println(menulist3.getSelectionModel().getSelectedItem().getGoods());
			orderMenuListTemp.add(menulist3.getSelectionModel().getSelectedItem());
		}

		// Ŀ�� �߰� -> orderMenulist
		for (int i = 0; i < menuDao.selectAllCoffe().size(); i++) {
			for (int j = 0; j < orderMenuListTemp.size(); j++) {
				if (menuDao.selectAllCoffe().get(i).getGoods().equals(orderMenuListTemp.get(j).getGoods())) {
					count++;
					price += Integer.valueOf(orderMenuListTemp.get(j).getPrice());
				}
			}
			if (count != 0) {
				orderMenuList.add(new Menu(menuDao.selectAllCoffe().get(i).getGoods(), price + "", count + ""));
				count = 0;

				price = 0;

			}
		}

		// Ǫ�� �߰� -> orderMenulist
		for (int i = 0; i < menuDao.selectAllFood().size(); i++) {
			for (int j = 0; j < orderMenuListTemp.size(); j++) {

				if (menuDao.selectAllFood().get(i).getGoods().equals(orderMenuListTemp.get(j).getGoods())) {
					count++;
					price += Integer.valueOf(orderMenuListTemp.get(j).getPrice());

				}
			}
			if (count != 0) {

				orderMenuList
						.add(new Menu(menuDao.selectAllFood().get(i).getGoods(), price + "", String.valueOf(count)));
				count = 0;
				price = 0;
			}
		}
		// �帵ũ �߰� -> orderMenuList
		for (int i = 0; i < menuDao.selectAllDrink().size(); i++) {
			for (int j = 0; j < orderMenuListTemp.size(); j++) {
				if (menuDao.selectAllDrink().get(i).getGoods().equals(orderMenuListTemp.get(j).getGoods())) {
					count++;
					price += Integer.valueOf(orderMenuListTemp.get(j).getPrice());
				}
			}
			if (count != 0) {
				orderMenuList
						.add(new Menu(menuDao.selectAllDrink().get(i).getGoods(), price + "", String.valueOf(count)));
				count = 0;
				price = 0;
			}
		}
		System.out.println("*****addOrder()");
		System.out.println(orderMenuListTemp);
		System.out.println(orderMenuList);
		orderlist.setItems(orderMenuList);

	}

	public void delOrder(ActionEvent e) {
		orderMenuList.clear();
		if (menulist.getSelectionModel().getSelectedItem() != null) {
			System.out.println(menulist.getSelectionModel().getSelectedItem().getGoods());
			orderMenuListTemp.remove(menulist.getSelectionModel().getSelectedItem());
		} else if (menulist2.getSelectionModel().getSelectedItem() != null) {
			System.out.println(menulist2.getSelectionModel().getSelectedItem().getGoods());
			orderMenuListTemp.remove(menulist2.getSelectionModel().getSelectedItem());
		} else if (menulist3.getSelectionModel().getSelectedItem() != null) {
			System.out.println(menulist3.getSelectionModel().getSelectedItem().getGoods());
			orderMenuListTemp.remove(menulist3.getSelectionModel().getSelectedItem());
		}

		for (int i = 0; i < menuDao.selectAllCoffe().size(); i++) {
			for (int j = 0; j < orderMenuListTemp.size(); j++) {
				if (menuDao.selectAllCoffe().get(i).getGoods().equals(orderMenuListTemp.get(j).getGoods())) {
					count++;
					price += Integer.valueOf(orderMenuListTemp.get(j).getPrice());
				}
			}
			if (count != 0) {
				orderMenuList
						.add(new Menu(menuDao.selectAllCoffe().get(i).getGoods(), price + "", String.valueOf(count)));
				count = 0;
				price = 0;
			}
		}

		for (int i = 0; i < menuDao.selectAllFood().size(); i++) {
			for (int j = 0; j < orderMenuListTemp.size(); j++) {
				if (menuDao.selectAllFood().get(i).getGoods().equals(orderMenuListTemp.get(j).getGoods())) {
					count++;
					price += Integer.valueOf(orderMenuListTemp.get(j).getPrice());
				}
			}
			if (count != 0) {
				orderMenuList
						.add(new Menu(menuDao.selectAllFood().get(i).getGoods(), price + "", String.valueOf(count)));
				count = 0;
				price = 0;
			}
		}

		for (int i = 0; i < menuDao.selectAllDrink().size(); i++) {
			for (int j = 0; j < orderMenuListTemp.size(); j++) {
				if (menuDao.selectAllDrink().get(i).getGoods().equals(orderMenuListTemp.get(j).getGoods())) {
					count++;
					price += Integer.valueOf(orderMenuListTemp.get(j).getPrice());
				}
			}
			if (count != 0) {
				orderMenuList
						.add(new Menu(menuDao.selectAllDrink().get(i).getGoods(), price + "", String.valueOf(count)));
				count = 0;
				price = 0;
			}
		}

		orderlist.setItems(orderMenuList);

	}

	// �����ϱ�
	public void pay(ActionEvent e) {
		// ����ð��� �����������...
		if (!selectdRsrvtTime()) {
			alert.setTitle("����ð�����");
			alert.setHeaderText("����ð��� �������ּ���.");
			alert.show();
		} else {
			// �ֹ� ���̺�信 ���õ� ��ü�� ��
			int size = orderMenuList.size();
			try {
				for (int i = 0; i < size; i++) {
					// rsrvt ���̺�� �����͸� ���� �غ�
					Rsrvt newRsrvt = new Rsrvt();
					newRsrvt.setId(LoginController.member.getId());
					newRsrvt.setName(LoginController.member.getName());
					newRsrvt.setGoods(orderMenuList.get(i).getGoods());
					newRsrvt.setPrice(Integer.valueOf(orderMenuList.get(i).getPrice()));
					newRsrvt.setCount(Integer.valueOf(orderMenuList.get(i).getCount()));
					newRsrvt.setNo(TableController.selectedTableNo);
					newRsrvt.setRmnngMinutes(Integer.valueOf(rsrvtTime.getText().split("��")[0]));
					String rsrvtStartDateTime = "";
					String rsrvtEndsDateTime = "";

					// 01:00 ������ -> 2020-01-01 01:00:00:000 ������ rsrvtDateTimeList�� ������ �޾� �����͸� ������.
					for (int j = 0; j < TableController.rsrvtDateTimeList.size(); j++) {
						// ����ð� 23��
						if (rsrvtEndsTime.getText().equals("23:00") && TableController.rsrvtDateTimeList.get(j)
								.getDateTime().indexOf(selectTime.getValue()) != -1) {
							rsrvtEndsDateTime = TableController.rsrvtDateTimeList
									.get(TableController.rsrvtDateTimeList.size() - 1).getDateTime();
							rsrvtStartDateTime = TableController.rsrvtDateTimeList.get(j).getDateTime();
							// ������۽ð�
						} else {
							if (TableController.rsrvtDateTimeList.get(j).getDateTime()
									.indexOf(selectTime.getValue()) != -1) {
								System.out.println("������۽ð�");
								rsrvtStartDateTime = TableController.rsrvtDateTimeList.get(j).getDateTime();
								System.out.println(rsrvtStartDateTime);
							}
							// ��������ð�
							else if (TableController.rsrvtDateTimeList.get(j).getDateTime()
									.indexOf(rsrvtEndsTime.getText()) != -1)
								rsrvtEndsDateTime = TableController.rsrvtDateTimeList.get(j).getDateTime();
						}
					}
					// ���� ������ �� ����
					System.out.println("���� ������ �� ����");
					newRsrvt.setRsrvtStartDateTime(rsrvtStartDateTime);
					newRsrvt.setRsrvtEndsDateTime(rsrvtEndsDateTime);

					// ������ ������
					System.out.println("seat ������ ������");
					seatDao.insertSeat(new Seat(newRsrvt.getNo(), newRsrvt.getRsrvtStartDateTime(),
							newRsrvt.getRsrvtEndsDateTime(), newRsrvt.getRmnngMinutes()));
					
					// cafeReservationDB -> rsrvt ������ ����
					reservationDao.insertRsrvt(newRsrvt);
					
					// ������� ����
					
					int idntfNmbr=menuDao.getIdntfNmbr(newRsrvt.getGoods());
					int count=menuDao.getCount(idntfNmbr);
					String result=menuDao.updateCount(idntfNmbr, count-newRsrvt.getCount());
					// ������� ���� �������� Ȯ��
					System.out.println(result);
					// DB�� �����Ͱ� ���޵Ǿ����� Ȯ��
					System.out.println("DB�� �����Ͱ� ���޵Ǿ����� Ȯ��");
					System.out.println("DB�����޵� ������ ����");
					System.out.println(seatDao.selectOne(new Seat(newRsrvt.getNo(), newRsrvt.getRsrvtStartDateTime(),
							newRsrvt.getRsrvtEndsDateTime(), newRsrvt.getRmnngMinutes())).toString());
				}
				// ������ ���� �� ������ �ݱ�
				((Stage) rsrvtTime.getScene().getWindow()).close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	// �ڷΰ���
	public void back(ActionEvent e) {
		orderList.clear();
		Stage root = (Stage) backBtn.getScene().getWindow();
		try {
			Parent root1 = FXMLLoader.load(getClass().getResource("../project_fxml/member.fxml"));
			Scene scene = new Scene(root1);
			root.setScene(scene);
			root.setResizable(false);
			root.show();
			root.setTitle("����� ���");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	// �̹��� �ִ��� Ȯ��
	public boolean havingImage(URL imageURL) {
		if (imageURL == null)
			return false;
		return true;

	}

	// �����ݾ׿� ����ؼ� �̿�ð� ���ϱ�
	public int saveTimeUse(int pymntAmnt) {
		return pymntAmnt / 50; // �̿�ð� : ���� 10��
	}

	// ����ð� ���� �˻�
	public boolean selectdRsrvtTime() {
		if (selectTime.getSelectionModel().getSelectedItem() != null) {
			return true;
		}
		return false;
	}

	// ���������� ����ϰ� �ð� ����
	public void setInfo() {

		cancleBtn.setDisable(false);
		pymntAmnt = 0;
		for (int i = 0; i < orderMenuList.size(); i++) {
			pymntAmnt += Integer.valueOf(orderMenuList.get(i).getPrice());
		}
		total.setText(String.valueOf(pymntAmnt));
		rsrvtTime.setText(String.valueOf(saveTimeUse(pymntAmnt)));
		int rsrvtStartTime = formatTime(selectTime.getValue());
		int rsrvtEndsTime = rsrvtStartTime + Integer.valueOf(rsrvtTime.getText()) / 60 * 100;
		this.rsrvtEndsTime.setText(this.formatStringTime(rsrvtEndsTime));

		// ����ð��� selectTime�� ���ٸ�=�ٸ����� ��������ѽð�
		// ��������ð� > 2300
		if (rsrvtEndsTime > formatTime(TableController.rsrvtDateTimeList
				.get(TableController.rsrvtDateTimeList.size() - 1).getDateTime().substring(11, 16))) {
			alert.setHeaderText("����Ұ�");
			alert.showAndWait();
			addBtn.setDisable(true);

		} else {
			if (orderMenuList.size() == 0) {
				cancleBtn.setDisable(true);
				reset();
			} else
				cancleBtn.setDisable(false);
			addBtn.setDisable(false);
			boolean isBoolean = false;
			for (int i = 0; i < selectedTime.size(); i++) {
				// ����ð��� ����ð� ����Ʈ�� �ִٸ�
				if (selectedTime.get(i).indexOf(this.rsrvtEndsTime.getText()) != -1) {
					isBoolean = true;
					break;
				} else
					isBoolean = false;
			}
			if (!isBoolean) {
				System.out.println("isBoolean");
				alert.setHeaderText(this.rsrvtEndsTime.getText() + "���� �̿밡��");
				alert.showAndWait();
				addBtn.setDisable(true);
				cancleBtn.setDisable(false);
			} else if (orderMenuList.size() == 0) {
				cancleBtn.setDisable(true);
				reset();
				return;
			} else
				cancleBtn.setDisable(false);

		}

	}

	public String formatSimpleTime(String timeValue) {
		return timeValue.split(" ")[1].split(":")[0] + timeValue.split(" ")[1].split(":")[1];
	}

	public int formatSimpleDate(String dateValue) {
		return Integer.valueOf(dateValue.split(" ")[0].split("-")[0] + dateValue.split(" ")[0].split("-")[1]
				+ dateValue.split(" ")[0].split("-")[2]);

	}

	public String formatDate(String dateValue) {
		return dateValue.split("-")[0] + dateValue.split("-")[1] + dateValue.split("-")[2];
	}

	public int formatTime(String timeValue) {
		if (timeValue.length() == 5) {
			return Integer.valueOf(timeValue.split(":")[0] + timeValue.split(":")[1]);
		}

		else {
			return 0;
		}

	}

	List<RsrvtDateTime> list = null;
	int listSize = 0;

	// ����� �ð��� �����ϱ�
	public void removeOvrlpTime() {
		try {
			// �¼��� ��� ���೻�� ���� �����´�.
			// ������ ������ �� ��ŭ �ݺ����� �����Ѵ�.
			// ������۽ð�,��������ð��� ���������� ��ȯ�Ѵ�.
			// ������۽ð��� ��������ð��� �ִ� �ð��븦 �����Ѵ�.
			RsrvtDateTimeDao rsrvtDateTimeDao = new RsrvtDateTimeDao();
			list = rsrvtDateTimeDao.slectAll();
			listSize = list.size();
			ObservableList<Seat> seatList = FXCollections.observableArrayList();
			// seatDao �Ű������� ���޵� ���̺��ȣ�� ����� ���մ��� ���೻���� �����´�.
			// ������������Ʈ�� �������ڰ� �ƴ϶�� �ݺ����� �ǳʶ��.

			for (int i = 0; i < seatDao.selectOne(TableController.selectedTableNo).size(); i++) {
				// ���೻�� �� ���������Ǻ�.
				if (isItToday(seatDao.selectOne(TableController.selectedTableNo).get(i).getRsrvtStartDatetime())
						&& isItToday(
								seatDao.selectOne(TableController.selectedTableNo).get(i).getRsrvtStartDatetime())) {
					seatList.add(seatDao.selectOne(TableController.selectedTableNo).get(i));
				} else // ������ �ƴ϶��... �ǳʶ���
					continue;
			}

			for (int j = 0; j < seatList.size(); j++) {
				// ������ �������� ������۽ð��� ��������ð��� ����Ÿ�������κ��� start,end�� �޴´�.
				int start = Integer.valueOf(formatSimpleTime(seatList.get(j).getRsrvtStartDatetime()));
				int end = Integer.valueOf(formatSimpleTime(seatList.get(j).getRsrvtEndsDatetime()));
				
				// �ð��� �� ��ŭ �ݺ�
				System.out.println("start/100 : " + start / 100);
				System.out.println("end/100 : " + end / 100);
				// �������� ��(hour) - �������� ����(hour) �� ����ŭ �ݺ��Ѵ�.
				for (int k = start / 100; k < end / 100; k++) {
					// �޴�â�� ���̴� �ð��뿡 �� ��ŭ �ݺ��� �ǽ��Ѵ�.
					for (int j2 = 0; j2 < this.selectedTime.size(); j2++) {
						int delVal = formatTime(this.selectedTime.get(j2));
						if (start <= delVal && delVal < end) {
							this.selectedTime.remove(j2);
							System.out.println("������ �� : " + this.selectedTime.get(j2));
						}
					}
				}
			}
		} catch (Exception e) {
		}
	}

	public String formatStringTime(int time) {
		if (String.valueOf(time).length() == 4)
			return String.valueOf(time).substring(0, 2) + ":" + String.valueOf(time).substring(2, 4);
		return "0" + String.valueOf(time).substring(0, 1) + ":" + String.valueOf(time).substring(1, 3);

	}

	public boolean isItToday(String date) {
		if (formatSimpleDate(date.toString()) == Integer
				.valueOf(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)))
			return true;
		return false;
	}

	public void reset() {
		rsrvtTime.setText("");
		rsrvtEndsTime.setText("");
		total.setText("");
	}
	

}
