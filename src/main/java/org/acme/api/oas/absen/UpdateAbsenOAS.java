package org.acme.api.oas.absen;

import org.acme.api.dto.AbsenDTO;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class UpdateAbsenOAS {

    @Schema(name = "UpdateLoginOAS.Request")
    public class Request extends AbsenDTO {
    }

    @Schema(name = "UpdateLoginOAS.Response")
    public static class Response {
        @Schema(example = "Berhasil Absen Keluar")
        public String message ;

    }

    @Schema(name = "UpdateLoginOAS.BadRequest")
    public static class BadRequest {
        @Schema(example = "Bad Request", enumeration = {"BAD_REQUEST"})
        public String message;
    }
}
