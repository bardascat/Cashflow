package com.lab.cashflow.dto;

import org.springframework.security.web.csrf.CsrfToken;

public class CsrfTokenDto {

    private String parameterName;
    private String parameterValue;

    public CsrfTokenDto() {

    }

    public CsrfTokenDto(CsrfToken csrfToken) {
        this.parameterName = csrfToken.getParameterName();
        this.parameterValue = csrfToken.getToken();
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    @Override
    public String toString() {
        return "CsrfToken [parameterName=" + parameterName
                + ", parameterValue=" + parameterValue + "]";
    }


}
