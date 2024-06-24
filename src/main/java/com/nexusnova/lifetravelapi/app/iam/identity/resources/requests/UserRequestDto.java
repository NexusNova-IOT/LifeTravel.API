package com.nexusnova.lifetravelapi.app.iam.identity.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(description="User (Request)")
public class UserRequestDto {
    @Schema(description="User Id from Firebase")
    private String id;
    @Schema(description="User Name")
    private String name;
    @Schema(description="User Email")
    private String email;
    @Schema(description="User Photo URL")
    private String photoUrl;
}
