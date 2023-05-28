package com.company.shipify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_details", referencedColumnName = "id")
    private UserDetails details;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private Roles userRole;

    @OneToMany(mappedBy = "userLike")
    private Set<LikedBy> likedByUserSet;

    @ManyToMany
    @JoinTable(
            name = "matches",
            joinColumns = { @JoinColumn(name = "id_user1") },
            inverseJoinColumns = { @JoinColumn(name = "id_user2") }
    )
    Set<User> matchedUser1 = new HashSet<>();

    @ManyToMany(mappedBy = "matchedUser1")
    private Set<User> matchedUser2 = new HashSet<>();

}