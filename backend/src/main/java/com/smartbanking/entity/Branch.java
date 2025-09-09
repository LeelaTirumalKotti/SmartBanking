package com.smartbanking.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Branch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	private String name;
	private String city;
	private String address;
}