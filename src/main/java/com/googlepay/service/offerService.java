package com.googlepay.service;

import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.OfferClass;
import com.google.api.services.walletobjects.model.OfferObject;


public interface offerService {
	public GenericJson insertOfferClass(OfferClass offerClass);
	public GenericJson insertOfferObject(OfferObject offerObject);
	public GenericJson updateOfferClass(String classId);
	public GenericJson updateOfferObject(String objectId);
	public GenericJson getOfferClass(String classId); 
	public GenericJson getOfferObject(String objectId);
}