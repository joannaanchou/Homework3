package dao;

import model.Member;

public interface MemberDao {

	
	//create
	void add(Member member);
	
	//read
	Member select(String username,String password); //判斷帳密是否錯誤
	Member select(String username); //判斷帳號是否重複
	
	
	//update
	
	
	
	//delete
}
