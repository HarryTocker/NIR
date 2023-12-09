package com.find.law.portal.repositories.entities.laws;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

/**
 * Сущность БД для хранения закона.
 * Закон хранится в таблице LAWS.
 */
@Entity
@Table(name = "LAWS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LawEntity {
    /**
     * Статья закона, является ключом (ID) объекта в базе данных.
     */
    @Id
    @Column(name = "ARTICLE", nullable = false, length = 8)
    private String article;

    /**
     * Наименование закона.
     */
    @Column(name = "NAME", nullable = false, length = 1024)
    private String name;

    /**
     * Тип категории преступления (умышленное / по неосторожности)
     */
    @EqualsAndHashCode.Exclude
    @Column(name = "TYPE", nullable = false, length = 64)
    private String type;

    /**
     * Коллекция всех частей статьи закона.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "law", cascade = CascadeType.ALL)
    private Collection<LawPartEntity> parts;
}
