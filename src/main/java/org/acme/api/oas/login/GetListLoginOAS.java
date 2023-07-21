package org.acme.api.oas.login;

import org.acme.model.AbsenModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

public class GetListLoginOAS {
    @Schema(name = "GetListLoginOAS.Request")
    public class Request extends GetListLoginOAS{
    }

    @Schema(name = "GetListLoginOAS.Response")
    public class Response{
        @Schema(name = "data", description = "list of login")
        private List<AbsenModel> logins;

    }

    @Schema(name = "GetListLoginOAS.BadRequest")
    public class BadRequest {
        @Schema(example = "Bad Request", enumeration = {"BAD_REQUEST"})
        public String message;
    }

    @Schema(name = "GetListLoginOAS.InternalError")
    public class InternalError {
        @Schema(example = "Internal Error", enumeration = {"InternalError"})
        public String message;
    }
}
