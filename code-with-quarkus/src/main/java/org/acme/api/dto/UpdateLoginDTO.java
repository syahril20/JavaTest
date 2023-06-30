package org.acme.api.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class UpdateLoginDTO {
    @Schema(required = true, example = "1")
    public long user_id;
}
