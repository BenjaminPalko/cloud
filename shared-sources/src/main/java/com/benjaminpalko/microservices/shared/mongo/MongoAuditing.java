package com.benjaminpalko.microservices.shared.mongo;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public abstract class MongoAuditing {
    @CreatedDate
    private final Date createdOn;
    @LastModifiedDate
    private final Date lastModifiedOn;
    @CreatedBy
    private final String createBy;
    @LastModifiedBy
    private final String lastModifiedBy;

    protected MongoAuditing(Date createdOn, Date lastModifiedOn, String createBy, String lastModifiedBy) {
        this.createdOn = createdOn;
        this.lastModifiedOn = lastModifiedOn;
        this.createBy = createBy;
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
}
