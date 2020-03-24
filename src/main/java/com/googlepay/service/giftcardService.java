package com.googlepay.service;

import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.GiftCardClass;
import com.google.api.services.walletobjects.model.GiftCardObject;


public interface giftcardService {
	public GenericJson insertGiftcardClass(GiftCardClass giftcardClass);
	public GenericJson insertGiftcardObject(GiftCardObject giftcardObject);
    public GenericJson updateGiftcardClass(GiftCardClass updateValues);
    public GenericJson updateGiftcardObject(GiftCardObject updateValues);
    public GenericJson getGiftcardClass (String classId);
	public GenericJson getGiftcardObject(String objectId);
}
