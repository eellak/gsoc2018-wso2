package org.wso2.carbon.identity.claim.endpoint.dto;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;





@ApiModel(description = "")
public class ReadResourceDTO  {
  
  
  
  private String claimId = null;
  
  
  private String name = null;
  
  
  private String scope = null;
  
  
  private String type = null;
  
  
  private String iconUri = null;
  
  
  private String description = null;

  
  /**
   *  Returning resource Id after successfull registration.
   **/
  @ApiModelProperty(value = " Returning resource Id after successfull registration.")
  @JsonProperty("claimId")
  public String getClaimId() {
    return claimId;
  }
  public void setClaimId(String claimId) {
    this.claimId = claimId;
  }

  
  /**
   *  Returning name after successfull registration.
   **/
  @ApiModelProperty(value = " Returning name after successfull registration.")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   *  Returning resource scopes after successfull registration.
   **/
  @ApiModelProperty(value = " Returning resource scopes after successfull registration.")
  @JsonProperty("scope")
  public String getScope() {
    return scope;
  }
  public void setScope(String scope) {
    this.scope = scope;
  }

  
  /**
   *  Returning type after successfull registration.
   **/
  @ApiModelProperty(value = " Returning type after successfull registration.")
  @JsonProperty("type")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  
  /**
   *  Returning icon_uri after successfull registration.
   **/
  @ApiModelProperty(value = " Returning icon_uri after successfull registration.")
  @JsonProperty("iconUri")
  public String getIconUri() {
    return iconUri;
  }
  public void setIconUri(String iconUri) {
    this.iconUri = iconUri;
  }

  
  /**
   *  Returning description after successfull registration.
   **/
  @ApiModelProperty(value = " Returning description after successfull registration.")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReadResourceDTO {\n");
    
    sb.append("  claimId: ").append(claimId).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  scope: ").append(scope).append("\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("  iconUri: ").append(iconUri).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
