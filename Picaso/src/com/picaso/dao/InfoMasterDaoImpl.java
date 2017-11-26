package com.picaso.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.picaso.model.ImageMaster;
import com.picaso.model.Tag;

@Repository("InfoMasterDao")
public class InfoMasterDaoImpl implements InfoMasterDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int getCount() {
		// String sqlquery = "select distinct(*) from ImageMaster";
		// String sqlquery = "from ImageMaster";
		String sqlquery = "select id,name,subname,height,width from ImageMaster group by subname";
		Query query = sessionFactory.getCurrentSession().createQuery(sqlquery);
		List<ImageMaster> list = query.list();
		return list.size();
	}

	@Override
	public List<ImageMaster> getList() {

		String sqlquery = "from ImageMaster";
		Query query = sessionFactory.getCurrentSession().createQuery(sqlquery);
		List<ImageMaster> list = query.list();
		return list;
	}

	@Override
	public List<ImageMaster> getList(String name) {

		String sqlquery = "from ImageMaster where subname='" + name + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(sqlquery);
		List<ImageMaster> list = query.list();
		return list;

	}

	@Override
	public ImageMaster getImage(int id) {
		return (ImageMaster) sessionFactory.getCurrentSession().get(ImageMaster.class, id);
	}

	@Override
	public void saveTag(Tag tag) {
		sessionFactory.getCurrentSession().saveOrUpdate(tag);

	}

	@Override
	public List<String> getTag(int id) {

		List<String> taglist = new ArrayList<String>();
		/* String sqlquery = "from Tag where Imgid='"+id+"'"; */
		String sqlquery = "from Tag where Imgid=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(sqlquery);
		List<Tag> list = query.list();

		for (Tag tag : list) {
			taglist.add(tag.getTag());
		}
		return taglist;
	}

	@Override
	public List<ImageMaster> getImage(List<String> tags) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < tags.size(); i++) {
			if (i < (tags.size() - 1)) {
				sb.append("tag like '%" + tags.get(i).trim() + "%' " + "or ");
			} else {
				sb.append("tag like '%" + tags.get(i).trim() + "%' ");
			}
		}
		String sqlquery = "from ImageMaster where id = any(select Imgid from Tag where "+sb.toString()+")";
		Query  query = sessionFactory.getCurrentSession().createQuery(sqlquery);
		List<ImageMaster>  list = query.list();

		return list;
	}

	@Override
	public List<Integer> saveNew(List<ImageMaster> list) {
		// TODO Auto-generated method stub
		List<Integer> newID = new ArrayList<Integer>();
		
		for(ImageMaster image: list){
			newID.add((Integer)sessionFactory.getCurrentSession().save(image));
		}
		sessionFactory.getCurrentSession().close();
		return newID;
	}

}
