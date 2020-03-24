package com.googlepay.service.impl;

import com.googlepay.utility.Config;
import com.googlepay.utility.utility;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.walletobjects.Walletobjects;
import com.google.api.services.walletobjects.model.GiftCardClass;
import com.google.api.services.walletobjects.model.GiftCardObject;
import com.googlepay.service.giftcardService;

@Service
public class giftcardServiceImpl implements giftcardService{
    com.googlepay.utility.utility utility = new utility();
    HttpTransport httpTransport = null;
    Config config = Config.getInstance();
    
	
	public GenericJson insertGiftcardClass(GiftCardClass giftcardClass) {
		GoogleCredential credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
		GenericJson response = null;
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.giftcardclass().insert(giftcardClass).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}

	
	public GenericJson insertGiftcardObject(GiftCardObject giftcardObject) {
		GoogleCredential credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
		GenericJson response = null;
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.giftcardobject().insert(giftcardObject).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson updateGiftcardClass(GiftCardClass updateValues) {
		GoogleCredential credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
		GenericJson response = null;
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.giftcardclass().update(updateValues.getId(), updateValues).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson updateGiftcardObject(GiftCardObject updateValues) {
		GoogleCredential credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
		GenericJson response = null;
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.giftcardobject().update(updateValues.getId(), updateValues).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}

	
	public GenericJson getGiftcardClass(String classId) {
		GoogleCredential credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
		GenericJson response = null;
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.giftcardclass().get(classId).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}

	

	public GenericJson getGiftcardObject(String objectId) {
		GoogleCredential credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
		GenericJson response = null;
		JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.giftcardobject().get(objectId).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}

}
