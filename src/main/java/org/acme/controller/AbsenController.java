package org.acme.controller;

import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.api.oas.absen.GetListAbsenOAS;
import org.acme.api.oas.absen.UpdateAbsenOAS;
import org.acme.model.AbsenModel;
import org.acme.service.AbsenService;
import org.acme.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.reactive.RestPath;

import java.time.LocalDate;
import java.util.List;

@Path("/absen")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AbsenController {

    @Inject
    AbsenService absenService;

    @Inject
    UserService userService;

    @GET
    @Path("/getData")
    public Response getDataById(@QueryParam("nik") String nik){
        JsonObject result = new JsonObject();
        try {
            result.put("Status", 200);
            result.put("Message", "Success");
            result.put("Payload", absenService.getId(nik));
            return Response.ok().entity(result).build();
        } catch (Exception e){
            result.put("Status", 200);
            result.put("Message", e.getMessage());
            result.put("Payload", "Data Kosong");
            return Response.ok().entity(result).build();
        }
    }

    @POST
    @Operation(summary = "Absensi")
    public Response absen(@QueryParam("nik") String nik){
        AbsenModel absenModel = new AbsenModel();
        JsonObject result = new JsonObject();
        if (absenService.validationAbsen(nik).isEmpty()) {

            result.put("Status", 200);
            result.put("Message", "Success");
            result.put("Payload", absenService.dataAbsen(nik));
            return Response.ok().entity(result).build();
        }
        result.put("Status", 200);
        result.put("Message", "Anda Sudah Absen");
        result.put("Payload", absenService.validationAbsen(nik).get(0));
        return Response.ok().entity(result).build();
    }

//    @POST
//    @Path("/absenNik")
//    @Operation(summary = "Absensi")
//    @APIResponses(value = {
//            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AddAbsenOAS.Response.class))),
//            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AddAbsenOAS.BadRequest.class)))
//    })
//    public Response absenNik(@QueryParam("nik") String nik){
//        JsonObject result = new JsonObject();
//
//        result.put("status", "Success");
//        result.put("message", "berhasil Absen");
//        result.put("data", absenService.persistAbsenNik(nik));
//        return Response.ok().entity(result).build();
//    }

    @POST
    @Path("/waw")
    @Operation(summary = "Get All Date")
    public Response getUserAll() {
        JsonObject result = new JsonObject();
        try {
            // Logika bisnis Anda
            result.put("Status", "Success");
            result.put("Message", "berhasil Absen");
            result.put("Payload", absenService.getListAll());
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
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListAbsenOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListAbsenOAS.BadRequest.class)))
    })
    public Response getDateNow() {
        JsonObject result = new JsonObject();
        result.put("data", absenService.findDateNow());
        return Response.ok().entity(result).build();
    }

    @PUT
    @Path("/{absen_id}")
    @Operation(summary = "Update Absen")
    @APIResponses(value = {
            @APIResponse(responseCode = "200",description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UpdateAbsenOAS.Response.class))),
            @APIResponse(responseCode = "400",description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UpdateAbsenOAS.BadRequest.class)))
    })
    public Response updateAbsen(@RestPath long absen_id){
      absenService.updateAbsen(absen_id);
        return Response.ok("MANTAP").build();
    }


    @GET
    @Path("/date")
    @Operation(summary = "Get All By Parameter Date")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListAbsenOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListAbsenOAS.BadRequest.class)))
    })
    public Response getDate(@QueryParam("tanggal") LocalDate tanggal) {
        JsonObject result = new JsonObject();
        List<AbsenModel> absenModels = absenService.findDate(tanggal);

        result.put("data", absenModels);
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("/dates")
    @Operation(summary = "Get All By Range Date")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListAbsenOAS.Response.class))),
            @APIResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GetListAbsenOAS.BadRequest.class)))
    })
    public Response getDates(@QueryParam("start")LocalDate start, @QueryParam("end")LocalDate end) {
        JsonObject result = new JsonObject();
        List<AbsenModel> absenModels = absenService.findDates(start, end);

        result.put("data", absenModels);
        return Response.ok().entity(result).build();
    }

}
