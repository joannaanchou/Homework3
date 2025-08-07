package service;

import java.util.List;

import model.Porder;

public interface PorderService {

	
	//create
		void addPorder(Porder porder);
		
		//read
		List<Porder> findAllPorder();
		
		//update
		boolean updatePorder(Porder porder);
		
		//delete
		boolean deletePorder(Porder porder);
}
