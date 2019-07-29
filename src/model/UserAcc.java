package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import interfaces.Accounts;

public class UserAcc implements Accounts {
	
	public static final String G_MALE ="M";
	public static final String G_FEMALE = "F";
	    
	public String email;
	public String gender = G_MALE;
	public String password;
	   
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
	
	public void	setAccount(String json) {
		try(
			ObjectOutputStream setAcc = new ObjectOutputStream(new FileOutputStream("D:\\java\\review\\pwater\\WebContent\\public\\accounts.json"))
		){	
			setAcc.writeObject(json);
		} catch (IOException i) {
            i.printStackTrace();
        }
	}
	
	public String getAccount() {
		String account = null;
		try (
			ObjectInputStream getAcc = new ObjectInputStream(new FileInputStream("D:\\java\\review\\pwater\\WebContent\\public\\accounts.json")); 
		){
			account = (String) getAcc.readObject();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return account;
	}

}
