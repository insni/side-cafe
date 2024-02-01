package Project_Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Project_DAO.MemberDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InfoTypeController implements Initializable {

	MemberDao dao = new MemberDao();
	Stage stage;
	@FXML
	Button btnPw; // ��й�ȣ ����
	@FXML
	Button btnPhone; // ����ó ����
	@FXML
	Button btnEmail; // �̸��� ����

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.stage = new Stage();

		// ��й�ȣ ����
		btnPw.setOnAction(e -> {
			InfoController.changeablePW = !InfoController.changeablePW;

			modify();
			InfoController.changeablePW = false;
		});
		btnPhone.setOnAction(e -> {
			InfoController.changeablePhone = !InfoController.changeablePhone;

			modify();
			InfoController.changeablePhone = false;
		});
		btnEmail.setOnAction(e -> {
			InfoController.changeableEamil = !InfoController.changeableEamil;

			modify();
			InfoController.changeableEamil = false;
		});

	}

	public void modify() {
		this.stage = new Stage();
		try {
			this.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/info.fxml"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.stage.show();
	}

}
