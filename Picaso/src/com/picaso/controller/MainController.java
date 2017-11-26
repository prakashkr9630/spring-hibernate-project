package com.picaso.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.picaso.Util.Util;
import com.picaso.model.ImageMaster;
import com.picaso.model.Tag;
import com.picaso.service.InfoMasterService;

@Controller
public class MainController {

	@Autowired
	InfoMasterService masterservice;
	// for local image testing 
	// String path="C:/apache-tomcat-7.0.65/webapps/Picaso/staticfile";
	// for server
	static String path = "staticfile";
	
	
	
	//for file based operation , this should not be commented 
	static String localpath = "C:/apache-tomcat-7.0.65/webapps/Picaso/staticfile";
	int ratio = 2;
	int gal_ratio = 5;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("index");
		int count = masterservice.getCount();
		model.addObject("count", count);

		List<ImageMaster> list = masterservice.getList();

		Set<String> subnames = new HashSet<String>();
		for (ImageMaster img : list) {
			subnames.add(img.getSubname());
		}

		model.addObject("list", new ArrayList<String>(subnames));
		return model;
	}

	/**
	 * Method will get the subname and display the gallery corresponsing
	 * 
	 * @param subname
	 * @return
	 */
	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public ModelAndView displayGallery(@RequestParam("subname") String subname) {

		ModelAndView model = new ModelAndView("gallery");

		List<ImageMaster> list = masterservice.getList(subname);
		List<ImageMaster> modifiedList = new ArrayList<ImageMaster>();
		ImageMaster galImage = null;

		for (ImageMaster img : list) {
			galImage = new ImageMaster();
			galImage.setId(img.getId());
			galImage.setName(path + "/" + img.getName());
			galImage.setSubname(img.getSubname());
			galImage.setHeight(img.getHeight() / gal_ratio);
			galImage.setWidth(img.getWidth() / gal_ratio);
			modifiedList.add(galImage);
		}
		model.addObject("list", modifiedList);

		return model;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView("view");

		if (Integer.parseInt(id) == 0) {
			id = "100";
		}
		ImageMaster img = masterservice.getImage(Integer.parseInt(id));
		model.addObject("id", img.getId());
		model.addObject("image", path + "/" + img.getName());
		model.addObject("height", img.getHeight() / ratio);
		model.addObject("width", img.getWidth() / ratio);

		// get the remaing tag related to image
		List<String> tags = masterservice.getTag(Integer.parseInt(id));
		model.addObject("tags", tags);

		return model;
	}

	@RequestMapping(value = "/addtag", method = RequestMethod.POST)
	public ModelAndView addTag(@RequestParam("tag") String tag, @RequestParam("id") String id) {

		//System.out.println("tag value:"+tag);
		
		ModelAndView model = new ModelAndView("view");
		// for getting back to same page
		ImageMaster img = masterservice.getImage(Integer.parseInt(id));
		model.addObject("id", img.getId());
		model.addObject("image", path + "/" + img.getName());
		model.addObject("height", img.getHeight() / ratio);
		model.addObject("width", img.getWidth() / ratio);

		// get the remaing tag related to image
		List<String> tags = masterservice.getTag(Integer.parseInt(id));
		model.addObject("tags", tags);

		// tag save process
		Tag tagobj = new Tag();
		tagobj.setImgid(Integer.parseInt(id));
		tagobj.setTag(tag);
		
		// need to be uncommnet for save
		masterservice.saveTag(tagobj);
		return model;
	}

	@RequestMapping(value = "/searchtags", method = RequestMethod.POST)
	public ModelAndView searchTags(@RequestParam("searchtext") String searchtext) {
		ModelAndView model = new ModelAndView("search");

		// TODO: in the dao class image need to be fetched from master table

		List<String> tokens = new ArrayList<String>();
		if (searchtext.trim() != null && searchtext.trim().length() > 0) {

			StringTokenizer stk = new StringTokenizer(searchtext, ":");
			while (stk.hasMoreElements()) {
				tokens.add(stk.nextElement().toString().trim());
			}

			List<ImageMaster> list = masterservice.getImage(tokens);
			List<ImageMaster> viewlist = prepareView(list);

			if (list != null) {
				model.addObject("list", viewlist);
			} else {
				model.addObject("error", "No result found");
			}
		} else {
			model.addObject("error", "Seach tags not correct");
		}
		return model;
	}

	/**
	 * method will prepare list from masterImg to view on jsp, it will add img
	 * ration and path to image.
	 * 
	 * @param list
	 * @return
	 */
	private List<ImageMaster> prepareView(List<ImageMaster> list) {
		List<ImageMaster> newlist = new ArrayList<ImageMaster>();
		ImageMaster img = null;
		for (ImageMaster imgl : list) {
			img = new ImageMaster();
			img.setId(imgl.getId());
			img.setName(path + "/" + imgl.getName());
			img.setSubname(imgl.getSubname());
			img.setHeight(imgl.getHeight() / gal_ratio);
			img.setWidth(imgl.getWidth() / gal_ratio);
			newlist.add(img);
		}
		return newlist;
	}

	@RequestMapping(value = "/shownew", method = RequestMethod.GET)
	public ModelAndView showNew(HttpSession session) {
		ModelAndView model = new ModelAndView("addnew");

		// new list to be sent to screen
		List<String> newlist = new ArrayList<String>();

		// list from database
		List<ImageMaster> list = masterservice.getList();
		// list from extracting name from imagemaster list
		List<String> namelistdatabase = new ArrayList<String>();
		// this list is from current file directory
		List<String> curlist = Util.getImageList(localpath);

		for (ImageMaster im : list) {
			namelistdatabase.add(im.getName());
		}

		for (String s : curlist) {
			if (!namelistdatabase.contains(s)) {
				newlist.add(s);
			}
		}

		if (newlist.size() > 0) {
			session.setAttribute("newlist", newlist);
			model.addObject("count", newlist.size());
			model.addObject("image", newlist);
		} else {
			session.setAttribute("newlist", newlist);
			model.addObject("count", 0);
		}

		return model;
	}

	/**
	 * method will save the image information to database
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView uploadNew(HttpSession session) {

		// new list to be sent to screen
		List<String> newlist = new ArrayList<String>();

		// list from database
		List<ImageMaster> list = masterservice.getList();
		// list from extracting name from imagemaster list
		List<String> namelistdatabase = new ArrayList<String>();
		// this list is from current file directory
		List<String> curlist = Util.getImageList(localpath);

		for (ImageMaster im : list) {
			namelistdatabase.add(im.getName());
		}

		for (String s : curlist) {
			if (!namelistdatabase.contains(s)) {
				newlist.add(s);
			}
		}

		List<ImageMaster> Pnewlist = Util.prepareNewFileUpload(newlist, localpath);
		List<Integer> newID = masterservice.saveNew(Pnewlist);
		ModelAndView model = new ModelAndView("redirect:/view.html?id=" + newID.get(0));
		return model;
	}

}
