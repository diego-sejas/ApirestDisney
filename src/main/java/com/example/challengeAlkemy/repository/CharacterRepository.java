
package com.example.challengeAlkemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.challengeAlkemy.entity.Character;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Mariela
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
       
    List<Character> findByAge (Integer age);
        
    List<Character> findByMoviesId( Long idMovie);
       
     @Query ("SELECT c FROM Character c WHERE c.name LIKE CONCAT('%', :dato,'%') ")
    List<Character>findByName(@Param("dato")String name );
}
