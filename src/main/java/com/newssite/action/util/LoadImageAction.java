package com.newssite.action.util;


import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.service.ImageService;


@ParentPackage("util")
public class LoadImageAction implements ServletRequestAware{

	private ImageService service;
	
	String path;
	
	private HttpServletRequest servletRequest;

	
	@Action(value="loadImage",results={@Result(name="ImageResult", type="imageBytesResult",params={"contentType","${imageContentType}",
																									"contentDisposition","${imageContentDisposition}"})})
	public String execute(){
		return "ImageResult";
	}
	
	public File getImage() throws IOException{
	    return service.loadImage(path);
	}
	public String getImageContentType(){
		return "image/jpeg";
	}
	
	public String getImageContentDisposition() {
		return "anyname.jpeg";
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.servletRequest = arg0;
	}
	
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String imagePath) {
		this.path = imagePath;
	}

	@Autowired
	public void setService(ImageService service) {
		this.service = service;
	}
}