package org.acme.api.oas.login;

import org.acme.model.LoginModel;
import org.acme.model.UserModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

public class GetListLoginOAS {
    @Schema(name = "GetListLoginOAS.Request")
    public class Request extends GetListLoginOAS{
    }

    @Schema(name = "GetListLoginOAS.Response")
    public class Response{
        @Schema(name = "data", description = "list of login")
        private List<LoginModel> logins;

        public Response(List<LoginModel> logins) {
            this.logins = logins;
        }

        public List<LoginModel> getLogins() {
            return logins;
        }

        public void setLogins(List<LoginModel> logins) {
            this.logins = logins;
        }
    }

    @Schema(name = "GetListLoginOAS.BadRequest")
    public class BadRequest {
        @Schema(example = "Bad Request", enumeration = {"BAD_REQUEST"})
        public String message;
    }
}
