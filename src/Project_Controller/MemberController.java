package Project_Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MemberController implements Initializable {
	Stage stage;
	@FXML
	Button btnReservation; // 예약하기 버튼
	@FXML
	Button btnModifing; // 수정하기 버튼
	@FXML
	Button btnWithDraw; // 회원탈퇴하기 버튼

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.stage = new Stage();
		btnReservation.setOnAction(event -> {
			try {
				this.stage=(Stage) btnReservation.getScene().getWindow();
				this.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/rsrvtTableStatus.fxml"))));
				this.stage.setTitle("예약");
				this.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		btnModifing.setOnAction(event -> {
			try {
				stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/infoType.fxml"))));
				stage.setTitle("정보수정");
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		btnWithDraw.setOnAction(event -> {
			try {
				stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/withdraw.fxml"))));
				stage.setTitle("회원탈퇴");
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

}
