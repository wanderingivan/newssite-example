package com.newssite.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.newssite.service.LogService;



/**
 *	Basic implementation of LogService 
 * 	tries to load all logs from a default location.
 *  Uses a list of aliases for log names allowing simplified  
 *  names to be displayed in log selection views.<br/>
 *  Handles user permission checking 
 */
public class BasicLogService implements LogService {

	private final String defaultPath;
	private final Map<String,String> aliases;


	public BasicLogService(){
		defaultPath = null;
		aliases = null;
	}
	public BasicLogService(Map<String,String> aliases){
		super();
		try{
			defaultPath = System.getProperty("catalina.home").concat("/logs/");
		}catch(NullPointerException e){
			throw new RuntimeException("The basic log service must either run within a tomcat container\n or be initialized with path to a logs folder");
		}
		this.aliases = aliases;
	}
	
	public BasicLogService(Map<String,String> aliases,String path){
		super();
		defaultPath =path;
		this.aliases = aliases;
	}
	/**
	 * Tries to load a log file by first resolving an alias 
	 * and then looking for it in a default location.
	 * @param logAlias the alias to resolve and load
	 * @throws IllegalArgumenException if the alias is missing
	 * @throws IOException
	 */	
	@Override
	public String getLog(String logAlias) throws IOException{
		
		if(!aliases.containsKey(logAlias)){ throw new IllegalArgumentException("Missing alias.Did you add new logs without updating the alias file ?");}
		
		File logFile = new File(defaultPath,aliases.get(logAlias));
		
		if(!logFile.exists()){ throw new RuntimeException("The Log file is missing " + aliases.get(logAlias));}
		
		StringBuilder sb = new StringBuilder();
		BufferedReader buf = null;
		try{
			buf = new BufferedReader(new FileReader(logFile));
			String line = null;
			while((line = buf.readLine())!= null){
				sb.append(line);
				sb.append("\n");
			}	
		}finally{
			try{
				buf.close();
			}catch(IOException ignore){
				
			}
		}
		return sb.toString();
	}

}
