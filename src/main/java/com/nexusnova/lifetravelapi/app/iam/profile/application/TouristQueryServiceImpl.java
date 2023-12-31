package com.nexusnova.lifetravelapi.app.iam.profile.application;

import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.Tourist;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.queries.GetTouristByUserId;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.repositories.TouristRepository;
import com.nexusnova.lifetravelapi.app.iam.profile.service.TouristQueryService;
import com.nexusnova.lifetravelapi.app.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TouristQueryServiceImpl implements TouristQueryService {

    private final TouristRepository touristRepository;

    @Autowired
    public TouristQueryServiceImpl(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    @Override
    public Tourist handle(GetTouristByUserId query) {
        Optional<Tourist> tourist = touristRepository.findByUserId(query.userId());

        if(tourist.isEmpty()){
            throw new ResourceNotFoundException("Tourist user id cant be found");
        }
        return tourist.get();
    }
}
