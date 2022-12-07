package sadian.lisset.personaapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsJpaRepository extends JpaRepository<Skills, Integer> {

}

