package com.thy.loyaltyServicesRest.model.base;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;


/**
 * Genel model soyut sinifi, tum modellerde bulunmasi gereken genel alanlari icerir
 *
 */
/*
https://projectlombok.org/features/Data

@Data is a convenient shortcut annotation that bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together: In other words, @Data generates all the boilerplate that is normally associated with simple POJOs (Plain Old Java Objects) and beans: getters for all fields, setters for all non-final fields, and appropriate toString, equals and hashCode implementations that involve the fields of the class, and a constructor that initializes all final fields, as well as all non-final fields with no initializer that have been marked with @NonNull, in order to ensure the field is never null.

@Data is like having implicit @Getter, @Setter, @ToString, @EqualsAndHashCode and @RequiredArgsConstructor annotations on the class (except that no constructor will be generated if any explicitly written constructors already exist). However, the parameters of these annotations (such as callSuper, includeFieldNames and exclude) cannot be set with @Data. If you need to set non-default values for any of these parameters, just add those annotations explicitly; @Data is smart enough to defer to those annotations.

All generated getters and setters will be public.
 */
@Data
@MappedSuperclass
public abstract class AbstractModel implements IAbstractModel
{
    private static final long serialVersionUID = 1L;

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

    /**
     * Olusturan user bilgisi manuel setlenmis mi
     */
    @Transient
    private boolean insertedByManually = false;

    /**
     * Duzenleyen user bilgisi manuel setlenmis mi
     */
    @Transient
    private boolean amendedByManually = false;

    @Override
    public String getInsertedBy()
    {
        return (insertedBy != null ? insertedBy : "N/A");
    }

    @Override
    public void setInsertedBy(String insertedBy)
    {
    	insertedByManually = true;
        this.insertedBy = insertedBy;
    }

    @Override
    public String getAmendedBy()
    {
        return (amendedBy != null ? amendedBy : "N/A");
    }

    @Override
    public void setAmendedBy(String amendedBy)
    {
    	amendedByManually = true;
        this.amendedBy = amendedBy;
    }

    @Override
    @Transient
    public boolean isNew()
    {
        return getId() == null || new Long("0").equals(getId());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj != null && obj instanceof IAbstractModel) {
            if (getClass().equals(obj.getClass())) {
            	IAbstractModel model = (IAbstractModel) obj;
                result = (model.getId() != null && getId() != null && model.getId().equals(getId()));
            }
        }

        return result;
    }
}