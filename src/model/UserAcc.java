package model;

public class UserAcc {
	
	public static final String G_MALE ="M";
	public static final String G_FEMALE = "F";
	    
	public String email;
	private String gender = G_MALE;
	private String password;
	   
	public UserAcc(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
       return email;
	}
 
	public void setEmail(String email) {
       this.email = email;
	}
 
	public String getGender() {
		return gender;
	}
 
	public void setGender(String gender) {
		this.gender = gender;
	}
 
	public String getPassword() {
	   return password;
	}
 
	public void setPassword(String password) {
	   this.password = password;
	}
}
