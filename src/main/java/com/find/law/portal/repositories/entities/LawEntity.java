package com.find.law.portal.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "LAWS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LawEntity {
    @Id
    @Column(name = "ARTICLE", nullable = false, length = 4)
    private String article;

    @Column(name = "NAME", nullable = false, length = 1024)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "law", cascade = CascadeType.ALL)
    private List<LawPartEntity> parts;
}
