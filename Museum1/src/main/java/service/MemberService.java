package service;

import model.Member;

public interface MemberService {

	//create
		boolean addMember(Member member);//註冊時判斷帳號是否重複
		
		//read
		Member login(String username,String password);//登入時判斷帳密是否正確
		
		//update
		
		
		//delete
}
