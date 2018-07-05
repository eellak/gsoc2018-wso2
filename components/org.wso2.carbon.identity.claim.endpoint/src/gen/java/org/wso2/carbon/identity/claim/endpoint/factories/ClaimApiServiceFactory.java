package org.wso2.carbon.identity.claim.endpoint.factories;

import org.wso2.carbon.identity.claim.endpoint.ClaimApiService;
import org.wso2.carbon.identity.claim.endpoint.impl.impl.ClaimApiServiceImpl;

public class ClaimApiServiceFactory {

   private final static ClaimApiService service = new ClaimApiServiceImpl();

   public static ClaimApiService getClaimApi()
   {
      return service;
   }
}
