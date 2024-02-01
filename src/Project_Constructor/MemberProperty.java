package Project_Constructor;


import javafx.beans.property.SimpleStringProperty;

public class MemberProperty {

	private SimpleStringProperty id;
	private SimpleStringProperty pw;
	private SimpleStringProperty name;
	private SimpleStringProperty phone;
	private SimpleStringProperty email;
	private SimpleStringProperty question;
	private SimpleStringProperty answer;
	private SimpleStringProperty joinDate;


	public MemberProperty() {
	}
	public MemberProperty(String id) {
		this.id = new SimpleStringProperty(id);
		}

	public MemberProperty(String id, String pw) {
		this.id = new SimpleStringProperty(id);
		this.pw = new SimpleStringProperty(pw);
	}


	public MemberProperty(String id, String pw, String name, String phone, String email, String question,String answer,String joinDate) {
		this.id = new SimpleStringProperty(id);
		this.pw = new SimpleStringProperty(pw);
		this.name = new SimpleStringProperty(name);
		this.phone = new SimpleStringProperty(phone);
		this.email = new SimpleStringProperty(email);
		this.question = new SimpleStringProperty(question);
		this.answer = new SimpleStringProperty(answer);
		this.joinDate = new SimpleStringProperty(joinDate);
	}

	// Setters

	public void setId(String id) {
		this.id.set(id);
	}

	public void setPw(String pw) {
		this.pw.set(pw);

	}

	public void setName(String name) {
		this.name.set(name);
	}

	public void setPhone(String phone) {
		this.phone.set(phone);
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public void setQuestion(String question) {
		this.question.set(question);
	}
	public void setAnswer(String answer) {
		this.answer.set(answer);
	}
	public void setJoinDate(String joinDate) {
		this.joinDate.set(joinDate);
	}

	// Getters
	public String getId() {
		return id.get();
	}

	public String getPw() {
		return pw.get();
	}

	public String getName() {
		return name.get();
	}

	public String getPhone() {
		return phone.get();
	}

	public String getEmail() {
		return email.get();
	}

	public String getQuestion() {
		return question.get();
	}
	public String getAnswer() {
		return answer.get();
	}
	public String getJoinDate() {
		return joinDate.get();
	}


}
