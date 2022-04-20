package dev.sassine.api.structure.delete.repository;

import dev.sassine.api.structure.delete.domain.HistPolicyInfoEntity;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<HistPolicyInfoEntity, Integer> { 

}