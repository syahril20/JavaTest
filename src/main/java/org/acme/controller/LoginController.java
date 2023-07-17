package org.acme.controller;

import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.api.dto.LoginDTO;
import org.acme.api.oas.login.AddLoginOAS;
import org.acme.api.oas.login.GetListLoginOAS;
import org.acme.api.oas.login.UpdateLoginOAS;
import org.acme.model.LoginModel;
import org.acme.service.LoginService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.reactive.RestPath;

import java.time.LocalDate;
import java.util.List;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    @Inject
    LoginService loginService;

    @POST
    @Operation(summary = "Absensi")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AddLoginOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AddLoginOAS.BadRequest.class)))
    })
    public Response login(@QueryParam("user_id") long user_id){
        JsonObject result = new JsonObject();

        result.put("status", "Success");
        result.put("message", "berhasil Absen");
        result.put("data", loginService.dataLogin(user_id));
        return Response.ok().entity(result).build();
    }

//    @POST
//    @Path("/loginNik")
//    @Operation(summary = "Absensi")
//    @APIResponses(value = {
//            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AddLoginOAS.Response.class))),
//            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AddLoginOAS.BadRequest.class)))
//    })
//    public Response loginNik(@QueryParam("nik") String nik){
//        JsonObject result = new JsonObject();
//
//        result.put("status", "Success");
//        result.put("message", "berhasil Absen");
//        result.put("data", loginService.persistLoginNik(nik));
//        return Response.ok().entity(result).build();
//    }

    @POST
    @Path("/waw")
    @Operation(summary = "Get All Date")
    public Response getUserAll() {
        JsonObject result = new JsonObject();
        try {
            // Logika bisnis Anda
            result.put("status", "Success");
            result.put("message", "berhasil Absen");
            result.put("data", loginService.getListAll());
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            // Tangani kesalahan internal (error 500)
            result.put("status", "Error");
            result.put("message", "Terjadi kesalahan internal: " + e.getMessage());

            // Tampilkan pesan kesalahan ke log
            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        }
    }



    @GET
    @Path("/datenow")
    @Operation(summary = "Get All Today")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListLoginOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListLoginOAS.BadRequest.class)))
    })
    public Response getDateNow() {
        JsonObject result = new JsonObject();
        result.put("data", loginService.findDateNow());
        return Response.ok().entity(result).build();
    }

    @PUT
    @Path("/{login_id}")
    @Operation(summary = "Update Absen")
    @APIResponses(value = {
            @APIResponse(responseCode = "200",description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UpdateLoginOAS.Response.class))),
            @APIResponse(responseCode = "400",description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UpdateLoginOAS.BadRequest.class)))
    })
    public Response updateLogin(@RestPath long login_id){
      loginService.updateLogin(login_id);
        return Response.ok("MANTAP").build();
    }


    @GET
    @Path("/date")
    @Operation(summary = "Get All By Parameter Date")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListLoginOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListLoginOAS.BadRequest.class)))
    })
    public Response getDate(@QueryParam("tanggal") LocalDate tanggal) {
        JsonObject result = new JsonObject();
        List<LoginModel> loginModels = loginService.findDate(tanggal);

        result.put("data", loginModels);
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("/dates")
    @Operation(summary = "Get All By Range Date")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListLoginOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListLoginOAS.BadRequest.class)))
    })
    public Response getDates(@QueryParam("start")LocalDate start, @QueryParam("end")LocalDate end) {
        JsonObject result = new JsonObject();
        List<LoginModel> loginModels = loginService.findDates(start, end);

        result.put("data", loginModels);
        return Response.ok().entity(result).build();
    }

}
