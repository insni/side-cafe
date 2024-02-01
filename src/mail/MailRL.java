package mail;

import java.io.File;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MailRL implements Initializable {

	@FXML
	private TextField receive;
	@FXML
	private TextField title;
	@FXML
	private Button btnFile;
	@FXML
	private Button btnSend;
	@FXML
	private HTMLEditor content;
	private File filename;
	private static String filePath;
	// 이메일 인증번호
	private int eMailNumber;
	// 인증번호 받을 이메일 주소
	private String eMailAddress;
	// 이메일 인증 유무
	private boolean certified = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnFile.setOnAction(e -> importFile());
		btnSend.setOnAction(e -> mailSend(e));
	}

	// 매개변수로 이메일주소를 받는다.
	// 이메일 인증번호를 전송한다.
	public MailRL(String eMailAddress) {
		if (eMailAddress != null) {
			// 받을 이메일 주소
			this.eMailAddress = eMailAddress;
			// 이메일 인증번호 (0~4999 중 하나)
			this.eMailNumber = (int) (Math.random() * 5000);
			System.out.println("MailRL에서 보낸 이메일 인증번호 : " + this.eMailNumber);
			athntNmbrRL.eMailAddress = eMailAddress;
			mailSend(new ActionEvent());
		} else {
			System.out.println("MailRL객체를 생성하기 전 athntNmbrRL.eMailAddress( 이메일 인증 번호 받을 이메일주소) 지정은 필수입니다 !!!");
		}
	}

	// 메일 보내기
	public void mailSend(ActionEvent actionEvent) {
//		System.out.println(filePath);
		String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
		String user = "lounge_cu@naver.com"; // 보내는 사람의 메일 계정
		String password = "gksdldma!!"; // 패스워드
		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);

			// 보내는 사람 설정
			message.setFrom(new InternetAddress(user));
			// 받는 사람 설정
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.eMailAddress)); // 리시브자리에 회원가입 이메일
			// 메일 제목 입력
			message.setSubject("회원가입 인증 메일입니다.");
			// 메일 내용 입력
			message.setText("이메일 인증번호입니다.\n" + eMailNumber);
			// 메일 내용 보내기
			Transport.send(message);
			System.out.println("메일 보내기 성공");
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
	}


	// 파일 추출 기능
	public String importFile() {
		FileChooser fileChooser = new FileChooser();
		// 경로 지정
		fileChooser.setInitialDirectory(new File("C:/"));
		fileChooser.getExtensionFilters().addAll(
				// 필터링의 제목 : Txt Files(*.txt), 필터링 형식 : *.txt
				new ExtensionFilter("Txt Files(*.txt)", "*.txt"),
				// 필터링의 제목 : Image Files(*.png, *.jpg, *.gif), 필터링 형식 : *.png, *.gif, *jpg
				new ExtensionFilter("Image Files(*.png, *.jpg, *.gif)", "*.png", "*.jpg", "*.gif"),
				// 필터링의 제목 : Audio Files(*.mp3,*.wav,*.aac), 필터링 형식 : *.mp3,*.wav,*.aac
				new ExtensionFilter("Audio Files(*.mp3, *.wav, *.aac)", "*.mp3", "*.wav", "*.aac"),
				// 필터링의 제목 : All Files(*.*), 필터링 형식 : *.*
				new ExtensionFilter("All Files(*.*)", "*.*"));
		filename = fileChooser.showOpenDialog(receive.getScene().getWindow());
		System.out.println("파일 경로 : " + filename.getPath());
		filePath = filename.getPath();
		return filePath;
	}

	// 입력한 인증번호와 보낸 인증번호의 유무를 조정
	public void checkNumber(int eMailNumber) {
		if (this.eMailNumber == eMailNumber) {
			this.certified = true;
		} else {
			this.certified = false;
		}

	}

	// 이메일 인증유무 반환
	public boolean certified() {
		return this.certified;
	}

	// 이메일 인증번호 반환
	public int getEmailNumber() {
		return this.eMailNumber;
	}
}
