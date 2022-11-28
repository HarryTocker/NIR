package com.find.law.portal.repositories;

import com.find.law.portal.repositories.entities.LawPartPunishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawPartPunishRepository extends JpaRepository<LawPartPunishEntity, Long> {

}
