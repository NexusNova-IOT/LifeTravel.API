package com.nexusnova.lifetravelapi.app.logging.identity.mapper;

import com.nexusnova.lifetravelapi.app.logging.identity.domain.model.User;
import com.nexusnova.lifetravelapi.app.logging.identity.resources.summaries.UserSummaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IdentityMapper {
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "name", source = "entity.googleName"),
            @Mapping(target = "role", source = "entity.role.name"),
    })
    UserSummaryDto userToSummaryDto(User entity);


}
