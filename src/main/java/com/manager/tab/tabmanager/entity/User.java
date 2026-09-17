package com.manager.tab.tabmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users",
    uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
    })

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", length = 50, nullable = false)
    private String username;

    @Column(name="email", length = 50, nullable = false)
    private String email;

    @Column(name="password", length = 60, nullable = false)
    private String password;

    private String roles;
}
