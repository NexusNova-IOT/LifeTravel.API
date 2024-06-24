package com.nexusnova.lifetravelapi.app.reporting.domain.repositories;

import com.nexusnova.lifetravelapi.app.reporting.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findByTourPackageIdAndDeletedIsFalse(Long tourPackageId);
    public List<Review> findByDeletedIsFalse();
}
