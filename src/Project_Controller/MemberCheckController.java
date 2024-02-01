//	회원 조회 테이블 컨트롤러
package Project_Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Project_Constructor.Member;
import Project_Constructor.MemberProperty;
import Project_DAO.MemberDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class MemberCheckController implements Initializable {
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TableView<MemberProperty> tableView;
	@FXML
	private TextField textField;
	private ObservableList<MemberProperty> observableList;
	private MemberProperty[] memberPropList;
	private MemberDao Dao;
	private Member member;

//	private Button btnCheck;
//	private Button btnCheckAll;
	public MemberCheckController() {
		this.Dao = new MemberDao(); // MemberDao 생성
		this.member = new Member();
		this.observableList = FXCollections.observableArrayList();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		btnCheckAll.setOnAction(event->checkAll(event));
//		btnCheck.setOnAction(event->check(event));
		TableColumn<MemberProperty, ?> id = tableView.getColumns().get(0);
		TableColumn<MemberProperty, ?> pw = tableView.getColumns().get(1);
		TableColumn<MemberProperty, ?> name = tableView.getColumns().get(2);
		TableColumn<MemberProperty, ?> phone = tableView.getColumns().get(3);
		TableColumn<MemberProperty, ?> email = tableView.getColumns().get(4);
		TableColumn<MemberProperty, ?> question = tableView.getColumns().get(5);
		TableColumn<MemberProperty, ?> answer = tableView.getColumns().get(6);
		TableColumn<MemberProperty, ?> joinDate = tableView.getColumns().get(7);

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		pw.setCellValueFactory(new PropertyValueFactory<>("pw"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		question.setCellValueFactory(new PropertyValueFactory<>("question"));
		answer.setCellValueFactory(new PropertyValueFactory<>("answer"));
		joinDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));

	}

	public void check(ActionEvent actionEvent) { // 아이디로 조회가능
		if (textField.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("");
			alert.setHeaderText("아이디를 입력하세요.");
			alert.show();
			return;
		} else {
			this.member = this.Dao.selectOne(textField.getText());
			if (this.member == null) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("");
				alert.setHeaderText("존재하지 않는 아이디입니다.");
				alert.show();
				return;
			}
			member = Dao.selectOne(textField.getText().trim()); // 적은 아이디 가져옴
			this.observableList.clear();
			MemberProperty memberProperty = new MemberProperty(member.getId(), member.getPw(), member.getName(),
					member.getPhone(), member.getEmail(), member.getQuestion(), member.getAnswer(),
					member.getJoinDate());
			this.observableList.add(memberProperty);
			this.tableView.setItems(this.observableList);

		}
	}

	// 전체조회
	public void checkAll(ActionEvent actionEvent) {
		this.observableList.clear();
		// List<Member> 반환 받음
		List<Member> memberList = Dao.slectAll();
		// 반환받은 List<Property>의 사이즈 만큼 MemberProperty객체 배열생성
		memberPropList = new MemberProperty[memberList.size()];

		// 위에서 생성한 객체배열에 attach
		for (int i = 0; i < memberPropList.length; i++) {
			Member member = memberList.get(i);
			memberPropList[i] = new MemberProperty(member.getId(), member.getPw(), member.getName(), member.getPhone(),
					member.getEmail(), member.getQuestion(), member.getAnswer(), member.getJoinDate());
		}
		observableList = FXCollections.observableArrayList(memberPropList);
		tableView.setItems(observableList);
	}

}