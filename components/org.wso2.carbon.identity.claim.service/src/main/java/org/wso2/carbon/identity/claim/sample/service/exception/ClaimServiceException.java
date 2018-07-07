package org.wso2.carbon.identity.claim.sample.service.exception;

import org.wso2.carbon.identity.claim.sample.service.constants.ClaimConstant;

public class ClaimServiceException extends Exception {
    private String errorCode;
    private String errorDescription;
    private int statusCode;

    public ClaimServiceException() {

    }

    public ClaimServiceException(String message) {

        super(message);
    }

    public ClaimServiceException(Throwable throwable) {

        super(throwable);
    }

    public ClaimServiceException(String message, Throwable throwable) {

        super(message, throwable);
    }

    public ClaimServiceException(ClaimConstant.ErrorMessages errorcode, String message) {

        super(message);
        this.errorCode = errorcode;
    }

    public ClaimServiceException(ClaimConstant.ErrorMessages errorCode, String message, Throwable throwable) {

        super(message, throwable);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {

        return errorCode;
    }

    public void setErrorCode(String errorCode) {

        this.errorCode = errorCode;
    }

    public String getErrorDescription() {

        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {

        this.errorDescription = errorDescription;
    }

    public int getStatusCode() {

        return statusCode;
    }

    public void setStatusCode(int statusCode) {

        this.statusCode = statusCode;
    }
}
