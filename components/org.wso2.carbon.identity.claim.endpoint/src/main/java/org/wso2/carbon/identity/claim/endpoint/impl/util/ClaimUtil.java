package org.wso2.carbon.identity.claim.endpoint.impl.util;

import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.claim.sample.service.ClaimService;
import org.wso2.carbon.identity.claim.sample.service.impl.ClaimServiceImpl;

public class ClaimUtil {

    public static ClaimService getResourceService() {

        return (ClaimService) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                .getOSGiService(ClaimServiceImpl.class, null);
    }
}
