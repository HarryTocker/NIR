package com.find.law.portal.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Сущность БД для хранения закона.
 * Закон хранится в таблице LAWS.
 */
@Entity
@Table(name = "LAWS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LawEntity {
    /**
     * Статья закона, является ключом (ID) объекта в базе данных.
     */
    @Id
    @Column(name = "ARTICLE", nullable = false, length = 4)
    private String article;

    /**
     * Наименование закона.
     */
    @Column(name = "NAME", nullable = false, length = 1024)
    private String name;

    /**
     * Коллекция всех частей статьи закона.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "law", cascade = CascadeType.ALL)
    private List<LawPartEntity> parts;
}
