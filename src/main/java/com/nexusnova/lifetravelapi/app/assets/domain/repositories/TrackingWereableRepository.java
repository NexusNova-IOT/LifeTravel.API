package com.nexusnova.lifetravelapi.app.assets.domain.repositories;

import com.nexusnova.lifetravelapi.app.assets.domain.model.TrackingWearable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrackingWereableRepository extends JpaRepository<TrackingWearable, Long> {

    @Query("select b.touristUser.id " +
            "from Booking b " +
            "where b.deleted=false and b.tourPackage.id=:packageId and b.selectedDate between :yesterday and :tomorrow")
    List<String> findUsersofDay(@Param("yesterday") Date yesterday,
                                @Param("tomorrow") Date tomorrow,
                                @Param("packageId") Long packageId);

    @Query("SELECT b.tourPackage.id " +
            "FROM Booking b " +
            "WHERE b.deleted = false AND b.agencyUser.id = :agencyUserId AND b.selectedDate >= :today " +
            "ORDER BY b.selectedDate ASC")
    List<Long> findFirstBooking(@Param("today") Date today,
                                    @Param("agencyUserId") String agencyUserId);


    @Query("select b " +
            "from TrackingWearable b " +
            "where b.deleted=false and b.touristUser.id=:touristUserId")
    TrackingWearable findGPS(@Param("touristUserId") String touristUserId);
}
