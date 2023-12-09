package com.find.law.portal.repositories.entities.laws;

import com.find.law.portal.repositories.entities.punishments.PunishmentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

/**
 * Сущность БД для хранения наказания части закона.
 * Наказание части закона хранится в таблице LAW_PART_PUNISHMENTS.
 */
@Entity
@Table(name = "LAW_PART_PUNISHMENTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LawPartPunishEntity {
    /**
     * Идентификатор наказания части закона.
     * Так как наименование наказания не является уникальным, используется данный, автоматически увеличивающийся, идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    /**
     * Тип наказания.
     */
    @Column(name = "TYPE", nullable = false, length = 128)
    private String type;

    /**
     * Минимальный срок наказания.
     */
    @JoinColumn(name = "MIN_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private PunishmentEntity min;

    /**
     * Максимальный срок наказания.
     */
    @JoinColumn(name = "MAX_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private PunishmentEntity max;

    /**
     * Является ли наказание пожизненным.
     */
    @Column(name = "IS_LIFE_TIME", nullable = false)
    private Boolean isLifeTime;

    /**
     * Связь с частью закона к которому привязано данное наказание.
     */
    @JoinColumn(name = "PART_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private LawPartEntity part;

    /**
     *
     */
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<LawPartPunishEntity> optionals;

    public LawPartPunishEntity(long id, String type, PunishmentEntity min, PunishmentEntity max, Boolean isLifeTime, LawPartEntity part) {
        this.id = id;
        this.type = type;
        this.min = min;
        this.max = max;
        this.optionals = List.of();
        this.isLifeTime = isLifeTime;
        this.part = part;
    }
}
