package com.sda.onlineAuction.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private UserRole userRole;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "winner", fetch = FetchType.EAGER)
    private List<Product> productsWon;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Bid> bidsList;

}
