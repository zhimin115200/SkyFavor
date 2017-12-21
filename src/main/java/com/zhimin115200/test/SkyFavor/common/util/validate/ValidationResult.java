package com.zhimin115200.test.SkyFavor.common.util.validate;

import java.util.Map;

public class ValidationResult
{
  private boolean hasErrors;
  private Map<String, String> errorMsg;

  public boolean isHasErrors() {
    return this.hasErrors;
  }

  public void setHasErrors(boolean hasErrors) {
    this.hasErrors = hasErrors;
  }

  public Map<String, String> getErrorMsg() {
    return this.errorMsg;
  }

  public void setErrorMsg(Map<String, String> errorMsg) {
    this.errorMsg = errorMsg;
  }

  public String toString()
  {
    return "ValidationResult [hasErrors=" + this.hasErrors + ", errorMsg=" + this.errorMsg + "]";
  }
}