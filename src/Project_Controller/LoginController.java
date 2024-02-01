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

// 이 클래스는 로그인창의 UI인 FXML 파일이다.
// 이 클래스는 root.fxml 파일의 컨트롤러인 파일이다.
public class LoginController implements Initializable {

	@FXML
	private GridPane gridPane; // 컨트롤러
	@FXML
	private TextField input_id; // 아이디 입력창
	@FXML
	private PasswordField input_pw; // 비밀번호 입력창
	@FXML
	private Button logBtn; // 로그인 버튼

	@FXML
	private Label addOrder;
	@FXML
	Label label_login;
	Stage stage;
	MemberDao mDao = new MemberDao();
	private Alert alert;
	public static Member member; // 현재 로그인한 회원정보
	private int count; // 로그인 실패 카운터

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

	// 로그인 버튼을 눌렀을 때 호출되는 이벤트 메소드
	@FXML
	private void login(ActionEvent actionEvent) {
		LoginController.member = mDao.selectOne(input_id.getText(), input_pw.getText());

		try {
			if (input_id.getText().trim().equals("admin") && input_pw.getText().trim().equals("admin")) {
				// 관리자 화면
				this.stage = (Stage) logBtn.getScene().getWindow();
				this.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/manager.fxml"))));
				this.stage.setTitle("관리자");
				this.stage.show();

				
			} else if (member.getId() == null || member.getPw() == null) {
				label_login.setStyle("-fx-text-fill:#ff003c");
				label_login.setText("다시 확인해주세요.");

				this.count++;
				if (this.count >= 3) {
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("");
					alert.setHeaderText("관리자에게 문의해주세요.");
					alert.show();
				}
			} else {
				// 멤버 화면
				this.stage = (Stage) logBtn.getScene().getWindow();
				stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../Project_fxml/member.fxml"))));
				this.stage.setTitle("회원");
				this.stage.show();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	};

	// 회원가입창 클릭했을 때
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