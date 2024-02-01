package Project_Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ManagerController implements Initializable {

	@FXML
	private Button btnMenu; // 메뉴관리 버튼
	@FXML
	private Button btnMemberCheck; // 회원찾기 버튼
	@FXML
	private Button btnRsrvtStatus; // 예약정보 버튼
	@FXML
	private Button btnRsrvtTableStatus; // 테이블 예약상태
	private Stage stage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnMenu.setOnAction(event -> manageMenus(event));
		btnMemberCheck.setOnAction(event -> checkMember(event));
		btnRsrvtStatus.setOnAction(event -> viewRsrvtStatus(event));
		btnRsrvtTableStatus.setOnAction(event -> viewRsrvtTableStatus(event));
	}

	// 메뉴관리 이벤트
	public void manageMenus(ActionEvent actonEvent) {
		this.stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/menu.fxml"))));
			stage.setTitle("메뉴관리");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 회원찾기 이벤트
	public void checkMember(ActionEvent actionEvent) {
		this.stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/memberCheck.fxml"))));
			stage.setTitle("회원관리");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 예약정보 이벤트
	public void viewRsrvtStatus(ActionEvent actionEvent) {
		this.stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/rsrvtStatus.fxml"))));
			stage.setTitle("예약관리");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 예약 테이블 상태
	public void viewRsrvtTableStatus(ActionEvent actionEvent) {
		this.stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/rsrvtTableStatus.fxml"))));
			stage.setTitle("예약관리");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
