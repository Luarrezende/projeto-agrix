package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.FarmModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Model Farm interface.
 */
@Repository
public interface FarmRepository extends JpaRepository<FarmModel, Long> {
}
