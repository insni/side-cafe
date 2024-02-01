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
	Button btnReservation; // �����ϱ� ��ư
	@FXML
	Button btnModifing; // �����ϱ� ��ư
	@FXML
	Button btnWithDraw; // ȸ��Ż���ϱ� ��ư

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.stage = new Stage();
		btnReservation.setOnAction(event -> {
			try {
				this.stage=(Stage) btnReservation.getScene().getWindow();
				this.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/rsrvtTableStatus.fxml"))));
				this.stage.setTitle("����");
				this.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		btnModifing.setOnAction(event -> {
			try {
				stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/infoType.fxml"))));
				stage.setTitle("��������");
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		btnWithDraw.setOnAction(event -> {
			try {
				stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/withdraw.fxml"))));
				stage.setTitle("ȸ��Ż��");
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

}
