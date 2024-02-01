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
	private Button btnMenu; // �޴����� ��ư
	@FXML
	private Button btnMemberCheck; // ȸ��ã�� ��ư
	@FXML
	private Button btnRsrvtStatus; // �������� ��ư
	@FXML
	private Button btnRsrvtTableStatus; // ���̺� �������
	private Stage stage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnMenu.setOnAction(event -> manageMenus(event));
		btnMemberCheck.setOnAction(event -> checkMember(event));
		btnRsrvtStatus.setOnAction(event -> viewRsrvtStatus(event));
		btnRsrvtTableStatus.setOnAction(event -> viewRsrvtTableStatus(event));
	}

	// �޴����� �̺�Ʈ
	public void manageMenus(ActionEvent actonEvent) {
		this.stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/menu.fxml"))));
			stage.setTitle("�޴�����");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ȸ��ã�� �̺�Ʈ
	public void checkMember(ActionEvent actionEvent) {
		this.stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/memberCheck.fxml"))));
			stage.setTitle("ȸ������");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �������� �̺�Ʈ
	public void viewRsrvtStatus(ActionEvent actionEvent) {
		this.stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/rsrvtStatus.fxml"))));
			stage.setTitle("�������");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���� ���̺� ����
	public void viewRsrvtTableStatus(ActionEvent actionEvent) {
		this.stage = new Stage();
		try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/rsrvtTableStatus.fxml"))));
			stage.setTitle("�������");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
