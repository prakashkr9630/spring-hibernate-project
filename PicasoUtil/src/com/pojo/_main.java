package com.pojo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class _main {

	static String path="C:/apache-tomcat-7.0.65/picasofile";
	
	public static void main(String[] args) {
		
		//reading up the file from folder
		List<String> files = getImageList();
	    List<ImageMaster> ls = getPojo(files);
	    
	    
	    /*for(ImageMaster ms : ls) {
	    	System.out.println(ms.getName()+"\t"+ms.getHeight()+"\t"+ms.getWidth()+"\t"+ms.getSubname()+"\t"+ms.getDate());
	    }*/
	    
	    
	    
		SessionFactory sessionfactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();		
		int j=0;
		for(ImageMaster ms : ls){
			  session.saveOrUpdate(ms);
			  System.out.println(++j);
		}
		session.getTransaction().commit();
		session.close();							    	    
		
		
	}

	private static List<String> getImageList() {
		File dir = new File(path);
	    String[] item = dir.list();	    	    
	    List<String> files = Arrays.asList(item);
		return files;
	}
	
	/**
	 * This will get the List of pojo
	 * @param files
	 * @return
	 */
	private static List<ImageMaster> getPojo(List<String> files) {
		List<ImageMaster> masterlist = new ArrayList<ImageMaster>(); 
		ImageMaster master = null;
		
		
		for(String name: files){
			master =  new ImageMaster();
			master.setName(name);
			master.setSubname(getSubname(name));
			master.setHeight(getHeight(name));
			master.setWidth(getWidth(name));			
			masterlist.add(master);			
			
		}				
		return masterlist;
	}
	
	/**
	 * get Height of image
	 * @param name
	 * @return
	 */
	private static int getHeight(String name) {
		int height=0;
		try {
			BufferedImage bimg =  ImageIO.read(new File(path+"/"+name));
			height = bimg.getHeight();
			System.out.println(name+": height:\t"+height);
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
	private static int getWidth(String name) {
		int width=0;
		try {
			BufferedImage bimg =  ImageIO.read(new File(path+"/"+name));
			width = bimg.getWidth();
			System.out.println(name+": width:\t"+width);
			bimg.flush();			
		} catch (IOException e) {
			System.out.println("Error while readin width of image:"+name);
			e.printStackTrace();
		}
		return width;
	}

	/**
	 * Will get subname
	 * @param name
	 * @return
	 */
	private static String getSubname(String name) {
		return name.replaceAll("[0-9_.'jpg']", "");
	}
}
