package com.find.law.portal.repositories.entities.categories;

import com.find.law.portal.repositories.entities.punishments.PunishmentEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CRIME_CATEGORIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CrimeCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "LAW_TYPE", nullable = false, length = 64)
    private String lawType;

    @Column(name = "CATEGORY_TYPE", nullable = false, length = 64)
    private String categoryType;

    @Column(name = "COMPARISON_TYPE", nullable = false, length = 64)
    private String comparisonType;

    @JoinColumn(name = "PUNISHMENT_ID", nullable = false, referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private PunishmentEntity punishment;
}
