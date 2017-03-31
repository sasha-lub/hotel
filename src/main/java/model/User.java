package model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Sasha
 *
 */

@Entity
@Table(name = "users")
@Access( AccessType.FIELD )
public class User implements Serializable{

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String phone;

	@Enumerated(value = EnumType.STRING)
	private Role role;

	@Column(nullable = false)
	private String password;

	public User(){	
	}

	public User(String email, String name, String phone, String password) {
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", role=" + role
				+ ", password=" + password + "]";
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
