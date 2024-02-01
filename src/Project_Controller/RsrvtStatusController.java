//	���೻��
package Project_Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import Project_Constructor.ReservationProperty;
import Project_Constructor.Rsrvt;
import Project_Constructor.RsrvtDateTime;
import Project_DAO.RsrvtDao;
import Project_DAO.RsrvtDateTimeDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class RsrvtStatusController implements Initializable {
	@FXML
	private AnchorPane anchorPane; // ��� ��Ʈ�ѷ�
	@FXML
	private TableView<ReservationProperty> tableChecker; // ���̺� ��Ʈ�ѷ�
	@FXML
	private TextField idInput; // ���̵� �Է�ĭ
	@FXML
	private DatePicker datePicker;
	@FXML
	Button btnCheck; // ��ȸ ��ư
	@FXML
	Button btnCheckAll; // ��ü��ȸ ��ư
	private ObservableList<ReservationProperty> observableList; // ���̺� �� �Ű���
	@SuppressWarnings("unused")
	private Rsrvt rsrvt; // ���� ����
	private RsrvtDao rsrvtDao; // ����DB
	private RsrvtDateTimeDao rsrvtDateTimeDao;
	private List<RsrvtDateTime> list;
	private int listSize;
	@FXML
	private Button btnReset; // �ʱ�ȭ ��ư
	private Alert alert;
	private String dataStr;
	/* ���̺� �÷� */

	public RsrvtStatusController() {
		this.observableList = FXCollections.observableArrayList();
		rsrvt = new Rsrvt();
		rsrvtDao = new RsrvtDao();
		rsrvtDateTimeDao = new RsrvtDateTimeDao();
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("");
		list = rsrvtDateTimeDao.slectAll();
		listSize = list.size();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnCheck.setOnAction(event -> check(event));
		btnCheckAll.setOnAction(event -> checkAll(event));
		btnReset.setOnAction(event -> reset(event));
		TableColumn<ReservationProperty, ?> no = tableChecker.getColumns().get(0);
		TableColumn<ReservationProperty, ?> id = tableChecker.getColumns().get(1);
		TableColumn<ReservationProperty, ?> name = tableChecker.getColumns().get(2);
		TableColumn<ReservationProperty, ?> goods = tableChecker.getColumns().get(3);
		TableColumn<ReservationProperty, ?> count = tableChecker.getColumns().get(4);
		TableColumn<ReservationProperty, ?> rsrvtStartDateTime = tableChecker.getColumns().get(5);
		TableColumn<ReservationProperty, ?> rsrvtEndsDateTime = tableChecker.getColumns().get(6);
		TableColumn<ReservationProperty, ?> rmngMinutes = tableChecker.getColumns().get(7);
		TableColumn<ReservationProperty, ?> price = tableChecker.getColumns().get(8);

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		goods.setCellValueFactory(new PropertyValueFactory<>("goods"));
		no.setCellValueFactory(new PropertyValueFactory<>("no"));
		count.setCellValueFactory(new PropertyValueFactory<>("count"));
		rsrvtStartDateTime.setCellValueFactory(new PropertyValueFactory<>("rsrvtStartDateTime"));
		rsrvtEndsDateTime.setCellValueFactory(new PropertyValueFactory<>("rsrvtEndsDateTime"));
		rmngMinutes.setCellValueFactory(new PropertyValueFactory<>("rmnngMinutes"));
		price.setCellValueFactory(new PropertyValueFactory<>("price"));

		datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue,
					LocalDate newValue) {
				dataStr = datePicker.getValue().toString();
				int count = 0; // ī���Ͱ�0�̸� alertâ ���
				observableList.clear();
				List<Rsrvt> rsrvtList = rsrvtDao.slectAll(); // ���೻��
				for (int i = 0; i < rsrvtList.size(); i++) {
					if (rsrvtList.get(i).getRsrvtStartDateTime().indexOf(dataStr) != -1) {
						count += 1;
						observableList.add(new ReservationProperty(rsrvtDao.slectAll().get(i).getNo(),
								rsrvtDao.slectAll().get(i).getId(), rsrvtDao.slectAll().get(i).getName(),
								rsrvtDao.slectAll().get(i).getGoods(), rsrvtDao.slectAll().get(i).getCount(),
								rsrvtDao.slectAll().get(i).getRsrvtStartDateTime().substring(11, 16),
								rsrvtDao.slectAll().get(i).getRsrvtEndsDateTime().substring(11, 16),
								rsrvtDao.slectAll().get(i).getPrice(), rsrvtDao.slectAll().get(i).getRmnngMinutes()));
					}
				}
				if (count == 0) {
					alert.setHeaderText("������ ȸ���� �����ϴ�");
					alert.show();
				}
				tableChecker.setItems(observableList);
			}
		});

	}

	// ���� �ҷ�����
	public void check(ActionEvent actionEvent) {
		try {
			this.observableList.clear();
			if (idInput.getText().trim() == null || idInput.getText().equals("")) {
				alert.setHeaderText("���̵� �Է����ּ���.");
				alert.show();
			} else if (this.rsrvtDao.selectOne(idInput.getText().trim()) == null) {
				alert.setHeaderText("�������� �ʴ� ���̵�.");
				alert.show();
			} else if (!idInput.getText().trim().equals("") || idInput.getText().trim() != null) {
				this.rsrvt = rsrvtDao.selectOne(idInput.getText().trim());

				for (int i = 0; i < rsrvtDao.slectAll().size(); i++) {
					if (this.rsrvtDao.slectAll().get(i).getId().equals(idInput.getText().trim())) {
						this.observableList.add(new ReservationProperty(rsrvtDao.slectAll().get(i).getNo(),
								rsrvtDao.slectAll().get(i).getId(), rsrvtDao.slectAll().get(i).getName(),
								rsrvtDao.slectAll().get(i).getGoods(), rsrvtDao.slectAll().get(i).getCount(),
								rsrvtDao.slectAll().get(i).getRsrvtStartDateTime().substring(11, 16),
								rsrvtDao.slectAll().get(i).getRsrvtEndsDateTime().substring(11, 16),
								rsrvtDao.slectAll().get(i).getPrice(), rsrvtDao.slectAll().get(i).getRmnngMinutes()));
					}
				}
				tableChecker.setItems(this.observableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ��翹�೻�� �ҷ�����
	public void checkAll(ActionEvent actionEvent) {
		this.observableList.clear();
		List<Rsrvt> rsrvtList = rsrvtDao.slectAll(); // ���೻��

		for (int i = 0; i < rsrvtList.size(); i++) { // ���೻���� ����ŭ �ݺ��Ѵ�.
			this.observableList.add(new ReservationProperty(rsrvtDao.slectAll().get(i).getNo(),
					rsrvtDao.slectAll().get(i).getId(), rsrvtDao.slectAll().get(i).getName(),
					rsrvtDao.slectAll().get(i).getGoods(), rsrvtDao.slectAll().get(i).getCount(),
					rsrvtDao.slectAll().get(i).getRsrvtStartDateTime().substring(11, 16),
					rsrvtDao.slectAll().get(i).getRsrvtEndsDateTime().substring(11, 16),
					rsrvtDao.slectAll().get(i).getPrice(), rsrvtDao.slectAll().get(i).getRmnngMinutes()));
		}
		tableChecker.setItems(observableList);
	}
	// �������
	public void cancelRsrvt(ActionEvent actionEvent) {
		if (tableChecker.getSelectionModel().getSelectedItem() == null) {
			System.out.println("���� �׸��� ����");
			System.out.println(tableChecker.getSelectionModel().getSelectedItem());
			alert.setHeaderText("�������� �ʴ� ȸ���Դϴ�.");
			alert.show();
		} else {

			for (int i = 0; i < listSize; i++) {
				if (isItToday(list.get(i).getDateTime())) {
					if (list.get(i).getDateTime().indexOf(
							tableChecker.getSelectionModel().getSelectedItem().getRsrvtStartDateTime()) != -1) {
						tableChecker.getSelectionModel().getSelectedItem()
								.setRsrvtStartDateTime(list.get(i).getDateTime());
						continue;
					}
					if (isItToday(list.get(i).getDateTime())) {
						if (list.get(i).getDateTime().indexOf(
								tableChecker.getSelectionModel().getSelectedItem().getRsrvtEndsDateTime()) != -1) {
							tableChecker.getSelectionModel().getSelectedItem()
									.setRsrvtEndsDateTime(list.get(i).getDateTime());
						}
					}
				}
			}
			rsrvtDao.cancel(tableChecker.getSelectionModel().getSelectedItem());
			alert.setHeaderText("��ҿϷ�");
			alert.show();
			System.out.println("�������");
			System.out.println(tableChecker.getSelectionModel().getSelectedItem());
		}
	}
	public void reset(ActionEvent actionEvent) {
		observableList.clear();
	}
	public boolean isItToday(String date) {
		if (formatSimpleDate(date.toString()) == Integer
				.valueOf(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)))
			return true;
		return false;
	}

	public int formatSimpleDate(String dateValue) {
		return Integer.valueOf(dateValue.split(" ")[0].split("-")[0] + dateValue.split(" ")[0].split("-")[1]
				+ dateValue.split(" ")[0].split("-")[2]);

	}
}