package com.thy.loyaltyServicesRest.model.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;


@Data
@MappedSuperclass
public abstract class BaseEntity implements IBaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

//	@Version
//	@Column(name = "Version", nullable = false)
//	private Integer version;

	@Column(name = "INSERT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date insertDate;
    
    @Column(name = "INSERTED_BY", length = 30)
    @Length(max = 30)
    protected String insertedBy;

    @Column(name = "AMEND_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date amendDate;
    
    @Column(name = "AMENDED_BY", length = 30)
    @Length(max = 30)
    protected String amendedBy;
}
