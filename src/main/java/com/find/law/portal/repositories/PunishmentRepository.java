package com.find.law.portal.repositories;

import com.find.law.portal.repositories.entities.punishments.PunishmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для CRUD операций с наказаниями.
 */
@Repository
public interface PunishmentRepository extends JpaRepository<PunishmentEntity, Long> {
}
