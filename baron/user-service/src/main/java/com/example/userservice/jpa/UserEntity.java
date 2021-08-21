package com.example.userservice.jpa;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false, length = 50)
        private String email;
        @Column(nullable = false, length = 50, unique = true)
        private String name;
        @Column(nullable = true, unique = true)
        private String userId;
        @Column(nullable = true, unique = true)
        private String encryptedPwd;
}
