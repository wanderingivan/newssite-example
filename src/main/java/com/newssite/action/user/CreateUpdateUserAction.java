package com.newssite.action.user;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.newssite.exception.DuplicateEmailException;
import com.newssite.exception.DuplicateUsernameException;
import com.newssite.model.User;
import com.newssite.service.ImageService;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class CreateUpdateUserAction extends AbstractUserAction implements ModelDriven<User>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4204661969822824472L;
	
	private static Logger logger = Logger.getLogger(CreateUpdateUserAction.class);

	private ImageService imageService;
	
	private File profilePic;
	
	private String profilePicContentType,
				   profilePicFileName;
	
	private User user = new User();

	
	@Action(value="createUser",results={@Result(name="success", type="redirectAction", params={"actionName","loadUser","username","${username}"}),
										@Result(name="input", type="tiles", location="userCreateLayout")})
	public String createUser(){
		
		try{
			if(!validatePassword(user.getPassword())){
				addFieldError("user.password",getText("global.invalid_password"));
				return INPUT;
			}
			logger.info("Creating user " + user );
			if(profilePic != null){
				imageService.saveImage(profilePic,profilePicContentType,profilePicFileName);
				user.setImagePath(profilePicFileName);
			}
			service.createUser(user);
			return SUCCESS;

		}catch(DuplicateEmailException de){

			addFieldError("user.email", getText("global.duplicate_email"));
			return INPUT;

		}catch(DuplicateUsernameException de){
			
			addFieldError("user.username", getText("global.duplicate_username"));
			return INPUT;
			
		}catch(Exception e){
			logger.error("Error creating user " + user + "\n" + e);
		}
		return ERROR;
	}

	@Action(value="editUser",results={@Result(name="success", type="redirectAction", params={"actionName","loadUser","username","${username}"}),
								      @Result(name="input", type="tiles", location="editUserInput")})
	public String editUser(){
		
		try{
			logger.info(String.format("Editing user "+ user));
			if(profilePic != null){
				imageService.saveImage(profilePic,profilePicContentType,profilePicFileName);
				user.setImagePath(profilePicFileName);
			}
			service.editUser(user);
			return SUCCESS;
			
		}catch(DuplicateEmailException de){

			addFieldError("user.email", getText("global.duplicate_email"));
			return INPUT;

		}catch(DuplicateUsernameException de){
			
			addFieldError("user.username", getText("global.duplicate_username"));
			return INPUT;
			
		}catch(Exception e){
			logger.error("Error updating user " + user + "\n" + e);
		}
		return ERROR;
		
	}

	public File getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(File profilePic) {
		this.profilePic = profilePic;
	}

	public String getProfilePicContentType() {
		return profilePicContentType;
	}

	public void setProfilePicContentType(String profilePicContentType) {
		this.profilePicContentType = profilePicContentType;
	}

	public String getProfilePicFileName() {
		return profilePicFileName;
	}

	public void setProfilePicFileName(String profilePicFileName) {
		this.profilePicFileName = profilePicFileName;
	}
	
	@Autowired
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}
	
	@Override
	public User getModel() {
		return user;
	}

	@VisitorFieldValidator(appendPrefix=true)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	private boolean validatePassword(String password) {
		if(password == null){
			addFieldError("user.password",getText("global.field_required"));
			return false;
		}else if(password.length() < 6 || password.length() > 50){
			addFieldError("user.password",getText("global.invalid_password"));
			return false;
		}else{
			return true;
		}
	}
	
}
