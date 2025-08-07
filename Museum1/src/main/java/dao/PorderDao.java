package dao;

import java.util.List;

import model.Porder;

public interface PorderDao {

	
	//create
	void add(Porder porder);
	
	
	//read
	List<Porder>selectAll(); //用來查詢所有訂單
	Porder selectById(int id); //用來修改訂單、刪除訂單
	
	
	
	//update
	void update(Porder porder);
	
	
	//delete
	void delete(Porder porder);
}
