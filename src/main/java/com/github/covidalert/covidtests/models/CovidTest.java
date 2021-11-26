package com.github.covidalert.covidtests.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class CovidTest
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String userId;

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

    public CovidTest()
    {
    }

    public CovidTest(String userId, String disease, String testType, String testResult, Date testDate, String certificationAuthorityIdentifier, String memberState, String certificateIssuer)
    {
        this.userId = userId;
        this.disease = disease;
        this.testType = testType;
        this.testResult = testResult;
        this.testDate = testDate;
        this.certificationAuthorityIdentifier = certificationAuthorityIdentifier;
        this.memberState = memberState;
        this.certificateIssuer = certificateIssuer;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getDisease()
    {
        return disease;
    }

    public void setDisease(String disease)
    {
        this.disease = disease;
    }

    public String getTestType()
    {
        return testType;
    }

    public void setTestType(String testType)
    {
        this.testType = testType;
    }

    public String getTestResult()
    {
        return testResult;
    }

    public void setTestResult(String testResult)
    {
        this.testResult = testResult;
    }

    public Date getTestDate()
    {
        return testDate;
    }

    public void setTestDate(Date testDate)
    {
        this.testDate = testDate;
    }

    public String getCertificationAuthorityIdentifier()
    {
        return certificationAuthorityIdentifier;
    }

    public void setCertificationAuthorityIdentifier(String certificationAuthorityIdentifier)
    {
        this.certificationAuthorityIdentifier = certificationAuthorityIdentifier;
    }

    public String getMemberState()
    {
        return memberState;
    }

    public void setMemberState(String memberState)
    {
        this.memberState = memberState;
    }

    public String getCertificateIssuer()
    {
        return certificateIssuer;
    }

    public void setCertificateIssuer(String certificateIssuer)
    {
        this.certificateIssuer = certificateIssuer;
    }
}
