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
import com.google.api.services.walletobjects.model.LoyaltyClass;
import com.google.api.services.walletobjects.model.LoyaltyObject;
import com.googlepay.service.loyaltyService;

@Service
public class loyaltyServiceImpl implements loyaltyService {
    com.googlepay.utility.utility utility = new utility();
    GoogleCredential credentials = null;
    HttpTransport httpTransport = null;
    Config config = Config.getInstance();
	
    
	public GenericJson insertLoyaltyClass(LoyaltyClass loyaltyClass) {
		credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
		GenericJson response = null;
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.loyaltyclass().insert(loyaltyClass).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson insertLoyaltyObject(LoyaltyObject loyaltyObject) {
        GenericJson response = null;
        credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
        	System.out.println(loyaltyObject.toString());
            response = client.loyaltyobject().insert(loyaltyObject).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson updateLoyaltyClass(LoyaltyClass updateValues) {
        GenericJson response = null;
        credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        LoyaltyClass content = null;
        try {
			response = client.loyaltyclass().update(updateValues.getId(), updateValues).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson updateLoyaltyObject(LoyaltyObject updateValues) {
        GenericJson response = null;
        credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        LoyaltyObject content = null;
        try {
			response = client.loyaltyobject().update(updateValues.getId(), updateValues).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson getLoyaltyClass(String classId) {
        GenericJson response = null;
        credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.loyaltyclass().get(classId).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson getLoyaltyObject(String objectId) {
        GenericJson response = null;
        credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.loyaltyobject().get(objectId).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}

}

