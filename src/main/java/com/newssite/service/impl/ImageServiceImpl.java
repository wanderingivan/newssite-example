package com.newssite.service.impl;




import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.newssite.service.ImageService;
import com.newssite.util.ImageUtil;


/** 
 *	Implementation of ImageService
 *	that adds caching for images 
 *  and delegates image manipulation to ImageUtil
 *  @see ImageService
 *  @see  ImageUtil
 *
 */
public class ImageServiceImpl implements ImageService {
	
	private static Logger logger = Logger.getLogger(ImageServiceImpl.class);
	
	private String placeholderFilename;
	private ImageUtil imageUtil;
	
	@Autowired
	public ImageServiceImpl(ImageUtil imageUtil,String placeholderFilename){
		super();
		this.imageUtil = imageUtil;
		this.placeholderFilename = placeholderFilename;
	}
	
	/**
	 * Uses image util to return an image converted as a b64 string.<br/>
	 * Tries to resolve missing files by returning a placeholder
	 * image
	 */
	@Override
	@Cacheable(value="b64", key="#path")
	public String getB64(String path){
		try{
			return imageUtil.encodeToB64String(path);
		}catch(IOException missingFile){
			try{
			    logger.error("Cant open file " + path);
			    return imageUtil.encodeToB64String(placeholderFilename);
			}catch(IOException ignore){
				throw new IllegalArgumentException("Can't open placeholder file ");
			}
		}
	}


	

	@Override
	public String saveImage(File file, String contentType, String fileName) throws IOException{
		 return imageUtil.saveImage(file, contentType, fileName);
	}

	/**
	 * Uses image util to return an image <br/>
	 * Tries to resolve missing files by returning a placeholder
	 * image
	 */
    @Override
	@Cacheable(value="image", key="#path")
    public File loadImage(String path) throws IOException{
		try{
			return imageUtil.loadImage(path);
		}catch(IOException missingFile){
			try{
			    logger.error("Cant open file " + path);
			    return imageUtil.loadImage(placeholderFilename);
			}catch(IOException ignore){
				throw new IllegalArgumentException("Can't open placeholder file ");
			}
		}
    }
	
}
