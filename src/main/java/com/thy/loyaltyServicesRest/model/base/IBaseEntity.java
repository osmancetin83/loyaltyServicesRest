package com.thy.loyaltyServicesRest.model.base;

public interface IBaseEntity {
	
	public abstract Long getId();
    public abstract boolean isNull();
    public abstract boolean isNew();
}
