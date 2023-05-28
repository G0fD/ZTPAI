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
@Table(name = "user_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String email;

    @Column
    private String name;

    @Column
    private String surname;

    @OneToOne(mappedBy = "details", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_gender", nullable = false)
    private Gender gender;

    @ManyToMany
    @JoinTable(
            name = "user_preferences",
            joinColumns = { @JoinColumn(name = "id_ud")},
            inverseJoinColumns = {@JoinColumn(name = "id_gender")}
    )
    Set<Gender> preferences = new HashSet<>();
}
