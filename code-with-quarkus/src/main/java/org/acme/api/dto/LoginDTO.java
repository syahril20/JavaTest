package org.acme.api.dto;

import org.acme.model.UserModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class LoginDTO {

    @Schema(required = true, example = "1")
    public long user_id;


}
