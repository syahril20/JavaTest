package org.acme.controller;

import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.api.dto.AddUserDTO;
import org.acme.api.oas.login.GetListLoginOAS;
import org.acme.api.oas.user.GetListUserOAS;
import org.acme.model.UserModel;
import org.acme.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject
    UserService userService;

    @GET
    @Path("/nik")
    @Operation(summary = "Get By Nik")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListUserOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListUserOAS.BadRequest.class)))
    })
    public Response findByNik(@QueryParam("nik") String nik) {
        JsonObject result = new JsonObject();
        result.put("data", userService.findByNik(nik));
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("/niks")
    public Response getByNik(@QueryParam("nik") String nik) {
        JsonObject result = new JsonObject();
        JsonObject payload = new JsonObject();

        if (!userService.getByNik(nik).isEmpty()){
            payload.put("nik", userService.getByNik(nik).get(0)[0]);
            payload.put("name", userService.getByNik(nik).get(0)[1]);
            result.put("Status", 200);
            result.put("Message", "Success");
            result.put("Payload", payload);
            return Response.ok().entity(result).build();
        }
        result.put("Status", 500);
        result.put("Message", "Failed");
        result.put("Payload", payload);
        return Response.ok().entity(result).build();
    }

    @GET
    @Operation(summary = "Get All User")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListUserOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListUserOAS.BadRequest.class)))
    })
    public Response getUserAll() {
        JsonObject result = new JsonObject();
        result.put("data", userService.getListAll());
        return Response.ok().entity(result).build();
    }

    @POST
    public Response insertUser(AddUserDTO userDTO){
        return Response.ok(userService.dataUser(userDTO)).build();
    }

}
