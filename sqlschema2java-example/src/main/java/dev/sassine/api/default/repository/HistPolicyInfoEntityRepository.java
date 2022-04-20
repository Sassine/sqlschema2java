package dev.sassine.api.default.repository;

import dev.sassine.api.default.domain.HistPolicyInfoEntity;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistPolicyInfoEntityRepository extends JpaRepository<HistPolicyInfoEntity, Integer> { 

}