package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PorderDao;
import model.Porder;
import util.DbConnection;

public class PorderDaoImpl implements PorderDao{

	public static void main(String[] args) {
		/*Porder porder=new Porder("Guest01", 3,1,2);
		new PorderDaoImpl().add(porder);*/
		
		/*List<Porder> sales=new PorderDaoImpl().selectAll();
		int sum=0;
		int count=0;
		
		for(Porder o:sales)
		{
			System.out.println(o.getId()+"\t姓名:"+o.getName()+"\t單日票："+o.getDaily()+"\t季票："+o.getSeason()+"\t年票："+o.getAnnual());
			sum=sum+o.getDaily()+o.getSeason()+o.getAnnual();
			count++;
		}
		System.out.println("========================================");
		System.out.println("總訂單數："+count+"\t總購買票數："+sum);*/
		
		/*System.out.println(new PorderDaoImpl().selectById(2));*/
		
		/*Porder porder=new PorderDaoImpl().selectById(3);
		porder.setDaily(50);
		
		new PorderDaoImpl().update(porder);*/
		
		Porder porder=new PorderDaoImpl().selectById(3);
		new PorderDaoImpl().delete(porder);
		
		
	}
	
	private static Connection conn=DbConnection.getDb();

	@Override
	public void add(Porder porder) {
		String sql="insert into porder(name,daily,season,annual) values (?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,porder.getName());
			ps.setInt(2,porder.getDaily());
			ps.setInt(3,porder.getSeason());
			ps.setInt(4,porder.getAnnual());
			
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Porder> selectAll() {
		String sql="select*from porder";
		List<Porder> sales=new ArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Porder porder=new Porder();
				porder.setId(rs.getInt("id"));
				porder.setName(rs.getString("name"));
				porder.setDaily(rs.getInt("daily"));
				porder.setSeason(rs.getInt("season"));
				porder.setAnnual(rs.getInt("annual"));
				
				sales.add(porder);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}

	@Override
	public Porder selectById(int id) {
		String sql="select*from porder where id=?";
		Porder porder=null;
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				porder=new Porder();
				porder.setId(rs.getInt("id"));
				porder.setName(rs.getString("name"));
				porder.setDaily(rs.getInt("daily"));
				porder.setSeason(rs.getInt("season"));
				porder.setAnnual(rs.getInt("annual"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return porder;
	}

	@Override
	public void update(Porder porder) {
		String sql="update porder set daily=?, season=?, annual=? where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1,porder.getDaily());
			ps.setInt(2,porder.getSeason());
			ps.setInt(3,porder.getAnnual());
			ps.setInt(4,porder.getId());
			
			ps.executeUpdate();
	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Porder porder) {
		String sql="delete from porder where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, porder.getId());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}
