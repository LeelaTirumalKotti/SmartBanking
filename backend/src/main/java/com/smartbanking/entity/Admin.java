package com.smartbanking.entity;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
 
@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
   private long id;
   private String name;
   @Column(unique=true, nullable=false)
   private String email;
   @Column(nullable=false)
   private String password;
   
}