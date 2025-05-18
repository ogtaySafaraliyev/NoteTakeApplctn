package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Notes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title; 
	// after update
	@Lob
	@Column(columnDefinition = "LONGTEXT")
	private String description;
	
	private LocalDate date;
	
	@ManyToOne
	private User user;

}


