package service.impl;

import java.util.List;

import dao.impl.PorderDaoImpl;
import model.Porder;
import service.PorderService;

public class PorderServiceImpl implements PorderService{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static PorderDaoImpl pdi=new PorderDaoImpl();

	@Override
	public void addPorder(Porder porder) {
		pdi.add(porder);
		
	}
	

	@Override
	public List<Porder> findAllPorder() {
	
		return pdi.selectAll();
	}

	@Override
	public boolean updatePorder(Porder porder) {
		Porder p=pdi.selectById(porder.getId());
		boolean x=false;
		if(p!=null)
		{
			x=true;
			pdi.update(porder);
		}
		
		return x;
	}

	@Override
	public boolean deletePorder(Porder porder) {
		Porder p=pdi.selectById(porder.getId());
		boolean x=false;
		if(p!=null)
		{
			x=true;
			pdi.delete(porder);
		}
		return x;
	}


	

}
