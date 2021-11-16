package com.github.covidalert.covidtests.dtos;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateCovidTestDTO
{

    @NotNull
    private String disease;

    @NotNull
    private String testType;

    @NotNull
    private String testResult;

    @NotNull
    private Date testDate;

    @NotNull
    private String certificationAuthorityIdentifier;

    @NotNull
    private String memberState;

    @NotNull
    private String certificateIssuer;

    public CreateCovidTestDTO()
    {
    }

    public String getDisease()
    {
        return disease;
    }

    public String getTestType()
    {
        return testType;
    }

    public String getTestResult()
    {
        return testResult;
    }

    public Date getTestDate()
    {
        return testDate;
    }

    public String getCertificationAuthorityIdentifier()
    {
        return certificationAuthorityIdentifier;
    }

    public String getMemberState()
    {
        return memberState;
    }

    public String getCertificateIssuer()
    {
        return certificateIssuer;
    }
}
