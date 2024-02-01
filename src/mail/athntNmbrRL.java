package mail;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogEvent;

public class athntNmbrRL implements Initializable {

	// �̸��� ������ȣ ī��Ʈ�ٿ� �˸���
	@FXML
	private Label label;

	// �̸��� ������ȣ �Է�â
	@FXML
	private TextField txtField;

	// ������ȣ ���� ���� �˸�â
	Alert alert = new Alert(AlertType.CONFIRMATION);
	// �̸��� �ּ�
	public static String eMailAddress;
	// ȭ����ȯ�� �ʿ��� ��ü
	Stage stage;
	// �̸��� ������ȣ ������ ��ü�� ���� ����
	MailRL sender;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// �̸��� �ּ� ������ �̸��� ������
		this.sender = new MailRL(athntNmbrRL.eMailAddress);
		System.out.println("(������ȣ ���� �̸��� ���)athntNmbrRL.eMailAddress-> " +athntNmbrRL.eMailAddress);
		// �̸��� ������ȣ ī��Ʈ�ٿ�
		Thread thread = new Thread() {
			private boolean stop; // ���ѷ��� �÷���
			private int countDown = 180; // ���� �ð� -> 3��
			private int minute; // ��
			private int second; // ��

			@Override
			public void run() {
				try {
					while (!stop) {
						if (countDown == 0)
							stop = !stop; // 3�������� -> true
						Platform.runLater(() -> {
							minute = countDown / 60;
							second = countDown % 60;
							if (second < 10) // 00:00 ���� ���߱� ���� ����
								label.setText("0" + minute + ":0" + second);
							else
								label.setText("0" + minute + ":" + second);
							countDown--;

						});
						Thread.sleep(1000); // 1�� ��
					}

				} catch (Exception e) {

				}

			}
		};

		thread.start();
	}

	// Ȯ�� ��ư ������ �ߵ�
	public void clickBtn(ActionEvent actionEvent) {
		// �Է�â�� �Է��� �̸��� ������ȣ�� ��Ȯ���� Ȯ���Ѵ�.
		sender.checkNumber(new Integer(txtField.getText().trim()));
		// �̸��� ���� ���� �˻�
		System.out.println("(�̸��� ������ȣ ���� �˻�)sender.checkNumber(new Integer(txtField.getText().trim())) ->" + sender.certified());
		try {
			// �̸��� ������ȣ�� �����ϴٸ� �Ʒ����� ����
			if (sender.certified()) {
				alert.setHeaderText("�̸����� �����Ǿ����ϴ�.");
				alert.show();
				this.stage = (Stage) txtField.getScene().getWindow();
				this.stage.close();
				// ���������� ��� �Ʒ����� ����
			} else {
				alert.setHeaderText("�̸����� ������ȭ �ٸ��ϴ�");
				alert.show();
			}

			this.stage = (Stage) txtField.getScene().getWindow();
			// alertâ �ݱ� ��ư ������ ��
			alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
				@Override
				public void handle(DialogEvent event) {
					alert.close();
					stage.close();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
