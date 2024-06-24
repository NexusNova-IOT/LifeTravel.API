package com.nexusnova.lifetravelapi.app.iam.identity.application;

import com.nexusnova.lifetravelapi.app.iam.identity.domain.model.User;
import com.nexusnova.lifetravelapi.app.iam.identity.domain.queries.GetUserByIdQuery;
import com.nexusnova.lifetravelapi.app.iam.identity.domain.repositories.UserRepository;
import com.nexusnova.lifetravelapi.app.iam.identity.service.UserQueryService;
import com.nexusnova.lifetravelapi.app.shared.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + query.id()));
    }
}
