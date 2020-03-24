package com.googlepay.utility;

import com.google.api.client.util.PemReader;
import com.google.api.client.util.SecurityUtils;

import lombok.Getter;
import lombok.Setter;

import org.json.JSONObject;

import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;


@Getter
@Setter
public class Config {

    private static Config config = new Config();
    private String SERVICE_ACCOUNT_EMAIL_ADDRESS;
    private String SERVICE_ACCOUNT_FILE;
    private String ISSUER_ID;
    private ArrayList<String> ORIGINS;
    private String AUDIENCE;
    private String JWT_TYPE;
    private ArrayList<String> SCOPES;
    private RSAPrivateKey SERVICE_ACCOUNT_PRIVATE_KEY;

    private String APPLICATION_NAME;    // this isn't required


    private Config() {
        // Identifiers of Service account
        this.SERVICE_ACCOUNT_EMAIL_ADDRESS = "@endless-comfort-.iam.gserviceaccount.com"; // CHANGEME
        this.SERVICE_ACCOUNT_FILE = String.format("src/main/resources/%s", "privatekey.json"); // CHANGEME. Path to file with private key and Google credential config

        // Used by the Google Pay API for Passes Client library
        this.APPLICATION_NAME = "Google_Pay"; // CHANGEME

        // Identifier of Google Pay API for Passes Merchant Center
        this.ISSUER_ID = ""; // CHANGEME

        // List of origins for save to phone button. Used for JWT // CHANGEME
        //// See https://developers.google.com/pay/passes/reference/s2w-reference
        this.ORIGINS = new ArrayList<String>(){
            {
                add("http://localhost:8080");
            }
        };


        // Constants that are application agnostic. Used for JWT
        this.AUDIENCE = "google";
        this.JWT_TYPE = "savetoandroidpay";
        this.SCOPES =  new ArrayList<String>(){
            {
                add("https://www.googleapis.com/auth/wallet_object.issuer");
            }
        };

        // Load the private key as a java RSAPrivateKey object from service account file
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(this.SERVICE_ACCOUNT_FILE)));
            JSONObject privateKeyJson = new JSONObject(content);
            String privateKeyPkcs8 = (String) privateKeyJson.get("private_key");
            Reader reader = new StringReader(privateKeyPkcs8);
            PemReader.Section section = PemReader.readFirstSectionAndClose(reader, "PRIVATE KEY");
            byte[] bytes = section.getBase64DecodedBytes();
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory keyFactory = SecurityUtils.getRsaKeyFactory();
            this.SERVICE_ACCOUNT_PRIVATE_KEY = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }
}