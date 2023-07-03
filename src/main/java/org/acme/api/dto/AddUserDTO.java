package org.acme.api.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class AddUserDTO {
    @Schema(required = true, example = "Haikal")
    public String name;

    @Schema(required = true, example = "32123021382728")
    public String nik;
}
