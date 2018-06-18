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
import org.wso2.carbon.identity.application.authentication.framework.context.AuthenticationContext;
import org.wso2.carbon.identity.application.authentication.framework.exception.AuthenticationFailedException;
import org.wso2.carbon.identity.application.authentication.framework.exception.LogoutFailedException;
import org.wso2.carbon.identity.application.authentication.framework.model.AuthenticatedUser;
import org.wso2.carbon.identity.application.authentication.framework.util.FrameworkConstants;
import org.wso2.carbon.identity.core.util.IdentityUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
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
    public AuthenticatorFlowStatus process(HttpServletRequest request, HttpServletResponse response,
                                           AuthenticationContext context) throws AuthenticationFailedException,
            LogoutFailedException {

        log.info("Sample Authenticator: \"" + getFriendlyName() + "\" called");
        /*for (Map.Entry<org.wso2.carbon.identity.application.common.model.ClaimMapping, String> entry :
                context.getSequenceConfig().getAuthenticatedUser().getUserAttributes().entrySet()) {
            log.info("claim uri: " + entry.getKey() + ", claim value: " + entry.getValue());
        }*/
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

        AuthenticatedUser lastUser = context.getSubject();
        String successParam = request.getParameter("success");
        boolean isSuccess = Boolean.parseBoolean(successParam);
        if (isSuccess) {
            String subject = lastUser.getAuthenticatedSubjectIdentifier();
            AuthenticatedUser authenticatedUser = AuthenticatedUser
                    .createFederateAuthenticatedUserFromSubjectIdentifier(subject);
            context.setSubject(authenticatedUser);
            log.info(getFriendlyName() + " successful, User : " + subject);
            return AuthenticatorFlowStatus.SUCCESS_COMPLETED;
        }

        return AuthenticatorFlowStatus.FAIL_COMPLETED;
    }

    private AuthenticatorFlowStatus initializeAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                             AuthenticationContext context)
            throws AuthenticationFailedException {

        Map<String, String> authenticatorProperties = context.getAuthenticatorProperties();
        String pageUrl = authenticatorProperties.get(getPageUrlProperty());
        try {
            String callbackUrl = IdentityUtil.getServerURL(FrameworkConstants.COMMONAUTH,
                    true, true);
            callbackUrl = callbackUrl + "?sessionDataKey=" + context.getContextIdentifier() + "&authenticatorName="
                    + getName();
            String encodedUrl = URLEncoder.encode(callbackUrl, StandardCharsets.UTF_8.name());

            response.sendRedirect(pageUrl + "?callbackUrl=" + encodedUrl);
            return AuthenticatorFlowStatus.INCOMPLETE;
        } catch (UnsupportedEncodingException e) {
            throw new AuthenticationFailedException("Unsupported encoding exception occurred." + e.getMessage());
        } catch (IOException e) {
            log.error("Error occurred in sending redirect to: " + pageUrl, e);
            throw new AuthenticationFailedException("Error occurred in sending redirect.");
        }
    }
}
