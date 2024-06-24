package com.nexusnova.lifetravelapi.app.iam.profile.domain.repositories;

import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TouristRepository extends JpaRepository<Tourist, Long> {

    @Query("select t from Tourist t where t.deleted=false and t.user.id =:userId")
    Optional<Tourist> findByUserId(@Param("userId") String userId);
}
