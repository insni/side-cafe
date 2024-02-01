package Project_Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import Project_Constructor.Member;
import Project_DAO.MemberDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mail.athntNmbrRL;
public class InfoController implements Initializable {
	@FXML
	private AnchorPane anchorpane; // 배경화면
	@FXML
	private TextField input_id; // 아이디 입력창
	@FXML
	private Label id_label; // 아이디 중복확인 누르면 뜨는 펫말
	@FXML
	private Button overlap_id_btn; // 아이디 중복확인 버튼
	@FXML
	private PasswordField input_pw; // 비밀번호 입력창
	@FXML
	private Label pw_label; // 비밀번호 중복확인 누르면 뜨는 펫말
	@FXML
	private Button confirm_pw_btn; // 비밀번화 확인 버튼
	@FXML
	private PasswordField input_pw_re; // 비밀번호 재확인 입력창
	@FXML
	private TextField input_name; // 이름 입력창
	@FXML
	private TextField input_phone; // 연락처 입력창
	@FXML
	private Button find_address_btn; // 주소찾기 버튼
	@FXML
	private TextField input_email; // 이메일 입력창
	@FXML
	private ComboBox<String> question_box; // 질문 양식 변경 버튼
	ObservableList<String> question_box_list = FXCollections.observableArrayList("나의 어릴적 꿈은?", "나의 고향은?", "나의 별명은?",
			"내가 가장 아끼는 것은?", "우리집 주소는?"); // 질문 양식 종류

	@FXML
	Button btnJoin;
	@FXML
	Button btnModify;

	private boolean overlap_id_flag;
	private boolean confirm_pw_flag;
	private boolean choiced_question_flag;
	static boolean changeablePW;
	static boolean changeablePhone;
	static boolean changeableEamil;

