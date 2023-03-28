package game;

public class User {
	private String userId;
	private String userPw;
	private String userName;
	
	public User(String id, String pw, String name) {
		this.userId = id;
		this.userPw = pw;
		this.userName = name;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getUserPw() {
		return userPw;
	}
	
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	public String getUserName() {
		return userName;
	}
	
}
