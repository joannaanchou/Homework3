package service.impl;

import dao.impl.MemberDaoImpl;
import model.Member;
import service.MemberService;

public class MemberServiceImpl implements MemberService{

	public static void main(String[] args) {
		/*Member m=new Member("Guest02", "guest02", "3333","台南市","0911763665");
		System.out.println(new MemberServiceImpl().addMember(m));*/
		
		System.out.println(new MemberServiceImpl().login("admin","0000"));

	}

	private static MemberDaoImpl mdi=new MemberDaoImpl();
	
	@Override
	public boolean addMember(Member member) {
		boolean isUsernameBeenUse=false;
		Member m=mdi.select(member.getUsername());
		
		if(m==null)
		{
			mdi.add(member);
			isUsernameBeenUse=true;
		}
		
		return isUsernameBeenUse;
	}

	@Override
	public Member login(String username, String password) {
		
		return mdi.select(username,password);
	}

}
