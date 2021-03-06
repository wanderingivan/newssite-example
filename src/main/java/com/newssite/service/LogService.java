package com.newssite.service;

import java.io.IOException;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 *	Service Layer Interface 
 *  providing log operations
 *
 */
public interface LogService {

	/**
	 * Retrieve a log as a list of strings
	 * @param logFileName the file to load
	 * @return List of all lines in the log
	 * @throws IOException
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	String getLog(String logFileName) throws IOException;
	
	

}
