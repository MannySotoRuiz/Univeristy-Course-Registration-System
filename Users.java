import java.util.ArrayList;

public class Users implements java.io.Serializable {

	String username;
	String password;
	String firstName;
	String lastName;
	
	public Users (){}
	
	public Users (String user, String pw, String fName, String lName) {
		username = user;
		password = pw;
		firstName = fName;
		lastName = lName;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void viewAllCourses() {
		
	}
	
	
}
