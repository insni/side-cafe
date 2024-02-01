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
	// �̸��� ������ȣ
	private int eMailNumber;
	// ������ȣ ���� �̸��� �ּ�
	private String eMailAddress;
	// �̸��� ���� ����
	private boolean certified = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnFile.setOnAction(e -> importFile());
		btnSend.setOnAction(e -> mailSend(e));
	}

	// �Ű������� �̸����ּҸ� �޴´�.
	// �̸��� ������ȣ�� �����Ѵ�.
	public MailRL(String eMailAddress) {
		if (eMailAddress != null) {
			// ���� �̸��� �ּ�
			this.eMailAddress = eMailAddress;
			// �̸��� ������ȣ (0~4999 �� �ϳ�)
			this.eMailNumber = (int) (Math.random() * 5000);
			System.out.println("MailRL���� ���� �̸��� ������ȣ : " + this.eMailNumber);
			athntNmbrRL.eMailAddress = eMailAddress;
			mailSend(new ActionEvent());
		} else {
			System.out.println("MailRL��ü�� �����ϱ� �� athntNmbrRL.eMailAddress( �̸��� ���� ��ȣ ���� �̸����ּ�) ������ �ʼ��Դϴ� !!!");
		}
	}

	// ���� ������
	public void mailSend(ActionEvent actionEvent) {
//		System.out.println(filePath);
		String host = "smtp.naver.com"; // ���̹��� ��� ���̹� ����, gmail��� gmail ����
		String user = "lounge_cu@naver.com"; // ������ ����� ���� ����
		String password = "gksdldma!!"; // �н�����
		// SMTP ���� ������ �����Ѵ�.
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

			// ������ ��� ����
			message.setFrom(new InternetAddress(user));
			// �޴� ��� ����
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.eMailAddress)); // ���ú��ڸ��� ȸ������ �̸���
			// ���� ���� �Է�
			message.setSubject("ȸ������ ���� �����Դϴ�.");
			// ���� ���� �Է�
			message.setText("�̸��� ������ȣ�Դϴ�.\n" + eMailNumber);
			// ���� ���� ������
			Transport.send(message);
			System.out.println("���� ������ ����");
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
	}


	// ���� ���� ���
	public String importFile() {
		FileChooser fileChooser = new FileChooser();
		// ��� ����
		fileChooser.setInitialDirectory(new File("C:/"));
		fileChooser.getExtensionFilters().addAll(
				// ���͸��� ���� : Txt Files(*.txt), ���͸� ���� : *.txt
				new ExtensionFilter("Txt Files(*.txt)", "*.txt"),
				// ���͸��� ���� : Image Files(*.png, *.jpg, *.gif), ���͸� ���� : *.png, *.gif, *jpg
				new ExtensionFilter("Image Files(*.png, *.jpg, *.gif)", "*.png", "*.jpg", "*.gif"),
				// ���͸��� ���� : Audio Files(*.mp3,*.wav,*.aac), ���͸� ���� : *.mp3,*.wav,*.aac
				new ExtensionFilter("Audio Files(*.mp3, *.wav, *.aac)", "*.mp3", "*.wav", "*.aac"),
				// ���͸��� ���� : All Files(*.*), ���͸� ���� : *.*
				new ExtensionFilter("All Files(*.*)", "*.*"));
		filename = fileChooser.showOpenDialog(receive.getScene().getWindow());
		System.out.println("���� ��� : " + filename.getPath());
		filePath = filename.getPath();
		return filePath;
	}

	// �Է��� ������ȣ�� ���� ������ȣ�� ������ ����
	public void checkNumber(int eMailNumber) {
		if (this.eMailNumber == eMailNumber) {
			this.certified = true;
		} else {
			this.certified = false;
		}

	}

	// �̸��� �������� ��ȯ
	public boolean certified() {
		return this.certified;
	}

	// �̸��� ������ȣ ��ȯ
	public int getEmailNumber() {
		return this.eMailNumber;
	}
}
