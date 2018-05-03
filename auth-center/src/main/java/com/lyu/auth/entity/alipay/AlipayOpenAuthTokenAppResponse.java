package com.lyu.auth.entity.alipay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lyu.auth.entity.alipay.base.AlipayResponseBase;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlipayOpenAuthTokenAppResponse extends AlipayResponseBase {

  /**
   * code : 10000 msg : Success user_id : 2088102150527498 auth_app_id : 2013121100055554
   * app_auth_token : 201509BBeff9351ad1874306903e96b91d248A36 app_refresh_token :
   * 201509BBdcba1e3347de4e75ba3fed2c9abebE36 expires_in : 123456 re_expires_in : 123456 sub_code :
   * isp.unknow-error sub_msg : 系统繁忙
   */
  private String code;

  private String msg;

  @JsonProperty("user_id")
  private String userId;

  @JsonProperty("auth_app_id")
  private String authAppId;

  @JsonProperty("app_auth_token")
  private String appAuthToken;

  @JsonProperty("app_refresh_token")
  private String appRefreshToken;

  @JsonProperty("expires_in")
  private String expiresIn;

  @JsonProperty("re_expires_in")
  private String reExpiresIn;

  @JsonProperty("sub_code")
  private String subCode;

  @JsonProperty("sub_msg")
  private String subMsg;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getAuthAppId() {
    return authAppId;
  }

  public void setAuthAppId(String authAppId) {
    this.authAppId = authAppId;
  }

  public String getAppAuthToken() {
    return appAuthToken;
  }

  public void setAppAuthToken(String appAuthToken) {
    this.appAuthToken = appAuthToken;
  }

  public String getAppRefreshToken() {
    return appRefreshToken;
  }

  public void setAppRefreshToken(String appRefreshToken) {
    this.appRefreshToken = appRefreshToken;
  }

  public String getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(String expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getReExpiresIn() {
    return reExpiresIn;
  }

  public void setReExpiresIn(String reExpiresIn) {
    this.reExpiresIn = reExpiresIn;
  }

  public String getSubCode() {
    return subCode;
  }

  public void setSubCode(String subCode) {
    this.subCode = subCode;
  }

  public String getSubMsg() {
    return subMsg;
  }

  public void setSubMsg(String subMsg) {
    this.subMsg = subMsg;
  }
}
