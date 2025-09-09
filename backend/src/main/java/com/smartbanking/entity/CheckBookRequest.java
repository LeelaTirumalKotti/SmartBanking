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
public class CheckBookRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	private String status;
	private String requestDate;
	@ManyToOne
	@JoinColumn(name="account_id")
	private BankAccount account;
	
}