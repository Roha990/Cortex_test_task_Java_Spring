package com.guide.first_site_spring.repo;

import com.guide.first_site_spring.models.Position;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, Long> {
}
