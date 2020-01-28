package com.thy.loyaltyServicesRest.model.base;


import java.io.Serializable;
import java.util.Date;

public interface IAbstractModel extends Serializable
{
    <I extends Serializable> I getId();

    String getInsertedBy();

    void setInsertedBy(String var1);

//    Date getInsertDate();
//
//    void setInsertDate(Date var1);

    String getAmendedBy();

    void setAmendedBy(String var1);
//
//    void setAmendDate(Date var1);

    boolean isInsertedByManually();

    boolean isAmendedByManually();

    boolean isNew();
}
