package com.nexusnova.lifetravelapi.app.reporting.domain.repositories;

import com.nexusnova.lifetravelapi.app.reporting.domain.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByAgencyIdAndDeletedIsFalse(Long agencyId);
    List<Report> findByDeletedIsFalse();
}
