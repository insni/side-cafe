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
	/* 메인 컨테이너 */
	@FXML
	private HBox top, center, bottom;
	/* Circle -> 테이블 상단4개,중단2개+카운터 하단4개 */
	@FXML
	private Circle topLeft, topCenterLeft, topCenterRight, topRight;
	@FXML
	private Circle centerLeft, centerRight;
	@FXML
	private Circle bottomLeft, bottomCenterLeft, bottomCenterRight, bottomRight;
	@FXML
	private Rectangle counter;
	/* /예약시간시작,예약종료시간,남은시간 컨트롤러 */
	@FXML
	private Label tlRsrvtStartTime, tlRsrvtEndsTime, tlRmnngTime, tclRmnngTime, tclRsrvtStartTime, tclRsrvtEndsTime,
			tcrRmnngTime, tcrRsrvtStartTime, tcrRsrvtEndsTime, trRmnngTime, trRsrvtStartTime, trRsrvtEndsTime,
			clRmnngTime, clRsrvtStartTime, clRsrvtEndsTime, crRmnngTime, crRsrvtStartTime, crRsrvtEndsTime, blRmnngTime,
			blRsrvtStartTime, blRsrvtEndsTime, bclRmnngTime, bclRsrvtStartTime, bclRsrvtEndsTime, bcrRmnngTime,
			bcrRsrvtStartTime, bcrRsrvtEndsTime, brRmnngTime, brRsrvtStartTime, brRsrvtEndsTime;
	/* 예약시간시작,예약종료시간,남은시간을 표현하는 라벨 컨테이너 */
	@FXML
	private VBox tlLblBox, tclLblBox, tcrLblBox, trLblBox;
	@FXML
	private VBox clLblBox, crLblBox;
	@FXML
	private VBox blLblBox, bclLblBox, bcrLblBox, brLblBox;
	/* /예약시간시작,예약종료시간,남은시간을 표현하는 라벨 컨테이너 */

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
		// tables는
		// seat테이블의 쿼리문 select * from seat 데이터를 참조한다.
		// 시간대 08:00 ~ 23:00 세팅
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
						tlStatus.setText("예약중");
						tlLblBox.setVisible(true);
						tlRsrvtStartTime.setText(startTime);
						tlRsrvtEndsTime.setText(endsTime);
						tlRmnngTime.setText((exitMinute) - (minusMinute) + "분");
						topLeft.setStyle("-fx-fill : #f7766a");
						break;
					}
//					else {
//						tlStatus.setText("예약가능");
//						tlLblBox.setVisible(false);
//						tlRsrvtStartTime.setText("");
//						tlRsrvtEndsTime.setText("");
//						tlRmnngTime.setText("");
//						topLeft.setStyle("-fx-fill : #98ff78");
//						break;
//					}
				case 2:
					System.out.println("좌석번호");
					System.out.println(rsrvtDao.slectAll().get(i).getNo());
					System.out.println(rsrvtDao.slectAll().get(i).getRsrvtStartDateTime());
					System.out.println("StartTime : " + startTime);
					if ((exitMinute) - (minusMinute) > 0) {
						System.out.println((exitMinute) - (minusMinute));
						tclStatus.setText("예약중");
						tclLblBox.setVisible(true);
						tclRsrvtStartTime.setText(startTime);
						tclRsrvtEndsTime.setText(endsTime);
						tclRmnngTime.setText((exitMinute) - (minusMinute) + "분");
						topCenterLeft.setStyle("-fx-fill : #f7766a");
						break;
					}


				case 3:
					if ((exitMinute) - (minusMinute) > 0) {
						tcrStatus.setText("예약중");
						tcrLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						tcrRsrvtStartTime.setText(startTime);
						tcrRsrvtEndsTime.setText(endsTime);
						tcrRmnngTime.setText((exitMinute) - (minusMinute) + "분");
						topCenterRight.setStyle("-fx-fill : #f7766a");
						break;
					}
