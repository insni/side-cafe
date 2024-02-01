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
	private AnchorPane anchorpane; // ���ȭ��
	@FXML
	private TextField input_id; // ���̵� �Է�â
	@FXML
	private Label id_label; // ���̵� �ߺ�Ȯ�� ������ �ߴ� �긻
	@FXML
	private Button overlap_id_btn; // ���̵� �ߺ�Ȯ�� ��ư
	@FXML
	private PasswordField input_pw; // ��й�ȣ �Է�â
	@FXML
	private Label pw_label; // ��й�ȣ �ߺ�Ȯ�� ������ �ߴ� �긻
	@FXML
	private Button confirm_pw_btn; // ��й�ȭ Ȯ�� ��ư
	@FXML
	private PasswordField input_pw_re; // ��й�ȣ ��Ȯ�� �Է�â
	@FXML
	private TextField input_name; // �̸� �Է�â
	@FXML
	private TextField input_phone; // ����ó �Է�â
	@FXML
	private Button find_address_btn; // �ּ�ã�� ��ư
	@FXML
	private TextField input_email; // �̸��� �Է�â
	@FXML
	private ComboBox<String> question_box; // ���� ��� ���� ��ư
	ObservableList<String> question_box_list = FXCollections.observableArrayList("���� ��� ����?", "���� ������?", "���� ������?",
			"���� ���� �Ƴ��� ����?", "�츮�� �ּҴ�?"); // ���� ��� ����

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
	private TextField input_question; // ������ �亯 �Է�â
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
		// ���̵� �ߺ�Ȯ�� �Ӽ�����
		input_id.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				try {
					overlap_id_flag = false;// ���̵� �ߺ�Ȯ�� �˻� -> false

					Member member = Dao.selectOne(input_id.getText());
					
					if (input_id.getText().trim().length() < 4 || input_id.getText().trim().length()>15) { 
						id_label.setStyle("-fx-text-fill:#a9b54a");
						id_label.setText("���� �� ���ڷ� 4~15�ڸ��� �Է����ּ���");
					} else if (member.getId() != null) {
						id_label.setText("������� ���̵��Դϴ�.");
						id_label.setStyle("-fx-text-fill:#ff003c");

					} else if (member.getId() == null) {
						id_label.setText("����� �� �ִ� ���̵��Դϴ�.");
						id_label.setStyle("-fx-text-fill:#0d9e2f");
						overlap_id_flag = !overlap_id_flag; // ���̵� �ߺ�Ȯ�� �˻�-> true
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {

				}

				
			}
		});
		btnOverLapPw.setVisible(false);
		pw_label.setVisible(true);
		// ��й�ȣ �ߺ�Ȯ�� �Ӽ�����
		input_pw.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				try {
					// ��й�ȣ �ߺ�Ȯ�� ���
					confirm_pw_flag = false;
					if (input_pw.getText().trim().length() < 4 || input_pw.getText().trim().length()>15) {
						pw_label.setStyle("-fx-text-fill:#a9b54a");
						pw_label.setText("���� �� ��ȣ�� 4~15�ڸ��� �Է����ּ���.");
					}else if(input_pw_re.getText().equals("")) {
						pw_label.setStyle("-fx-text-fill:#a9b54a");
						pw_label.setText("��й�ȣ ���Է¶��� �Է����ּ���.");
					}
					else if (!input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
						pw_label.setStyle("-fx-text-fill:#ff003c");
						pw_label.setText("��й�ȣ���� ��ġ���� �ʽ��ϴ�.");
					} else if (input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
						pw_label.setText("��й�ȣ�� ��ġ�մϴ�.");
						pw_label.setStyle("-fx-text-fill:#0d9e2f");
						// ��й�ȣ �ߺ�Ȯ�� OK
						confirm_pw_flag = !confirm_pw_flag;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// ��й�ȣ �ߺ�Ȯ�� �Ӽ�����
		input_pw_re.textProperty().addListener(new ChangeListener<String>() {

			@Override 
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				try {
					// ��й�ȣ �ߺ�Ȯ�� ���
					confirm_pw_flag = false;
					if (input_pw.getText().trim().length() < 4 || input_pw.getText().trim().length()>15) {
						pw_label.setStyle("-fx-text-fill:#a9b54a");
						pw_label.setText("���� �� ��ȣ�� 4~15�ڸ��� �Է����ּ���.");
					} else if (!input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
						pw_label.setStyle("-fx-text-fill:#ff003c");
						pw_label.setText("��й�ȣ���� ��ġ���� �ʽ��ϴ�.");
					} else if (input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
						pw_label.setText("��й�ȣ�� ��ġ�մϴ�.");
						pw_label.setStyle("-fx-text-fill:#0d9e2f");
						// ��й�ȣ �ߺ�Ȯ�� OK
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
		// ȸ�����Խ� ������ ���
		else if (member != null) {
			btnJoin.setVisible(false);
			btnModify.setVisible(true);
			// �̸��� ������ ������ �� �޼ҵ� ȣ���ؾ��մϴ�.
			// �������� ȸ�����Խ� �����ߴ� ������ �������ִ� �޼ҵ��Դϴ�.
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
							this.alert.setHeaderText("��й�ȣ �ߺ�Ȯ�� ��ư�� �����ּ���");
							alert.show();
							throw new Exception("ȸ�� ���� ������й�ȣ �ߺ�Ȯ�� ��ư�� �����ּ���");
						}

						Dao.updatePw(member, input_pw.getText().trim());
						System.out.println("��й�ȣ ���� �Ϸ�");
						this.stage = (Stage) btnModify.getScene().getWindow();
						this.stage.close();
						this.alert = new Alert(AlertType.CONFIRMATION);
						this.alert.setHeaderText("��й�ȣ ����Ϸ�");
						alert.show();
					} catch (Exception exception) {
						System.out.println("ȸ�� ���������� ���ܹ߻�");
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
					System.out.println("��й�ȣ ���� �Ϸ�");
					this.stage = (Stage) btnModify.getScene().getWindow();
					this.stage.close();
					this.alert = new Alert(AlertType.CONFIRMATION);
					this.alert.setHeaderText("����ó ����Ϸ�");
					alert.show();
				});
			} else if (InfoController.changeableEamil) {
				id_label.setVisible(false);
				pw_label.setVisible(false);
				
				input_email.setEditable(true);
				input_email.setText("");
				btnModify.setOnAction(event->{
					Dao.updateEmail(member, input_email.getText().trim());
					System.out.println("�̸��� ���� �Ϸ�");
					this.stage = (Stage) btnModify.getScene().getWindow();
					this.stage.close();
					this.alert = new Alert(AlertType.CONFIRMATION);
					this.alert.setHeaderText("�̸��� ����Ϸ�");
					alert.show();					
				});
				
			}

		}
	}

	// �̸��� ������ �� �� �ֵ��� �̸��� ������
	public String getEmail() {
		return new String(this.input_email.getText());
	}

	// ���̵� �ߺ�Ȯ��
	@FXML
	public void overlap_id_btn(ActionEvent event) { // �ߺ���ư Ŭ���� �ߵ�

		try {
			overlap_id_flag = false;// ���̵� �ߺ�Ȯ�� �˻� -> false

			Member member = Dao.selectOne(input_id.getText());
			alert = new Alert(AlertType.INFORMATION);
			if (input_id.getText().trim().length() < 4 || input_id.getText().trim().length()>15) { 
				id_label.setStyle("-fx-text-fill:#a9b54a");
				id_label.setText("���� �� ���ڷ� 4~15�ڸ��� �Է����ּ���");
				alert.setHeaderText("���̵� 4~15�ڸ��� �Է����ּ���");
				alert.show();
			} else if (member.getId() != null) {
				alert.setHeaderText("����� �� ���� ���̵��Դϴ�.");
				id_label.setText("����� �� ���� ���̵��Դϴ�.");
				id_label.setStyle("-fx-text-fill:#ff003c");
				alert.show();

			} else if (member.getId() == null) {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("����� �� �ִ� ���̵��Դϴ�.");
				id_label.setText("����� �� �ִ� ���̵��Դϴ�.");
				id_label.setStyle("-fx-text-fill:#0d9e2f");
				alert.show();
				overlap_id_flag = !overlap_id_flag; // ���̵� �ߺ�Ȯ�� �˻�-> true
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	// ��й�ȣ ��Ȯ��
	@FXML
	public void confirm_pw_btn(ActionEvent event) {
		try {
			// ��й�ȣ �ߺ�Ȯ�� ���
			confirm_pw_flag = false;
			alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("");
			if (input_pw.getText().trim().length() < 4 || input_pw.getText().trim().length()>15) {
				alert.setHeaderText("��й�ȣ�� 4~15�ڸ��� �Է����ּ���");
				alert.show();
				pw_label.setStyle("-fx-text-fill:#a9b54a");
				pw_label.setText("���� �� ��ȣ�� 4~15�ڸ��� �Է����ּ���.");
			} else if (!input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
				alert.setHeaderText("��й�ȣ���� ��ġ���� �ʽ��ϴ�.");
				alert.show();
				pw_label.setStyle("-fx-text-fill:#ff003c");
				pw_label.setText("��й�ȣ���� ��ġ���� �ʽ��ϴ�.");
			} else if (input_pw.getText().trim().equals(input_pw_re.getText().trim())) {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("��й�ȣ�� ��ġ�մϴ�.");
				alert.show();
				pw_label.setText("��й�ȣ�� ��ġ�մϴ�.");
				pw_label.setStyle("-fx-text-fill:#0d9e2f");
				// ��й�ȣ �ߺ�Ȯ�� OK
				confirm_pw_flag = !confirm_pw_flag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���� ��ư Ŭ������ �� ������ �� �ߵ�
	@FXML
	public void join(ActionEvent event) {
		try {
			System.out.println("join()");
			System.out.println("��üũ�ߴµ�?:" + question_box.getSelectionModel().getSelectedItem());
			if (question_box.getSelectionModel().getSelectedItem() != null) {
				choiced_question_flag = true; // ���� ���� ������� �˻� -> true;
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
				alert.setHeaderText("ȸ������ �Ǿ����ϴ�.");
				alert.show();
				Stage stage = (Stage) anchorpane.getScene().getWindow();
				stage.close();
				System.out.println("ȸ������ ����");

			} else if (overlap_id_flag == false) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("���̵� �ߺ�Ȯ���� �����ּ���.");
				alert.show();
			} else if (confirm_pw_flag == false) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("��й�ȣ Ȯ�ι�ư�� �����ּ���.");
				alert.show();
			} else if (choiced_question_flag == false) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("���� ������ ����ּ���.");
				alert.show();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��� ��ư Ŭ����ư ������ �� �ߵ�
	public void exit(ActionEvent event) {
		Stage stage = (Stage) anchorpane.getScene().getWindow();
		stage.close();

	}

	// �̸��� �����ϱ� ��ư ������ �ߵ�
	public void confirm_email(ActionEvent event) {
		// �̸��� ����â ����
		this.stage = new Stage();
		String regExp="\\w+@\\w+\\.\\w+(\\.\\w+)?";
		if(Pattern.matches(regExp, input_email.getText())==false) {
			alert=new Alert(AlertType.INFORMATION);
			alert.setTitle("");
			alert.setHeaderText("�̸��� ������ Ȯ�����ּ���.\n(abcd@mail.com)");
			alert.show();
			return;
		}else if(Dao.selectEmail(input_email.getText().trim())==1) {
			alert=new Alert(AlertType.INFORMATION);
			alert.setTitle("");
			alert.setHeaderText("�̹� ���Ե� ������� �̸��� �Դϴ�.");
			alert.show();
			return;
		}
		try {
			athntNmbrRL.eMailAddress = input_email.getText().trim();
			this.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../mail/athntNmbr.fxml"))));
			this.stage.setTitle("�̸��� ���� â");
			this.stage.setResizable(false);
			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("�̸��� ���� �޼��� ����Ϸ�");
	}// confirm_email()

}
