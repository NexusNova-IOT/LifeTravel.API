package com.nexusnova.lifetravelapi.app.core.tours.domain.repositories;

import com.nexusnova.lifetravelapi.app.core.tours.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s where s.deleted=false and s.tourPackage.id=:packageId")
    List<Schedule> finByPackageId(@Param("packageId") Long packageId);
    @Transactional
    @Modifying
    @Query("update Schedule s set s.deleted = true where s.tourPackage.id = :tourPackageId")
    void deleteAllByTourPackageId(@Param("tourPackageId") Long tourPackageId);
}
