package com.nexusnova.lifetravelapi.app.core.tours.domain.repositories;

import com.nexusnova.lifetravelapi.app.core.tours.domain.model.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {

    @Query("select tp from TourPackage tp where tp.deleted=false and tp.region.id =:regionId AND tp.visible=true")
    List<TourPackage> findByRegionId(@Param("regionId") Long regionId);
    List<TourPackage> findAllByVisibleIsTrue();
    List<TourPackage> findByAgencyId(Long agencyId);
    List<TourPackage> findByAgencyIdAndVisible(Long agencyId, boolean visible);
    List<TourPackage> findByIdIn(List<Long> ids);
}