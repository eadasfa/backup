package spittr.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="spitter")
public class Spitter {
	@NotNull
	@Id
	@Size(min=5,max=16)
	@Column(name="username")
	private String userName;
	
	@NotNull
	@Size(min=5,max=25)
	@Column(name="password")
	private  String password;
	
	@NotNull
	@Size(min=2,max=30)
	@Column(name="firstname")
	private String firstName;
	
	@NotNull
	@Size(min=2,max=30)
	@Column(name="lastname")
	private String lastName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public String toString() {
		return "Spitter [userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}



	

	
	
	
}
