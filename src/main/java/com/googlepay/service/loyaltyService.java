package com.googlepay.service;

import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.LoyaltyClass;
import com.google.api.services.walletobjects.model.LoyaltyObject;


public interface loyaltyService {
	public GenericJson insertLoyaltyClass(LoyaltyClass loyaltyClass);
	public GenericJson insertLoyaltyObject(LoyaltyObject loyaltyObject);
	public GenericJson updateLoyaltyClass(LoyaltyClass updateValues);
	public GenericJson updateLoyaltyObject(LoyaltyObject updateValues);
	public GenericJson getLoyaltyClass(String classId);
	public GenericJson getLoyaltyObject(String objectId);
}