//					else {
//						tcrStatus.setText("예약가능");
//						tcrLblBox.setVisible(false);
//						tcrRsrvtStartTime.setText("");
//						tcrRsrvtEndsTime.setText("");
//						tcrRmnngTime.setText("");
//						topCenterRight.setStyle("-fx-fill : #98ff78");
//						break;
//					}
				case 4:
					if ((exitMinute) - (minusMinute) > 0) {
						trStatus.setText("예약중");
						trLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						trRsrvtStartTime.setText(startTime);
						trRsrvtEndsTime.setText(endsTime);
						trRmnngTime.setText((exitMinute) - (minusMinute) + "분");
						topRight.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 5:
					if ((exitMinute) - (minusMinute) > 0) {
						clStatus.setText("예약중");
						clLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						clRsrvtStartTime.setText(startTime);
						clRsrvtEndsTime.setText(endsTime);
						clRmnngTime.setText((exitMinute) - (minusMinute) + "분");
						centerLeft.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 6:
					if ((exitMinute) - (minusMinute) > 0) {
						crStatus.setText("예약중");
						crLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						crRsrvtStartTime.setText(startTime);
						crRsrvtEndsTime.setText(endsTime);
						crRmnngTime.setText((exitMinute) - (minusMinute) + "분");
						centerRight.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 7:
					if ((exitMinute) - (minusMinute) > 0) {
						blStatus.setText("예약중");
						blLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						blRsrvtStartTime.setText(startTime);
						blRsrvtEndsTime.setText(endsTime);
						blRmnngTime.setText((exitMinute) - (minusMinute) + "분");
						bottomLeft.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 8:
					if ((exitMinute) - (minusMinute) > 0) {
						bclStatus.setText("예약중");
						bclLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						bclRsrvtStartTime.setText(startTime);
						bclRsrvtEndsTime.setText(endsTime);
						bclRmnngTime.setText((exitMinute) - (minusMinute) + "분");
						bottomCenterLeft.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 9:
					if ((exitMinute) - (minusMinute) > 0) {
						bcrStatus.setText("예약중");
						bcrLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						bcrRsrvtStartTime.setText(startTime);
						bcrRsrvtEndsTime.setText(endsTime);
						bcrRmnngTime.setText((exitMinute) - (minusMinute) + "분");
						bottomCenterLeft.setStyle("-fx-fill : #f7766a");
						break;
					}
				case 10:
					if ((exitMinute) - (minusMinute) > 0) {
						brStatus.setText("예약중");
						brLblBox.setVisible(true);
						System.out.println((exitMinute) - (minusMinute));
						brRsrvtStartTime.setText(startTime);
						brRsrvtEndsTime.setText(endsTime);
						brRmnngTime.setText((exitMinute) - (minusMinute) + "분");
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
		// 위 맨왼쪽 테이블
		tlStatus.setText("예약가능");
		tlLblBox.setVisible(false);
		tlRsrvtStartTime.setText("");
		tlRsrvtEndsTime.setText("");
		tlRmnngTime.setText("");
		topLeft.setStyle("-fx-fill : #98ff78");
		// 위 중앙쪽 왼쪽 테이블
		tclStatus.setText("예약가능");
		tclLblBox.setVisible(false);
		tclRsrvtStartTime.setText("");
		tclRsrvtEndsTime.setText("");
		tclRmnngTime.setText("");
		topCenterLeft.setStyle("-fx-fill : #98ff78");
		// 위 중앙쪽 오른쪽 테이블
		tcrStatus.setText("예약가능");
		tcrLblBox.setVisible(false);
		tcrRsrvtStartTime.setText("");
		tcrRsrvtEndsTime.setText("");
		tcrRmnngTime.setText("");
		topCenterRight.setStyle("-fx-fill : #98ff78");
		// 위 맨오른쪽 테이블
		trStatus.setText("예약가능");
		trLblBox.setVisible(false);
		trRsrvtStartTime.setText("");
		trRsrvtEndsTime.setText("");
		trRmnngTime.setText("");
		topRight.setStyle("-fx-fill : #98ff78");

		// 중앙 왼쪽 테이블
		clStatus.setText("예약가능");
		clLblBox.setVisible(false);
		clRsrvtStartTime.setText("");
		clRsrvtEndsTime.setText("");
		clRmnngTime.setText("");
		centerLeft.setStyle("-fx-fill : #98ff78");
		// 중앙 오른쪽 테이블
		crStatus.setText("예약가능");
		crLblBox.setVisible(false);
		crRsrvtStartTime.setText("");
		crRsrvtEndsTime.setText("");
		crRmnngTime.setText("");
		centerRight.setStyle("-fx-fill : #98ff78");

		// 하단 맨 왼쪽 테이블
		blStatus.setText("예약가능");
		blLblBox.setVisible(false);
		blRsrvtStartTime.setText("");
		blRsrvtEndsTime.setText("");
		blRmnngTime.setText("");
		bottomLeft.setStyle("-fx-fill : #98ff78");
		// 하단 중앙에서 왼쪽 테이블
		bclStatus.setText("예약가능");
		bclLblBox.setVisible(false);
		bclRsrvtStartTime.setText("");
		bclRsrvtEndsTime.setText("");
		bclRmnngTime.setText("");
		bottomCenterLeft.setStyle("-fx-fill : #98ff78");
		// 하단 중앙에서 오른쪽 테이블
		bcrStatus.setText("예약가능");
		bcrLblBox.setVisible(false);
		bcrRsrvtStartTime.setText("");
		bcrRsrvtEndsTime.setText("");
		bcrRmnngTime.setText("");
		bottomCenterRight.setStyle("-fx-fill : #98ff78");
		// 하단 맨 오른쪽 테이블
		brStatus.setText("예약가능");
		brLblBox.setVisible(false);
		brRsrvtStartTime.setText("");
		brRsrvtEndsTime.setText("");
		brRmnngTime.setText("");
		bottomRight.setStyle("-fx-fill : #98ff78");

	}

}
