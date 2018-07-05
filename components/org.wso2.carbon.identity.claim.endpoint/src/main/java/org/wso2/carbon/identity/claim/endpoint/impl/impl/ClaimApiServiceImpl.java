package org.wso2.carbon.identity.claim.endpoint.impl.impl;

import org.wso2.carbon.identity.claim.endpoint.*;
import org.wso2.carbon.identity.claim.endpoint.dto.*;


import org.wso2.carbon.identity.claim.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.claim.endpoint.dto.ReadResourceDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public class ClaimApiServiceImpl extends ClaimApiService {
    @Override
    public Response getClaim(String claimId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
