package com.find.law.portal.repositories;

import com.find.law.portal.repositories.entities.laws.LawPartPunishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для CRUD операций с наказаниями по частям закона.
 */
@Repository
public interface LawPartPunishRepository extends JpaRepository<LawPartPunishEntity, Long> {

}
