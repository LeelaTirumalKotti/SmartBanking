package com.example.cbs.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

//   public Role getRole(){
//       return this.role;
//   }
//
//   public String getUsername(){
//       return username;
//   }
//
//   public String getPassword(){
//       return password;
//   }

}
