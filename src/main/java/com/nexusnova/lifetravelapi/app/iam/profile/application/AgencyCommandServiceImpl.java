package com.nexusnova.lifetravelapi.app.iam.profile.application;

import com.nexusnova.lifetravelapi.app.iam.identity.domain.model.User;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.commands.CreateAgencyCommand;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.commands.UpdateAgencyPhotoCommand;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.Agency;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.repositories.AgencyRepository;
import com.nexusnova.lifetravelapi.app.iam.profile.resources.requests.AgencyRequestDto;
import com.nexusnova.lifetravelapi.app.iam.profile.service.AgencyCommandService;
import com.nexusnova.lifetravelapi.app.shared.util.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class AgencyCommandServiceImpl implements AgencyCommandService {

    private final AgencyRepository agencyRepository;
    private final ValidationUtil validationUtil;

    public AgencyCommandServiceImpl(AgencyRepository agencyRepository,
            ValidationUtil validationUtil) {
        this.agencyRepository = agencyRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public Agency handle(UpdateAgencyPhotoCommand updateAgencyPhotoCommand) {
        return null;
    }

    @Override
    public Agency handle(CreateAgencyCommand command) {
        Agency agency = new Agency();
        User user = validationUtil.findUserById(command.id());
        AgencyRequestDto requestDto = command.agencyRequestDto();

        agency.setUser(user);
        agency.setDeleted(user.getDeleted());
        agency.setLegalName(requestDto.getLegalName());
        agency.setRUC(requestDto.getRUC());
        agency.setAddress(requestDto.getAddress());
        agency.setAgencyPhotoUrl(requestDto.getAgencyPhotoUrl());
        agency.setDescription(requestDto.getDescription());
        agency.setPhoneNumber(requestDto.getPhoneNumber());
        agency.setWebPageUrl(requestDto.getWebPageUrl());

        agencyRepository.save(agency);
        return agency;
    }
}
