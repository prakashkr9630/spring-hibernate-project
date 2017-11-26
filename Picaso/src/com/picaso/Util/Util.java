package com.picaso.Util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import com.picaso.model.ImageMaster;
import com.sun.istack.internal.logging.Logger;

/**
 * utility class for all work
 * 
 * @author user
 *
 */
public class Util {
	
	/**
	 * Class to make ImageMaster pojo out of provided new list of file to save in the database  
	 * @param list
	 * @return
	 */
	
	public static List<ImageMaster> prepareNewFileUpload(List<String> list,String path) {
		List<ImageMaster> newpojolist = new ArrayList<ImageMaster>();
		ImageMaster image = null;
		
		for(String name: list){
			image= new ImageMaster();
			//image.setName(path + "/" + name);
			image.setName(name);
			image.setSubname(getSubname(name));
			image.setWidth(getWidth(name,path));
			image.setHeight(getHeight(name, path));
			newpojolist.add(image);
		}
		return newpojolist;
	}
	
	/**
	 * Method will get subname(remove all int and _ )
	 * @param name
	 * @return
	 */
	private static String getSubname(String name) {
		return name.replaceAll("[0-9_.'jpg']", "");
	}
	
	/**
	 * get Height of image
	 * @param name
	 * @return
	 */
	private static int getHeight(String name,String path) {
		int height=0;
		try {
			BufferedImage bimg =  ImageIO.read(new File(path+"/"+name));
			height = bimg.getHeight();
			//System.out.println(name+": height:\t"+height);
			bimg.flush();			
		} catch (IOException e) {
			System.out.println("Error while readin height of image:"+name);
			e.printStackTrace();
		}
		return height;
	}

	/*
	 * Get width of image	
	 */
	private static int getWidth(String name,String path) {
		int width=0;
		try {
			BufferedImage bimg =  ImageIO.read(new File(path+"/"+name));
			width = bimg.getWidth();
			//System.out.println(name+": width:\t"+width);
			bimg.flush();			
		} catch (IOException e) {
			System.out.println("Error while readin width of image:"+name);
			e.printStackTrace();
		}
		return width;
	}
	
	/**
	 * list all the image name in the folder 
	 * @param path
	 * @return
	 */
	public static List<String> getImageList(String path) {
		List<String> files = null;
		File dir = new File(path);

	    String[] item = dir.list();
	    if(item!=null){
	    	 files = Arrays.asList(item);
	    }   
		return files;
	}

}
