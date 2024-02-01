package Project_Constructor;


public class Member {

	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private String question;
	private String answer;
	private String joinDate;


	public Member() {
	}

	public Member(String id, String pw) {
		this.id = new String(id);
		this.pw = new String(pw);
	}


	public Member(String id, String pw, String name, String phone, String address, String question,String answer,String joinDate) {
		this.id = new String(id);
		this.pw = new String(pw);
		this.name = new String(name);
		this.phone = new String(phone);
		this.email = new String(address);
		this.question = new String(question);
		this.answer = new String(answer);
		this.joinDate= new String(joinDate);

	}
	// Setters

	public void setId(String id) {
		this.id=id;
	}

	public void setPw(String pw) {
		this.pw=pw;

	}

	public void setName(String name) {
		this.name=name;
	}

	public void setPhone(String phone) {
		this.phone=phone;
	}

	public void setEmail(String email) {
		this.email=email;
	}

	public void setQuestion(String question) {
		this.question=question;
	}
	public void setAnswer(String answer) {
		this.answer=answer;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate=joinDate;
	}

	// Getters
	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
	public String getJoinDate() {
		return joinDate;
	}


}
