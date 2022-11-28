package com.find.law.portal.repositories;

import com.find.law.portal.repositories.entities.LawEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для CRUD операций с законами
 */
@Repository
public interface LawRepository extends JpaRepository<LawEntity, String> {
    @Query("SELECT l FROM LawEntity AS l WHERE LOWER(l.name) LIKE LOWER(:text)")
    List<LawEntity> searchAllByNameLike(@Param("text") String text);
}
