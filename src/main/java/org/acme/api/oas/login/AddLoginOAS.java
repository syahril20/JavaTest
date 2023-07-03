package org.acme.api.oas.login;

import org.acme.api.dto.LoginDTO;
import org.acme.model.LoginModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class AddLoginOAS {

    @Schema(name = "AddLoginOAS.Request")
    public class Request extends LoginDTO {
    }

    @Schema(name = "AddLoginOAS.Response")
    public static class Response {
        @Schema(description = "The status of the response", example = "Success")
        public String status;
        @Schema(description = "A message providing additional information about the response", example = "berhasil absen")
        public String message;
        @Schema(name = "data", description = "Absen")
        public LoginModel loginModel;

        public Response(String status, String message, LoginModel loginModel) {
            this.status = status;
            this.message = message;
            this.loginModel = loginModel;
        }

    }

    @Schema(name = "AddLoginOAS.BadRequest")
    public static class BadRequest {
        @Schema(example = "Bad Request", enumeration = {"BAD_REQUEST"})
        public String message;
    }
}
