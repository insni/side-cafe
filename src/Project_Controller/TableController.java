package Project_Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import Project_Constructor.RsrvtDateTime;
import Project_DAO.RsrvtDao;
import Project_DAO.RsrvtDateTimeDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TableController implements Initializable {
	static int selectedTableNo;
	public static ObservableList<RsrvtDateTime> rsrvtDateTimeList = FXCollections.observableArrayList();
	/* ���� �����̳� */
	@FXML
	private HBox top, center, bottom;
	/* Circle -> ���̺� ���4��,�ߴ�2��+ī���� �ϴ�4�� */
	@FXML
	private Circle topLeft, topCenterLeft, topCenterRight, topRight;
	@FXML
	private Circle centerLeft, centerRight;
	@FXML
	private Circle bottomLeft, bottomCenterLeft, bottomCenterRight, bottomRight;
	@FXML
	private Rectangle counter;
	/* /����ð�����,��������ð�,�����ð� ��Ʈ�ѷ� */
	@FXML
	private Label tlRsrvtStartTime, tlRsrvtEndsTime, tlRmnngTime, tclRmnngTime, tclRsrvtStartTime, tclRsrvtEndsTime,
			tcrRmnngTime, tcrRsrvtStartTime, tcrRsrvtEndsTime, trRmnngTime, trRsrvtStartTime, trRsrvtEndsTime,
			clRmnngTime, clRsrvtStartTime, clRsrvtEndsTime, crRmnngTime, crRsrvtStartTime, crRsrvtEndsTime, blRmnngTime,
			blRsrvtStartTime, blRsrvtEndsTime, bclRmnngTime, bclRsrvtStartTime, bclRsrvtEndsTime, bcrRmnngTime,
			bcrRsrvtStartTime, bcrRsrvtEndsTime, brRmnngTime, brRsrvtStartTime, brRsrvtEndsTime;
	/* ����ð�����,��������ð�,�����ð��� ǥ���ϴ� �� �����̳� */
	@FXML
	private VBox tlLblBox, tclLblBox, tcrLblBox, trLblBox;
	@FXML
	private VBox clLblBox, crLblBox;
	@FXML
	private VBox blLblBox, bclLblBox, bcrLblBox, brLblBox;
	/* /����ð�����,��������ð�,�����ð��� ǥ���ϴ� �� �����̳� */

	private Stage stage;
	private List<RsrvtDateTime> list;
	private int listSize;
	private RsrvtDateTimeDao rsrvtDateTimeDao;
	@FXML
	private Label tlStatus, tclStatus, tcrStatus, trStatus, clStatus, crStatus, blStatus, bclStatus, bcrStatus,
			brStatus;
	private RsrvtDao rsrvtDao;
	LocalTime localTime;

	private String startTime;
	private String endsTime;

	private int exitMinute;
	private int minusMinute;

	public TableController() {
		rsrvtDateTimeDao = new RsrvtDateTimeDao();
		list = rsrvtDateTimeDao.slectAll();
		listSize = list.size();
		rsrvtDao = new RsrvtDao();
		localTime = LocalTime.now();
		startTime = "";
		endsTime = "";

		minusMinute = 0;
		exitMinute = 0;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setInfo();
		rsrvtDateTimeList.clear();
		// tables��
		// seat���̺��� ������ select * from seat �����͸� �����Ѵ�.
		// �ð��� 08:00 ~ 23:00 ����
		for (int i = 0; i < listSize; i++) {
			if (isItToday(list.get(i).getDateTime())) {
				if (LocalTime.now().getHour() > formatSimpleTime(list.get(i).getDateTime()) / 100)
					continue;
				TableController.rsrvtDateTimeList.add(rsrvtDateTimeDao.slectAll().get(i));

			}
		}
//		System.out.println("TABLECONTROLLER");
//		for (int i = 0; i < TableController.rsrvtDateTimeList.size(); i++) {
//			System.out.println(TableController.rsrvtDateTimeList.get(i).getDateTime());
//		}
		if (rsrvtDao.slectAll().size() == 0)
			return;
		for (int i = 0; i < rsrvtDao.slectAll().size(); i++) {
			if (isItToday(rsrvtDao.slectAll().get(i).getRsrvtStartDateTime())) {
				if (localTime.getHour() >= formatSimpleTime(rsrvtDao.slectAll().get(i).getRsrvtStartDateTime()) / 100
						&& localTime.getHour() < formatSimpleTime(rsrvtDao.slectAll().get(i).getRsrvtEndsDateTime())
								/ 100) {
					startTime = formatStringTime(formatSimpleTime(rsrvtDao.slectAll().get(i).getRsrvtStartDateTime()));

					endsTime = formatStringTime(formatSimpleTime(rsrvtDao.slectAll().get(i).getRsrvtEndsDateTime()));

					exitMinute = (formatSimpleTime(rsrvtDao.slectAll().get(i).getRsrvtEndsDateTime()) / 100) * 60
							+ Integer.valueOf(
									String.valueOf(formatSimpleTime(rsrvtDao.slectAll().get(i).getRsrvtEndsDateTime()))
											.substring(2, 4));
					minusMinute = this.localTime.getHour() * 60 + this.localTime.getMinute();
				} else {
					startTime = "";
					endsTime = "";
					exitMinute = 0;
					minusMinute = 0;
				}
				switch (rsrvtDao.slectAll().get(i).getNo()) {
				case 1:
					if ((exitMinute) - (minusMinute) > 0) {
						System.out.println((exitMinute) - (minusMinute));
						tlStatus.setText("������");
						tlLblBox.setVisible(true);
						tlRsrvtStartTime.setText(startTime);
						tlRsrvtEndsTime.setText(endsTime);
						tlRmnngTime.setText((exitMinute) - (minusMinute) + "��");
						topLeft.setStyle("-fx-fill : #f7766a");
						break;
					}
//					else {
//						tlStatus.setText("���డ��");
//						tlLblBox.setVisible(false);
//						tlRsrvtStartTime.setText("");
//						tlRsrvtEndsTime.setText("");
//						tlRmnngTime.setText("");
//						topLeft.setStyle("-fx-fill : #98ff78");
//						break;
//					}
				case 2:
					System.out.println("�¼���ȣ");
					System.out.println(rsrvtDao.slectAll().get(i).getNo());
					System.out.println(rsrvtDao.slectAll().get(i).getRsrvtStartDateTime());
					System.out.println("StartTime : " + startTime);
					if ((exitMinute) - (minusMinute) > 0) {
						System.out.println((exitMinute) - (minusMinute));
						tclStatus.setText("������");
						tclLblBox.setVisible(true);
						tclRsrvtStartTime.setText(startTime);
						tclRsrvtEndsTime.setText(endsTime);
						tclRmnngTime.setText((exitMinute) - (minusMinute) + "��");
						topCenterLeft.setStyle("-fx-fill : #f7766a");
						break;
					}


				case 3:
					if ((exitMinute) - (minusMinute) > 0) {
						tcrStatus.setText("������");
						tcrLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						tcrRsrvtStartTime.setText(startTime);
						tcrRsrvtEndsTime.setText(endsTime);
						tcrRmnngTime.setText((exitMinute) - (minusMinute) + "��");
						topCenterRight.setStyle("-fx-fill : #f7766a");
						break;
					}
//					else {
//						tcrStatus.setText("���డ��");
//						tcrLblBox.setVisible(false);
//						tcrRsrvtStartTime.setText("");
//						tcrRsrvtEndsTime.setText("");
//						tcrRmnngTime.setText("");
//						topCenterRight.setStyle("-fx-fill : #98ff78");
//						break;
//					}
				case 4:
					if ((exitMinute) - (minusMinute) > 0) {
						trStatus.setText("������");
						trLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						trRsrvtStartTime.setText(startTime);
						trRsrvtEndsTime.setText(endsTime);
						trRmnngTime.setText((exitMinute) - (minusMinute) + "��");
						topRight.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 5:
					if ((exitMinute) - (minusMinute) > 0) {
						clStatus.setText("������");
						clLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						clRsrvtStartTime.setText(startTime);
						clRsrvtEndsTime.setText(endsTime);
						clRmnngTime.setText((exitMinute) - (minusMinute) + "��");
						centerLeft.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 6:
					if ((exitMinute) - (minusMinute) > 0) {
						crStatus.setText("������");
						crLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						crRsrvtStartTime.setText(startTime);
						crRsrvtEndsTime.setText(endsTime);
						crRmnngTime.setText((exitMinute) - (minusMinute) + "��");
						centerRight.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 7:
					if ((exitMinute) - (minusMinute) > 0) {
						blStatus.setText("������");
						blLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						blRsrvtStartTime.setText(startTime);
						blRsrvtEndsTime.setText(endsTime);
						blRmnngTime.setText((exitMinute) - (minusMinute) + "��");
						bottomLeft.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 8:
					if ((exitMinute) - (minusMinute) > 0) {
						bclStatus.setText("������");
						bclLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						bclRsrvtStartTime.setText(startTime);
						bclRsrvtEndsTime.setText(endsTime);
						bclRmnngTime.setText((exitMinute) - (minusMinute) + "��");
						bottomCenterLeft.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 9:
					if ((exitMinute) - (minusMinute) > 0) {
						bcrStatus.setText("������");
						bcrLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						bcrRsrvtStartTime.setText(startTime);
						bcrRsrvtEndsTime.setText(endsTime);
						bcrRmnngTime.setText((exitMinute) - (minusMinute) + "��");
						bottomCenterLeft.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 10:
					if ((exitMinute) - (minusMinute) > 0) {
						brStatus.setText("������");
						brLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						brRsrvtStartTime.setText(startTime);
						brRsrvtEndsTime.setText(endsTime);
						brRmnngTime.setText((exitMinute) - (minusMinute) + "��");
						bottomRight.setStyle("-fx-fill : #f7766a");
						break;
					}
				default:
					break;
				}
			}
		}

		stage = new Stage();
		topLeft.setOnMouseClicked(event -> {
			TableController.selectedTableNo = 1;
			choseSeat(event);
		});
		topCenterLeft.setOnMouseClicked(event -> {
			TableController.selectedTableNo = 2;
			choseSeat(event);
		});
		topCenterRight.setOnMouseClicked(event -> {
			TableController.selectedTableNo = 3;
			choseSeat(event);
		});
		topRight.setOnMouseClicked(event -> {
			TableController.selectedTableNo = 4;
			choseSeat(event);
		});
		centerLeft.setOnMouseClicked(event -> {
			TableController.selectedTableNo = 5;
			choseSeat(event);
		});
		centerRight.setOnMouseClicked(event -> {
			TableController.selectedTableNo = 6;
			choseSeat(event);
		});
		bottomLeft.setOnMouseClicked(event -> {
			TableController.selectedTableNo = 7;
			choseSeat(event);
		});
		bottomCenterLeft.setOnMouseClicked(event -> {
			TableController.selectedTableNo = 8;
			choseSeat(event);
		});
		bottomCenterRight.setOnMouseClicked(event -> {
			TableController.selectedTableNo = 9;
			choseSeat(event);
		});
		bottomRight.setOnMouseClicked(event -> {
			TableController.selectedTableNo = 10;
			choseSeat(event);
		});

	}

	public void choseSeat(Event event) {
		try {
			this.stage = (Stage) counter.getScene().getWindow();
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/order.fxml"))));
			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int formatSimpleTime(String timeValue) {
		return Integer.valueOf(timeValue.split(" ")[1].split(":")[0] + timeValue.split(" ")[1].split(":")[1]);

	}

	public int formatSimpleDate(String dateValue) {
		return Integer.valueOf(dateValue.split(" ")[0].split("-")[0] + dateValue.split(" ")[0].split("-")[1]
				+ dateValue.split(" ")[0].split("-")[2]);

	}

	public int formatDate(String dateValue) {
		return Integer.valueOf(dateValue.split("-")[0] + dateValue.split("-")[1] + dateValue.split("-")[2]);
	}

	public int formatTime(String timeValue) {
		return Integer.valueOf(timeValue.split(":")[0] + timeValue.split(":")[1]);
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

	public boolean isItPreviousTime(String date) {
		if (Integer.valueOf(date) < LocalTime.now().getHour())
			return true;
		return false;

	}

	public void setInfo() {
		// �� �ǿ��� ���̺�
		tlStatus.setText("���డ��");
		tlLblBox.setVisible(false);
		tlRsrvtStartTime.setText("");
		tlRsrvtEndsTime.setText("");
		tlRmnngTime.setText("");
		topLeft.setStyle("-fx-fill : #98ff78");
		// �� �߾��� ���� ���̺�
		tclStatus.setText("���డ��");
		tclLblBox.setVisible(false);
		tclRsrvtStartTime.setText("");
		tclRsrvtEndsTime.setText("");
		tclRmnngTime.setText("");
		topCenterLeft.setStyle("-fx-fill : #98ff78");
		// �� �߾��� ������ ���̺�
		tcrStatus.setText("���డ��");
		tcrLblBox.setVisible(false);
		tcrRsrvtStartTime.setText("");
		tcrRsrvtEndsTime.setText("");
		tcrRmnngTime.setText("");
		topCenterRight.setStyle("-fx-fill : #98ff78");
		// �� �ǿ����� ���̺�
		trStatus.setText("���డ��");
		trLblBox.setVisible(false);
		trRsrvtStartTime.setText("");
		trRsrvtEndsTime.setText("");
		trRmnngTime.setText("");
		topRight.setStyle("-fx-fill : #98ff78");

		// �߾� ���� ���̺�
		clStatus.setText("���డ��");
		clLblBox.setVisible(false);
		clRsrvtStartTime.setText("");
		clRsrvtEndsTime.setText("");
		clRmnngTime.setText("");
		centerLeft.setStyle("-fx-fill : #98ff78");
		// �߾� ������ ���̺�
		crStatus.setText("���డ��");
		crLblBox.setVisible(false);
		crRsrvtStartTime.setText("");
		crRsrvtEndsTime.setText("");
		crRmnngTime.setText("");
		centerRight.setStyle("-fx-fill : #98ff78");

		// �ϴ� �� ���� ���̺�
		blStatus.setText("���డ��");
		blLblBox.setVisible(false);
		blRsrvtStartTime.setText("");
		blRsrvtEndsTime.setText("");
		blRmnngTime.setText("");
		bottomLeft.setStyle("-fx-fill : #98ff78");
		// �ϴ� �߾ӿ��� ���� ���̺�
		bclStatus.setText("���డ��");
		bclLblBox.setVisible(false);
		bclRsrvtStartTime.setText("");
		bclRsrvtEndsTime.setText("");
		bclRmnngTime.setText("");
		bottomCenterLeft.setStyle("-fx-fill : #98ff78");
		// �ϴ� �߾ӿ��� ������ ���̺�
		bcrStatus.setText("���డ��");
		bcrLblBox.setVisible(false);
		bcrRsrvtStartTime.setText("");
		bcrRsrvtEndsTime.setText("");
		bcrRmnngTime.setText("");
		bottomCenterRight.setStyle("-fx-fill : #98ff78");
		// �ϴ� �� ������ ���̺�
		brStatus.setText("���డ��");
		brLblBox.setVisible(false);
		brRsrvtStartTime.setText("");
		brRsrvtEndsTime.setText("");
		brRmnngTime.setText("");
		bottomRight.setStyle("-fx-fill : #98ff78");

	}

}
