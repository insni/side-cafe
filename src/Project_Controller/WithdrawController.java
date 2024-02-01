package Project_Controller;

import java.net.URL;
import java.util.ResourceBundle;


import Project_DAO.MemberDao;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class WithdrawController implements Initializable{
	@FXML
	private PasswordField input_pw;
	@FXML
	private PasswordField input_pw_re;
	@FXML
	private Button btnWithDraw;
	@FXML
	private Button btnCancel;
	private Alert alert;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnWithDraw.setOnAction(event->{
			if(LoginController.member.getPw().equals(input_pw.getText().trim()) && LoginController.member.getPw().equals(input_pw_re.getText().trim())){
				alert= new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("회원탈퇴 되었습니다.");
				alert.show();
				MemberDao memberDao=new MemberDao();
				memberDao.deleteMember(LoginController.member);
				alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
					
					@Override
					public void handle(DialogEvent event) {
						((Stage)btnCancel.getScene().getWindow()).close();
						System.exit(0);
					}
				});
			}else {
				alert=new Alert(AlertType.WARNING);
				alert.setHeaderText("비밀번호를 다시 확인해주세요.");
				alert.show();
			}
					
		});
		btnCancel.setOnAction(event->{
			((Stage)btnCancel.getScene().getWindow()).close();
		});
		
				
			
				
	}

}
