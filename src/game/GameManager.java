package game;

import java.util.*;
import java.io.*;

public class GameManager {
	
	private boolean log;
	private User curUser;
	
	public static Scanner scan;
	public static Random ran;
	private static GameManager instance = new GameManager();
	
	private Map<String, User> userList = new HashMap<String, User>();
	
	private final char JOIN = '1';
	private final char LOG_IN = '2';
	
	private final char CONN = '1';
	private final char LOG_OUT = '2';
	private final char LEAVE = '3';
	
	private final char QUIT = '0';

	public static GameManager getInstance() {	
		return instance;
	}
	
	private GameManager() {}
	
	private void init() {
		this.scan = new Scanner(System.in);
		this.ran = new Random();
	}
	
	private void printMainMenu() {
		if(!this.log) {
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
		} else {
			System.out.println("1. 접속하기");
			System.out.println("2. 로그아웃");
			System.out.println("3. 회원탈퇴");
		}
		System.out.println("0. 종료");
	}
	
	static public String input(String msg) {
		System.out.print(msg + ": ");
		String output = scan.next();	
		
		return output;
	}
	
	void run() {
		
		init();
		while(true) {
			printMainMenu();
			char sel = input("select").charAt(0);
			
			if(!this.log) {
				if(sel==JOIN)			join();
				else if(sel==LOG_IN)	logIn();
			} else {
				if(sel==CONN)			connect();
				else if(sel==LOG_OUT)	logOut();
				else if(sel==LEAVE)		leave();
			}
			
			if(sel==QUIT)				break;
		}
	}
	
	private void join() {
		String id = input("ID");
		
		if(this.userList.containsKey(id)) {
			System.out.println("중복된 아이디입니다.");
			return;
		}
		
		String pw = input("PW");
		String name = input("Name");
		
		User user = new User(id, pw, name);
		
		this.userList.put(id, user);
	}
	
	private void logIn() {
		String id = input("ID");
		String pw = input("PW");
		
		for(String key: this.userList.keySet()) {
			User user = this.userList.get(key);
			if(key.equals(id) && user.getUserPw().equals(pw)) {
				this.log = true;
				this.curUser = user;
				System.out.printf("%s님 환영합니다.\n", user.getUserName());
			}
		}
	}
	
	private void connect() {
		if(this.log) {
			GamePlayer player = new GamePlayer();
			player.run();
		}
	}
	
	private void logOut() {
		this.log = false;
		this.curUser = null;
		
		System.out.println("정상적으로 로그아웃 되었습니다.");
	}
	
	private void leave() {
		String id = input("ID");
		String pw = input("PW");
		
		for(String key: userList.keySet()) {
			if(key.equals(id) && this.userList.get(key).getUserPw().equals(pw)) {
				this.log = false;
				this.curUser = null;
				this.userList.remove(key);
				System.out.println("탈퇴 처리가 완료되었습니다.");
			}
		}	
	}
}