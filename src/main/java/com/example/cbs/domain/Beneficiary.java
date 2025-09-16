package com.example.cbs.domain;
import com.example.cbs.domain.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "beneficiaries")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String beneficiaryName;

    private String accountNumber;

    private String bankName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
