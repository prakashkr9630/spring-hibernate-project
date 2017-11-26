package com.picaso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.picaso.dao.InfoMasterDao;
import com.picaso.model.ImageMaster;
import com.picaso.model.Tag;

@Service("InfoMasterService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InfoMasterServiceImpl implements InfoMasterService{

	@Autowired
	InfoMasterDao masterDao;
	
	@Override
	public int getCount() {
		return masterDao.getCount();
	}

	@Override
	public List<ImageMaster> getList() {		
		return masterDao.getList();
	}

	@Override
	public List<ImageMaster> getList(String name) {
		return masterDao.getList(name);
	}

	@Override
	public ImageMaster getImage(int id) {
		return masterDao.getImage(id);
	}

	@Override
	public void saveTag(Tag tag) {
		masterDao.saveTag(tag);
	}

	@Override
	public List<String> getTag(int id) {		
		return masterDao.getTag(id);
	}

	@Override
	public List<ImageMaster> getImage(List<String> tags) {
		return masterDao.getImage(tags);
	}

	@Override
	public List<Integer> saveNew(List<ImageMaster> list) {
		// TODO Auto-generated method stub
		return masterDao.saveNew(list);
	}



	

	

}
