package com.github.covidalert.covidtests.dtos;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserPositiveDTO
{

    @NotNull
    private final String userId;

    @NotNull
    private final Date timestamp;

    public UserPositiveDTO(String userId, Date timestamp)
    {
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public String getUserId()
    {
        return userId;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }
}
