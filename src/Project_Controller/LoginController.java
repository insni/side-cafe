package Project_Controller;

import java.net.URL;

import java.util.ResourceBundle;

import Project_Constructor.Member;
import Project_DAO.MemberDao;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// �� Ŭ������ �α���â�� UI�� FXML �����̴�.
// �� Ŭ������ root.fxml ������ ��Ʈ�ѷ��� �����̴�.
public class LoginController implements Initializable {

	@FXML
	private GridPane gridPane; // ��Ʈ�ѷ�
	@FXML
	private TextField input_id; // ���̵� �Է�â
	@FXML
	private PasswordField input_pw; // ��й�ȣ �Է�â
	@FXML
	private Button logBtn; // �α��� ��ư

	@FXML
	private Label addOrder;
	@FXML
	Label label_login;
	Stage stage;
	MemberDao mDao = new MemberDao();
	private Alert alert;
	public static Member member; // ���� �α����� ȸ������
	private int count; // �α��� ���� ī����

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label_login.setText("");
		input_pw.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					login(null);
				}
			}
		});
	}

	// �α��� ��ư�� ������ �� ȣ��Ǵ� �̺�Ʈ �޼ҵ�
	@FXML
	private void login(ActionEvent actionEvent) {
		LoginController.member = mDao.selectOne(input_id.getText(), input_pw.getText());

		try {
			if (input_id.getText().trim().equals("admin") && input_pw.getText().trim().equals("admin")) {
				// ������ ȭ��
				this.stage = (Stage) logBtn.getScene().getWindow();
				this.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/manager.fxml"))));
				this.stage.setTitle("������");
				this.stage.show();

				
			} else if (member.getId() == null || member.getPw() == null) {
				label_login.setStyle("-fx-text-fill:#ff003c");
				label_login.setText("�ٽ� Ȯ�����ּ���.");

				this.count++;
				if (this.count >= 3) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("");
					alert.setHeaderText("�����ڿ��� �������ּ���.");
					alert.show();
				}
			} else {
				// ��� ȭ��
				this.stage = (Stage) logBtn.getScene().getWindow();
				stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/member.fxml"))));
				this.stage.setTitle("ȸ��");
				this.stage.show();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	};

	// ȸ������â Ŭ������ ��
	@FXML
	private void open_join_window(ActionEvent event) {
		try {
			this.stage = new Stage();
			this.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/info.fxml"))));
			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}