	@FXML
	private TextField input_question; // 질문의 답변 입력창
	private Alert alert;
	Stage stage;
	private MemberDao Dao = new MemberDao();
	private Member member = LoginController.member;
	@FXML
	private Button btnOverLapId;
	@FXML
	private Button btnOverLapPw;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnOverLapId.setVisible(false);
		id_label.setVisible(true);
		// 아이디 중복확인 속성감시
		input_id.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				try {
					overlap_id_flag = false;// 아이디 중복확인 검사 -> false

					Member member = Dao.selectOne(input_id.getText());
					
					if (input_id.getText().trim().length() < 4 || input_id.getText().trim().length()>15) { 
						id_label.setStyle("-fx-text-fill:#a9b54a");
						id_label.setText("문자 및 숫자로 4~15자리로 입력해주세요");
					} else if (member.getId() != null) {
						id_label.setText("사용중인 아이디입니다.");
						id_label.setStyle("-fx-text-fill:#ff003c");

					} else if (member.getId() == null) {
						id_label.setText("사용할 수 있는 아이디입니다.");
						id_label.setStyle("-fx-text-fill:#0d9e2f");
						overlap_id_flag = !overlap_id_flag; // 아이디 중복확인 검사-> true
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {

				}

				
			}
		});
		btnOverLapPw.setVisible(false);
		pw_label.setVisible(true);
		// 비밀번호 중복확인 속성감시
		input_pw.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				try {
					// 비밀번호 중복확인 깃발
					confirm_pw_flag = false;
					if (input_pw.getText().trim().length() < 4 || input_pw.getText().trim().length()>15) {
						pw_label.setStyle("-fx-text-fill:#a9b54a");
						pw_label.setText("문자 및 번호로 4~15자리로 입력해주세요.");
					}else if(input_pw_re.getText().equals("")) {
						pw_label.setStyle("-fx-text-fill:#a9b54a");
						pw_label.setText("비밀번호 재입력란도 입력해주세요.");
					}
					else if (!input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
						pw_label.setStyle("-fx-text-fill:#ff003c");
						pw_label.setText("비밀번호를가 일치하지 않습니다.");
					} else if (input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
						pw_label.setText("비밀번호가 일치합니다.");
						pw_label.setStyle("-fx-text-fill:#0d9e2f");
						// 비밀번호 중복확인 OK
						confirm_pw_flag = !confirm_pw_flag;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// 비밀번호 중복확인 속성감시
		input_pw_re.textProperty().addListener(new ChangeListener<String>() {

			@Override 
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				try {
					// 비밀번호 중복확인 깃발
					confirm_pw_flag = false;
					if (input_pw.getText().trim().length() < 4 || input_pw.getText().trim().length()>15) {
						pw_label.setStyle("-fx-text-fill:#a9b54a");
						pw_label.setText("문자 및 번호로 4~15자리로 입력해주세요.");
					} else if (!input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
						pw_label.setStyle("-fx-text-fill:#ff003c");
						pw_label.setText("비밀번호를가 일치하지 않습니다.");
					} else if (input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
						pw_label.setText("비밀번호가 일치합니다.");
						pw_label.setStyle("-fx-text-fill:#0d9e2f");
						// 비밀번호 중복확인 OK
						confirm_pw_flag = !confirm_pw_flag;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		question_box.setItems(question_box_list);
		if (member == null) {
			btnJoin.setVisible(true);
			btnModify.setVisible(false);
		}
		// 회원가입시 생성한 멤버
		else if (member != null) {
			btnJoin.setVisible(false);
			btnModify.setVisible(true);
			// 이메일 수정전 무조건 이 메소드 호출해야합니다.
			// 정보란에 회원가입시 기재했던 값들을 기재해주는 메소드입니다.
			input_id.setText(member.getId());
			input_pw.setText(member.getPw());
			input_pw_re.setText(member.getPw());
			input_name.setText(member.getName());
			input_phone.setText(member.getPhone());
			input_email.setText(member.getEmail());
			question_box.setPromptText(member.getQuestion());
			input_question.setText(member.getAnswer());
			
			input_id.setEditable(false);
			input_pw.setEditable(false);
			input_name.setEditable(false);
			input_phone.setEditable(false);
			input_email.setEditable(false);
			question_box.setEditable(false);
			input_question.setEditable(false);

			overlap_id_flag = true;
			choiced_question_flag = true;
			
			if (InfoController.changeablePW) {
				id_label.setVisible(false);

				input_pw.setEditable(true);
				input_pw.setText("");
				input_pw.setEditable(true);
				input_pw_re.setText("");

				btnModify.setOnAction(event -> {
					try {
						if (this.confirm_pw_flag != true) {
							this.alert = new Alert(AlertType.INFORMATION);
							this.alert.setHeaderText("비밀번호 중복확인 버튼을 눌러주세요");
							alert.show();
							throw new Exception("회원 정보 수정비밀번호 중복확인 버튼을 눌러주세요");
						}

						Dao.updatePw(member, input_pw.getText().trim());
						System.out.println("비밀번호 변경 완료");
						this.stage = (Stage) btnModify.getScene().getWindow();
						this.stage.close();
						this.alert = new Alert(AlertType.CONFIRMATION);
						this.alert.setHeaderText("비밀번호 변경완료");
						alert.show();
					} catch (Exception exception) {
						System.out.println("회원 정보수정시 예외발생");
						exception.printStackTrace();
					}
				});

			} else if (InfoController.changeablePhone) {
				id_label.setVisible(false);
				pw_label.setVisible(false);
				
				input_phone.setEditable(true);
				input_phone.setText("");
				btnModify.setOnAction(event->{
					Dao.updatePhone(member, input_phone.getText().trim());
					System.out.println("비밀번호 변경 완료");
					this.stage = (Stage) btnModify.getScene().getWindow();
					this.stage.close();
					this.alert = new Alert(AlertType.CONFIRMATION);
					this.alert.setHeaderText("연락처 변경완료");
					alert.show();
				});
			} else if (InfoController.changeableEamil) {
				id_label.setVisible(false);
				pw_label.setVisible(false);
				
				input_email.setEditable(true);
				input_email.setText("");
				btnModify.setOnAction(event->{
					Dao.updateEmail(member, input_email.getText().trim());
					System.out.println("이메일 변경 완료");
					this.stage = (Stage) btnModify.getScene().getWindow();
					this.stage.close();
					this.alert = new Alert(AlertType.CONFIRMATION);
					this.alert.setHeaderText("이메일 변경완료");
					alert.show();					
				});
				
			}

		}
	}

	// 이메일 인증을 할 수 있도록 이메일 얻어오기
	public String getEmail() {
		return new String(this.input_email.getText());
	}

	// 아이디 중복확인
	@FXML
	public void overlap_id_btn(ActionEvent event) { // 중복버튼 클릭시 발동

		try {
			overlap_id_flag = false;// 아이디 중복확인 검사 -> false

			Member member = Dao.selectOne(input_id.getText());
			alert = new Alert(AlertType.INFORMATION);
			if (input_id.getText().trim().length() < 4 || input_id.getText().trim().length()>15) { 
				id_label.setStyle("-fx-text-fill:#a9b54a");
				id_label.setText("문자 및 숫자로 4~15자리로 입력해주세요");
				alert.setHeaderText("아이디를 4~15자리로 입력해주세요");
				alert.show();
			} else if (member.getId() != null) {
				alert.setHeaderText("사용할 수 없는 아이디입니다.");
				id_label.setText("사용할 수 없는 아이디입니다.");
				id_label.setStyle("-fx-text-fill:#ff003c");
				alert.show();

			} else if (member.getId() == null) {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("사용할 수 있는 아이디입니다.");
				id_label.setText("사용할 수 있는 아이디입니다.");
				id_label.setStyle("-fx-text-fill:#0d9e2f");
				alert.show();
				overlap_id_flag = !overlap_id_flag; // 아이디 중복확인 검사-> true
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	// 비밀번호 재확인
	@FXML
	public void confirm_pw_btn(ActionEvent event) {
		try {
			// 비밀번호 중복확인 깃발
			confirm_pw_flag = false;
			alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("");
			if (input_pw.getText().trim().length() < 4 || input_pw.getText().trim().length()>15) {
				alert.setHeaderText("비밀번호를 4~15자리로 입력해주세요");
				alert.show();
				pw_label.setStyle("-fx-text-fill:#a9b54a");
				pw_label.setText("문자 및 번호로 4~15자리로 입력해주세요.");
			} else if (!input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
				alert.setHeaderText("비밀번호를가 일치하지 않습니다.");
				alert.show();
				pw_label.setStyle("-fx-text-fill:#ff003c");
				pw_label.setText("비밀번호를가 일치하지 않습니다.");
			} else if (input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("비밀번호를 일치합니다.");
				alert.show();
				pw_label.setText("비밀번호가 일치합니다.");
				pw_label.setStyle("-fx-text-fill:#0d9e2f");
				// 비밀번호 중복확인 OK
				confirm_pw_flag = !confirm_pw_flag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 가입 버튼 클릭했을 때 눌렀을 때 발동
	@FXML
	public void join(ActionEvent event) {
		try {
			System.out.println("join()");
			System.out.println("뭐체크했는데?:" + question_box.getSelectionModel().getSelectedItem());
			if (question_box.getSelectionModel().getSelectedItem() != null) {
				choiced_question_flag = true; // 질문 유형 골랐는지 검사 -> true;
			}

			if (overlap_id_flag == true && confirm_pw_flag == true && choiced_question_flag == true

			) {

				String id = input_id.getText().trim();
				String pw = input_pw.getText().trim();
				String name = input_name.getText().trim();
				String phone = input_phone.getText().trim();
				String email = input_email.getText().trim();
				String question = question_box.getSelectionModel().getSelectedItem();
				String answer = input_question.getText().trim();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
				String joinDate = dateFormat.format(new Date());
				Dao.insertMember(new Member(id, pw, name, phone, email, question, answer, joinDate));
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("회원가입 되었습니다.");
				alert.show();
				Stage stage = (Stage) anchorpane.getScene().getWindow();
				stage.close();
				System.out.println("회원가입 성공");

			} else if (overlap_id_flag == false) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("아이디 중복확인을 눌러주세요.");
				alert.show();
			} else if (confirm_pw_flag == false) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("비밀번호 확인버튼을 눌러주세요.");
				alert.show();
			} else if (choiced_question_flag == false) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("질문 유형을 골라주세요.");
				alert.show();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 취소 버튼 클릭버튼 눌렀을 때 발동
	public void exit(ActionEvent event) {
		Stage stage = (Stage) anchorpane.getScene().getWindow();
		stage.close();

	}

	// 이메일 인증하기 버튼 누를시 발동
	public void confirm_email(ActionEvent event) {
		// 이메일 인증창 띄우기
		this.stage = new Stage();
		String regExp="\\w+@\\w+\\.\\w+(\\.\\w+)?";
		if(Pattern.matches(regExp, input_email.getText())==false) {
			alert=new Alert(AlertType.INFORMATION);
			alert.setTitle("");
			alert.setHeaderText("이메일 형식을 확인해주세요.\n(abcd@mail.com)");
			alert.show();
			return;
		}else if(Dao.selectEmail(input_email.getText().trim())==1) {
			alert=new Alert(AlertType.INFORMATION);
			alert.setTitle("");
			alert.setHeaderText("이미 가입된 사용자의 이메일 입니다.");
			alert.show();
			return;
		}
		try {
			athntNmbrRL.eMailAddress = input_email.getText().trim();
			this.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../mail/athntNmbr.fxml"))));
			this.stage.setTitle("이메일 인증 창");
			this.stage.setResizable(false);
			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("이메일 인증 메서드 무사완료");
	}// confirm_email()

}
