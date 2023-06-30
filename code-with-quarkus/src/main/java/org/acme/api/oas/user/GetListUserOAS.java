package org.acme.api.oas.user;

import org.acme.model.UserModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

public class GetListUserOAS {
    @Schema(name = "GetListUserOAS.Request")
    public class Request extends GetListUserOAS{
    }

    @Schema(name = "GetListUserOAS.Response")
    public class Response{
        @Schema(name = "data", description = "list of user")
        private List<UserModel> users;

        public Response(List<UserModel> users) {
            this.users = users;
        }

        public List<UserModel> getUsers() {
            return users;
        }

        public void setUsers(List<UserModel> users) {
            this.users = users;
        }
        }

        @Schema(name = "GetListUserOAS.BadRequest")
        public class BadRequest {
            @Schema(example = "Bad Request", enumeration = {"BAD_REQUEST"})
            public String message;
        }
    }
