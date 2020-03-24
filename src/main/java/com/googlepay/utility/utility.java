package com.googlepay.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

public class utility {
	private static HttpTransport httpTransport = null;
	private GoogleCredential credentials = null;
	
	public long convertBalance(BigDecimal value, String currencyCode) { //big decimal
		long balance = 0L;
		long moneyUnit = 1000000L;
		if ("TWD".equals(currencyCode)) {
			balance = value.longValue();
			balance = balance * moneyUnit;
			return balance;
		} else {
			long bigDecimalToLongUnit = 100L;
			BigDecimal mutiValForBigDecimal = new BigDecimal("100");
			value = value.multiply(mutiValForBigDecimal);
			balance = value.longValue();
			balance = balance * moneyUnit / bigDecimalToLongUnit;
			return balance;
		}
	}
	
	public String makeId(String issuerId, String id) {
		return issuerId+"."+id;
	}
	
	public HttpTransport getHttpTransport() {
		try {
            this.httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return httpTransport;
	}
	
    public GoogleCredential makeOauthCredential(){
        Config config = Config.getInstance();
        JsonFactory jsonFactory = new GsonFactory();
        httpTransport = getHttpTransport();
        // the variables are in config file
        try {
        	credentials = GoogleCredential
                    .fromStream(new FileInputStream(config.getSERVICE_ACCOUNT_FILE()), httpTransport, jsonFactory)
                    .createScoped(config.getSCOPES());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return credentials;
    }
}
