package com.project.hikes.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="users")
/*
 	Use NamedQuery for email search
 	
	@NamedQueries({
    @NamedQuery(name = "User.findByEmail", 
query = "SELECT u FROM User u WHERE u.email = :email")})
@ManagedBean(name="user")
*/
public class HikeUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name="email", unique = true)
	@NotNull(message="Email is required")
	//@Email(message="Email shoule be valid")
	private String email;
	
	@Column(name="password")
	@NotNull(message="Password is required")
    private String password;
	
	@Transient
	@NotNull(message="Confirm Password is required")
    private String confirmPassword;

	@Column(name="first_name")
	@NotNull(message="First Name is required")
	@Size(min=3, message="First Name must be at least 3 characters")
	private String firstName;
	
	@Column(name="last_name")
	@NotNull(message="Last Name is required")
	@Size(min=3, message="Last Name must be at least 3 characters")
	private String lastName;
	
	
	@ManyToMany(fetch=FetchType.EAGER, cascade={
			CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH
	})
	@JoinTable(name="user_role",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="role_id")
	)
	private Set<Role> roles;
	
	
	public HikeUser(){}

	public HikeUser(HikeUser user){
		this.userId = user.getUserId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.roles = user.getRoles();
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", roles=" + roles + "]";
	}

	//add role
	public void addUserRole(Role userRole){
		//(default USER role on User registration)
		if(roles==null){
			roles = new HashSet<Role>();
		}
		roles.add(userRole);
		
		//roles.add(new Role(Role.DEFAULT_ROLE));
	}
	
}
