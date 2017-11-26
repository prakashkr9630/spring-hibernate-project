package com.picaso.dao;

import java.util.List;

import com.picaso.model.ImageMaster;
import com.picaso.model.Tag;

public interface InfoMasterDao {
	
	public int getCount();
	public List<ImageMaster> getList();
	public List<ImageMaster> getList(String name);
	public ImageMaster getImage(int id);
	
	//for save all new Entries in imageMaster
	public List<Integer> saveNew(List<ImageMaster> list);
	
	
	//for tag operations 
	public void saveTag(Tag tag);
	public List<String> getTag(int id);

	public List<ImageMaster> getImage(List<String> tags);
	
}
