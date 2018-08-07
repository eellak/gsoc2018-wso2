 /*
  * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
  *
  * WSO2 Inc. licenses this file to you under the Apache License,
  * Version 2.0 (the "License"); you may not use this file except
  * in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing,
  * software distributed under the License is distributed on an
  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  * KIND, either express or implied.  See the License for the
  * specific language governing permissions and limitations
  * under the License.
  */
 package org.wso2.carbon.identity.sample.extension.authenticators;

 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.wso2.carbon.identity.application.authentication.framework.AuthenticatorFlowStatus;
 import org.wso2.carbon.identity.application.authentication.framework.FederatedApplicationAuthenticator;
 import org.wso2.carbon.identity.application.authentication.framework.config.model.StepConfig;
 import org.wso2.carbon.identity.application.authentication.framework.context.AuthenticationContext;
 import org.wso2.carbon.identity.application.authentication.framework.exception.AuthenticationFailedException;
 import org.wso2.carbon.identity.application.authentication.framework.exception.LogoutFailedException;
 import org.wso2.carbon.identity.application.authentication.framework.model.AuthenticatedUser;
 import org.wso2.carbon.identity.application.authentication.framework.util.FrameworkConstants;
 import org.wso2.carbon.identity.application.common.model.Claim;
 import org.wso2.carbon.identity.application.common.model.ClaimMapping;
 import org.wso2.carbon.identity.core.util.IdentityIOStreamUtils;
 import org.wso2.carbon.identity.core.util.IdentityTenantUtil;
 import org.wso2.carbon.identity.core.util.IdentityUtil;
 import org.wso2.carbon.identity.sample.extension.authenticators.internal.ServiceExtensionComponent;
 import org.wso2.carbon.user.api.UserRealm;
 import org.wso2.carbon.user.api.UserStoreException;
 import org.wso2.carbon.user.core.UserStoreManager;
 import org.apache.oltu.oauth2.common.utils.JSONUtils;

 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.UnsupportedEncodingException;
 import java.net.URL;
 import java.net.URLConnection;
 import java.net.URLDecoder;
 import java.net.URLEncoder;
 import java.nio.charset.Charset;
 import java.nio.charset.StandardCharsets;
 import java.util.*;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 /**
  * Abstract Sample Authenticator.
  */
 public abstract class CustomAuthenticator implements FederatedApplicationAuthenticator {

     private static final Log log = LogFactory.getLog(CustomAuthenticator.class);

     /**
      * Returns the page URL.
      *
      * @return
      */
     protected abstract String getPageUrlProperty();


     @Override
     public AuthenticatorFlowStatus process(HttpServletRequest request, HttpServletResponse response, AuthenticationContext context) throws AuthenticationFailedException,
             LogoutFailedException {

         log.info("Sample Authenticator: \"" + getFriendlyName() + "\" called");
         log.info(context.getCurrentAuthenticatedIdPs());
         String authenticatorName = request.getParameter("authenticatorName");
         if (context.isLogoutRequest()) {
             return AuthenticatorFlowStatus.SUCCESS_COMPLETED;
         } else if (getName().equals(authenticatorName)) {
             return processAuthenticationResponse(request, response, context);
         } else {
             return initializeAuthentication(request, response, context);
         }
     }

     private AuthenticatorFlowStatus processAuthenticationResponse(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   AuthenticationContext context) {

         String successParam = request.getParameter("success");

         boolean isSuccess = Boolean.parseBoolean(successParam);
         if (isSuccess) {
             String uniqueAttribute = getUniqueAttribute(context);
             String userInfoEndpoint = "";

             try {
                 AuthenticatedUser authenticatedUser = AuthenticatedUser
                         .createFederateAuthenticatedUserFromSubjectIdentifier(uniqueAttribute);
                 context.setSubject(authenticatedUser);

                 //Configure this property value from UI

                 userInfoEndpoint = context.getAuthenticatorProperties().get("UserInfoEndpoint") +
                         "?token=" + context.getProperty("token") + "&uniqueidenifier=" + uniqueAttribute;
                 String userInfoResponse = sendRequest(userInfoEndpoint, null);
                 Map<String, Object> jsonObject = JSONUtils.parseJSON(userInfoResponse);

                 Map<String, String> userInfo = new HashMap<>();
                 for(Map.Entry<String, Object> claim : jsonObject.entrySet()){
                    userInfo.put(claim.getKey(), (String) claim.getValue());
                 }

                 buildClaims(context, userInfo);

                 log.info(getFriendlyName() + " successful, User : " + uniqueAttribute);
             } catch (IOException e) {
                 log.error("Error occurred in sending request to: " + userInfoEndpoint);
             }

             return AuthenticatorFlowStatus.SUCCESS_COMPLETED;
         }

         return AuthenticatorFlowStatus.FAIL_COMPLETED;
     }

     private AuthenticatorFlowStatus initializeAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                              AuthenticationContext context) throws AuthenticationFailedException {
         Map<String, String> authenticatorProperties = context.getAuthenticatorProperties();

         //Get web app end point from the UI

         String pageUrl = authenticatorProperties.get(getPageUrlProperty());

         try {
             String userName = authenticatorProperties.get("UserName");
             String passWord = authenticatorProperties.get("Password");

             String callbackUrl = IdentityUtil.getServerURL(FrameworkConstants.COMMONAUTH, true, true);
             callbackUrl = callbackUrl + "?sessionDataKey=" + context.getContextIdentifier() + "&authenticatorName="
                     + getName();
             String encodedUrl = URLEncoder.encode(callbackUrl, StandardCharsets.UTF_8.name());

            //Web app request with callback url. From web app, get the
             // callback url and location redirection to call back external claims

             String userCredentials = userName + ":" + passWord;
             String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

            Map.Entry<String, String> authHeader = new AbstractMap.SimpleEntry<String, String>("Authorization", basicAuth);
            String authResponse = sendRequest(pageUrl + "?callbackurl=" + encodedUrl, authHeader);
             Map<String, Object> jsonObject = JSONUtils.parseJSON(authResponse);
             String callbackUrlRsp = (String) jsonObject.get("callbackurl");
             String token = (String) jsonObject.get("token");

             //if callback url is matched to the received callback url

             if(callbackUrl.equals(callbackUrlRsp)){
                 response.sendRedirect(URLDecoder.decode(callbackUrlRsp, "UTF-8")+ "&success=true");
                 context.setProperty("token", token);
             }

             return AuthenticatorFlowStatus.INCOMPLETE;
         } catch (UnsupportedEncodingException e) {
             throw new AuthenticationFailedException("Unsupported encoding exception occurred." + e.getMessage());
         } catch (IOException e) {
             log.error("Error occurred in sending redirect to: " + pageUrl, e);
             throw new AuthenticationFailedException("Error occurred in sending redirect.");
         }
     }

     private void buildClaims(AuthenticationContext context, Map<String, String> userInfo){

         Map<ClaimMapping, String> claims = new HashMap<>();
         for(Map.Entry<String, String> externalClaim : userInfo.entrySet()){
             ClaimMapping mapping = new ClaimMapping();
             Claim claim = new Claim();
             claim.setClaimUri(externalClaim.getKey());
             mapping.setRemoteClaim(claim);
             mapping.setLocalClaim(claim);

             claims.put(mapping, externalClaim.getValue());
         }
         context.getSubject().setUserAttributes(claims);
     }

     private String sendRequest(String url, Map.Entry<String, String> authHeader) throws IOException {

         BufferedReader in = null;
         StringBuilder b = new StringBuilder();

         try {
             URLConnection urlConnection = new URL(url).openConnection();

             if(authHeader != null){
                 urlConnection.setRequestProperty(authHeader.getKey(), authHeader.getValue());
             }

             in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), Charset.forName("utf-8")));
             String inputLine = in.readLine();
             while (inputLine != null) {
                 b.append(inputLine).append("\n");
                 inputLine = in.readLine();
             }
         } finally {
             IdentityIOStreamUtils.closeReader(in);
         }
         return b.toString();
     }

     private String getUniqueAttribute(AuthenticationContext context){
         String claimUri = context.getAuthenticatorProperties().get("ClaimProperty");
         UserStoreManager userStoreManager = null;
         Map.Entry<Integer, StepConfig> entry = context.getSequenceConfig().getStepMap().entrySet().iterator().next();
         String authenticatedUser = entry.getValue().getAuthenticatedUser().getAuthenticatedSubjectIdentifier();
         String value = "";
         try {
             int tenantId = IdentityTenantUtil.getTenantIdOfUser(authenticatedUser);
             UserRealm userRealm = ServiceExtensionComponent.getRealmService().getTenantUserRealm(tenantId);
             if (userRealm != null) {
                 userStoreManager = (UserStoreManager) userRealm.getUserStoreManager();
             }
             value = userStoreManager.getUserClaimValue(authenticatedUser, claimUri, null);
             log.info("claim value : " + value);
         } catch (UserStoreException e) {
             log.debug("Error occured file retrieving claim attribute from user store.");
         }
         return value;
     }
 }
