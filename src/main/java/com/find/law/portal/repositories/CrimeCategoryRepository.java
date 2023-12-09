package com.find.law.portal.repositories;

import com.find.law.portal.repositories.entities.categories.CrimeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для CRUD операций с категориями преступлений.
 */
@Repository
public interface CrimeCategoryRepository extends JpaRepository<CrimeCategoryEntity, Long> {
}
