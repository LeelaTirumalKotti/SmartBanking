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
public class BankAccount {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	@Column(unique=true,nullable=false)
	private String accountNumber;
	private String accountType;
	private double balance;
	private String status;
	private String atmPin;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
}