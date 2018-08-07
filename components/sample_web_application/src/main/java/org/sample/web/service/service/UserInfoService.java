package org.sample.web.service.service;

import org.sample.web.service.handler.APIClaimHandlerImpl;
import org.sample.web.service.handler.ExternalClaimHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Path("/claim")
public class UserInfoService {

    public static final String token = "123556664334456633344455664";

    @GET
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(@QueryParam("callbackurl") String callbackURL,
                             @Context HttpHeaders headers) {

        String authHeader = headers.getRequestHeader("authorization").get(0);

        if (authHeader != null && authHeader.startsWith("Basic")){
            String base64Credentials = authHeader.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                    Charset.forName("UTF-8"));
            final String[] values = credentials.split(":",2);

            String userName = values[0];
            String password = values[1];

            if(userName.equals("admin") && password.equals("admin")){
                Map<String, String> response = new HashMap<String, String>();
                response.put("callbackurl", callbackURL);
                response.put("token", token);

                return Response.status(Response.Status.OK).entity(response).build();
            }
        }

        return Response.status(Response.Status.UNAUTHORIZED).entity("authentication fail").build();
    }

    @GET
    @Path("/userinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(@QueryParam("token") String token, @QueryParam("uniqueidenifier") String uniqueIdentifier){

        System.out.println("identifier : " + uniqueIdentifier);
        System.out.println("token: " + token);
        if(token.equals(token)){
            Map<String, String> userInfo = getUserInfo(uniqueIdentifier);
            return Response.status(Response.Status.OK).entity(userInfo).build();
        }

        return  Response.status(Response.Status.NOT_FOUND).entity("resource not found").build();
    }


    /**
     * This method should implemented as web service method
     * @param identifier
     */
    private Map<String, String> getUserInfo(String identifier){

        ExternalClaimHandler externalClaimHandler = new APIClaimHandlerImpl();
        Map<String, String> claims = externalClaimHandler.getClaims(identifier);

        /*
        * Create response object with claims.
        *
        */
        return claims;
    }

}
