package org.wso2.carbon.identity.claim.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ErrorDTO  {
  
  
  
  private String error = null;
  
  
  private String errorDescription = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("error")
  public String getError() {
    return error;
  }
  public void setError(String error) {
    this.error = error;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("errorDescription")
  public String getErrorDescription() {
    return errorDescription;
  }
  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorDTO {\n");
    
    sb.append("  error: ").append(error).append("\n");
    sb.append("  errorDescription: ").append(errorDescription).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
