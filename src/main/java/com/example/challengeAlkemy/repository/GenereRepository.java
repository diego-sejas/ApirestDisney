
package com.example.challengeAlkemy.repository;

import com.example.challengeAlkemy.entity.Genere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mariela
 */
@Repository
public interface GenereRepository extends JpaRepository<Genere,Long>{
    @Query("SELECT g FROM Genere g LEFT JOIN g.movies m WHERE m.id = :id")
    Genere findgenereByMovie(@Param("id") Long id);
    
    Genere findByname(String name);
}
