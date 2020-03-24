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
import com.google.api.services.walletobjects.model.OfferClass;
import com.google.api.services.walletobjects.model.OfferObject;
import com.googlepay.service.offerService;

@Service
public class offerServiceImpl implements offerService {
    com.googlepay.utility.utility utility = new utility();
    GoogleCredential credentials = null;
    HttpTransport httpTransport = null;
    Config config = Config.getInstance();
	
	public GenericJson insertOfferClass(OfferClass offerClass) {
        GenericJson response = null;
        credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.offerclass().insert(offerClass).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson insertOfferObject(OfferObject offerObject) {
        GenericJson response = null;
        credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.offerobject().insert(offerObject).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson updateOfferClass(String classId) {
        GenericJson response = null;
        credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        OfferClass content = null;
        try {
			response = client.offerclass().update(classId, content).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson updateOfferObject(String objectId) {
        GenericJson response = null;
        credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        OfferObject content = null;
        try {
			response = client.offerobject().update(objectId, content).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson getOfferClass(String classId) {
        GenericJson response = null;
        credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.offerclass().get(classId).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}


	public GenericJson getOfferObject(String objectId) {
        GenericJson response = null;
        credentials = utility.makeOauthCredential();
		httpTransport = utility.getHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Walletobjects client = new Walletobjects.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(config.getAPPLICATION_NAME())
                .build();
        try {
            response = client.offerobject().get(objectId).execute();
            response.put("code",200);
        } catch (GoogleJsonResponseException gException)  {
            response = gException.getDetails();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
	}

}

