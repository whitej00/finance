package com.nte.financedcore.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Long rating;

    @OneToMany(mappedBy = "user")
    List<Research> researchList = new ArrayList<>();

    public void addResearch(Research research){
        this.researchList.add(research);
    }

    @Builder
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
