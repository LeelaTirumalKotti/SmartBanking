package com.smartbanking.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	private String firstName;
	private String lastName;
	@Column(unique=true,nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	private String mobile;
	private String dob;
	@ManyToOne
	@JoinColumn(name="branch_id")
	private Branch branch;
}