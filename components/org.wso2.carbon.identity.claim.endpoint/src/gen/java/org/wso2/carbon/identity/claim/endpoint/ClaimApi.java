package org.wso2.carbon.identity.claim.endpoint;

import org.wso2.carbon.identity.claim.endpoint.dto.*;
import org.wso2.carbon.identity.claim.endpoint.ClaimApiService;
import org.wso2.carbon.identity.claim.endpoint.factories.ClaimApiServiceFactory;

import io.swagger.annotations.ApiParam;

import org.wso2.carbon.identity.claim.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.claim.endpoint.dto.ReadResourceDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/Claim")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(value = "/Claim", description = "the Claim API")
public class ClaimApi  {

   private final ClaimApiService delegate = ClaimApiServiceFactory.getClaimApi();

    @GET
    @Path("/{claimId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Obtain claim description by ID", notes = "This method uses to obtain resource information when resource ID is given", response = ReadResourceDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation. Return Id,name,scopes,icon_uri and type"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found.Resource does not exist.") })

    public Response getClaim(@ApiParam(value = "ID ofthe  claim",required=true ) @PathParam("claimId")  String claimId)
    {
    return delegate.getClaim(claimId);
    }
}

