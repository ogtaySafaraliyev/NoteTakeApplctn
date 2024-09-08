package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data                           //   (( i can't use data lombok features ))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "full_name")
	private String fullName;
	private String qualification;
	private String address;
	private String gender;
	private String email;
	private String password;
	private String role;
	
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getFullName() {
//	    return fullName;
//	}
//	public void setFullName(String fullName) {
//	    this.fullName = fullName;
//	}
//	public String getQualification() {
//		return qualification;
//	}
//	public void setQualification(String qualification) {
//		this.qualification = qualification;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public String getGender() {
//		return gender;
//	}
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getRole() {
//		return role;
//	}
//	public void setRole(String role) {
//		this.role = role;
//	}
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", fullName=" + fullName + ", qualification=" + qualification + ", address=" + address
//				+ ", gender=" + gender + ", email=" + email + ", password=" + password + ", role=" + role + "]";
//	}
	
	
	
	
	
	

}
