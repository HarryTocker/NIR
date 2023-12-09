package com.find.law.portal.repositories;

import com.find.law.portal.repositories.entities.laws.LawPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для CRUD операций с частями закона.
 */
@Repository
public interface LawPartRepository extends JpaRepository<LawPartEntity, Long> {

}
