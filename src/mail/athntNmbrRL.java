package mail;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogEvent;

public class athntNmbrRL implements Initializable {

	// 이메일 인증번호 카운트다운 알림라벨
	@FXML
	private Label label;

	// 이메일 인증번호 입력창
	@FXML
	private TextField txtField;

	// 인증번호 성공 유무 알림창
	Alert alert = new Alert(AlertType.CONFIRMATION);
	// 이메일 주소
	public static String eMailAddress;
	// 화면전환시 필요한 객체
	Stage stage;
	// 이메일 인증번호 보내는 객체를 받을 변수
	MailRL sender;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 이메일 주소 지정과 이메일 보내기
		this.sender = new MailRL(athntNmbrRL.eMailAddress);
		System.out.println("(인증번호 받을 이메일 등록)athntNmbrRL.eMailAddress-> " +athntNmbrRL.eMailAddress);
		// 이메일 인증번호 카운트다운
		Thread thread = new Thread() {
			private boolean stop; // 무한루프 플래그
			private int countDown = 180; // 제한 시간 -> 3분
			private int minute; // 분
			private int second; // 초

			@Override
			public void run() {
				try {
					while (!stop) {
						if (countDown == 0)
							stop = !stop; // 3분지나면 -> true
						Platform.runLater(() -> {
							minute = countDown / 60;
							second = countDown % 60;
							if (second < 10) // 00:00 형식 맞추기 위한 조건
								label.setText("0" + minute + ":0" + second);
							else
								label.setText("0" + minute + ":" + second);
							countDown--;

						});
						Thread.sleep(1000); // 1초 텀
					}

				} catch (Exception e) {

				}

			}
		};

		thread.start();
	}

	// 확인 버튼 누를시 발동
	public void clickBtn(ActionEvent actionEvent) {
		// 입력창에 입력한 이메일 인증번호가 정확한지 확인한다.
		sender.checkNumber(new Integer(txtField.getText().trim()));
		// 이메일 인증 유무 검사
		System.out.println("(이메일 인증번호 유무 검사)sender.checkNumber(new Integer(txtField.getText().trim())) ->" + sender.certified());
		try {
			// 이메일 인증번호와 동일하다면 아래구문 실행
			if (sender.certified()) {
				alert.setHeaderText("이메일이 인증되었습니다.");
				alert.show();
				this.stage = (Stage) txtField.getScene().getWindow();
				this.stage.close();
				// 인증실패의 경우 아래문장 수행
			} else {
				alert.setHeaderText("이메일이 인증번화 다릅니다");
				alert.show();
			}

			this.stage = (Stage) txtField.getScene().getWindow();
			// alert창 닫기 버튼 눌렀을 때
			alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
				@Override
				public void handle(DialogEvent event) {
					alert.close();
					stage.close();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
