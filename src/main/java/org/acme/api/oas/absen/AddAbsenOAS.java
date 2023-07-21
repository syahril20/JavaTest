package org.acme.api.oas.absen;

import org.acme.api.dto.AbsenDTO;
import org.acme.model.AbsenModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class AddAbsenOAS {

    @Schema(name = "AddLoginOAS.Request")
    public class Request extends AbsenDTO {
    }

    @Schema(name = "AddLoginOAS.Response")
    public static class Response {
        @Schema(description = "The status of the response", example = "Success")
        public String status;
        @Schema(description = "A message providing additional information about the response", example = "berhasil absen")
        public String message;
        @Schema(name = "data", description = "Absen")
        public AbsenModel absenModel;

        public Response(String status, String message, AbsenModel absenModel) {
            this.status = status;
            this.message = message;
            this.absenModel = absenModel;
        }

    }

    @Schema(name = "AddLoginOAS.BadRequest")
    public static class BadRequest {
        @Schema(example = "Bad Request", enumeration = {"BAD_REQUEST"})
        public String message;
    }
}
