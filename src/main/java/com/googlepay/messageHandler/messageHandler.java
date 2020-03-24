package com.googlepay.messageHandler;

import java.math.BigDecimal;

import com.googlepay.utility.Config;
import com.googlepay.utility.Jwt;
import com.googlepay.utility.utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.GiftCardObject;
import com.google.api.services.walletobjects.model.LoyaltyObject;
import com.google.gson.Gson;
import com.googlepay.model.GiftcardClassModel;
import com.googlepay.model.GiftcardObjectModel;
import com.googlepay.model.LoyaltyClassModel;
import com.googlepay.model.LoyaltyObjectModel;
import com.googlepay.model.OfferClassModel;
import com.googlepay.model.giftcardClass;
import com.googlepay.model.giftcardObject;
import com.googlepay.model.loyaltyClass;
import com.googlepay.model.loyaltyObject;
import com.googlepay.model.offerClass;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Card Insert/Update/Get")
@RestController
public class messageHandler {
	@Autowired
	private com.googlepay.service.giftcardService giftcardService;
	@Autowired
	private com.googlepay.service.loyaltyService loyaltyService;
	@Autowired
	private com.googlepay.service.offerService offerService;
	String signedJwt = null;
	final Config config = Config.getInstance();

	final private Integer success = 200;
	final private Integer alreadyExist = 409;
	final private String saveLink = "https://pay.google.com/gp/v/u/0/save/";

	@ApiOperation(value = "Insert Giftcard Class", notes = "Insert Giftcard Class")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Giftcard Class Insert Successfully")})
	@PostMapping("/giftcardclass/insert")
	public GenericJson giftcardClassInsertHanlder (@RequestBody GiftcardClassModel giftcardClassModel) {
			GenericJson classResponse = null;
			giftcardClass giftcardClass = new giftcardClass();
			giftcardClass.prepareAllValues(giftcardClassModel);
			classResponse = giftcardService.insertGiftcardClass(giftcardClass.getGiftcardClass());
			return classResponse;
	}
	
	@ApiOperation(value = "Insert Giftcard Object", notes = "Insert Giftcard Object")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Giftcard Insert Successfully")})
	@PostMapping("/giftcardobject/insert")
	public String giftcardObjectInsertHanlder (@RequestBody GiftcardObjectModel giftcadObjectModel) {
			GenericJson objectResourcePayload = null; //for generate JWT
			giftcardObject giftcardObject = new giftcardObject();	//for setting insert object
			GenericJson objectInsertResponse = null; //for insert response
			giftcardObject.prepareAllValues(giftcadObjectModel);
			objectInsertResponse = giftcardService.insertGiftcardObject(giftcardObject.getGiftcardObject());
			if(!StringUtils.isEmpty(objectInsertResponse.get("code"))) {
				if(!success.equals(objectInsertResponse.get("code")) && !alreadyExist.equals(objectInsertResponse.get("code"))) {
					return objectInsertResponse.toString();
				}
			}
			System.out.println(giftcardObject.getGiftcardObject());
			System.out.println(objectInsertResponse.toString());
			objectResourcePayload = (GiftCardObject) giftcardObject.getGiftcardObject();
            Jwt googlePassJwt = new Jwt();
            Gson gson = new Gson();
            googlePassJwt.addGiftcardObject(gson.toJsonTree(objectResourcePayload));
            signedJwt = googlePassJwt.generateSignedJwt();
			return saveLink+signedJwt;
	}
	
	@ApiOperation(value = "Update Giftcard Class", notes = "Update Giftcard Class")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Giftcard Class Update Successfully")})
	@PostMapping("/giftcardclass/update")
	public GenericJson giftcardClassUpdateHandler(@RequestBody GiftcardClassModel giftcardClassModel) {
		giftcardClass giftcardClass = new giftcardClass();
		GenericJson updateSuccess = new GenericJson();
		//set update class
		giftcardClass.prepareAllValues(giftcardClassModel);
		updateSuccess = giftcardService.updateGiftcardClass(giftcardClass.getGiftcardClass());
		return updateSuccess;
	}
	
	@ApiOperation(value = "Update Giftcard Object", notes = "Update Giftcard Object")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Giftcard Object Update Successfully")})
	@PostMapping("/giftcardobject/update")
	public GenericJson giftcardObjectUpdateHandler(String issuerId, String objectId, BigDecimal balance, String barcodeType) {
		GenericJson objectResponse = null;
		giftcardObject giftcardObject = new giftcardObject();
		GenericJson updateSuccess = new GenericJson();
		utility utils = new utility();
		objectResponse = giftcardService.getGiftcardObject(utils.makeId(issuerId, objectId));
		if(!StringUtils.isEmpty(objectResponse.get("code"))) {//failed return google server response
			if(!success.equals(objectResponse.get("code"))) { 
				return objectResponse;
			}
		}
		giftcardObject.setGiftcardObject((GiftCardObject) objectResponse);
		giftcardObject.setUpdateValue((GiftCardObject) objectResponse, balance, barcodeType);
		updateSuccess = giftcardService.updateGiftcardObject(giftcardObject.getGiftcardObject());
		return updateSuccess;
	}
	
	@ApiOperation(value = "Get Giftcard Class", notes = "Get Giftcard Class")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Get Giftcard Class Successfully")})
	@PostMapping("/giftcardclass/get")
	public GenericJson giftcardClassGetHandler(String issuerId, String giftcardClassId) {
		GenericJson objectResponse = null;
		utility utils = new utility();
		objectResponse = giftcardService.getGiftcardClass(utils.makeId(issuerId, giftcardClassId));
		return objectResponse;
	}
	
	@ApiOperation(value = "Get Giftcard Object", notes = "Get Giftcard Object")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Get Giftcard Object Successfully")})
	@PostMapping("/giftcardobject/get")
	public GenericJson giftcardGetHandler(String issuerId, String giftcardObjectId) {
		GenericJson objectResponse = null;
		utility utils = new utility();
		objectResponse = giftcardService.getGiftcardObject(utils.makeId(issuerId, giftcardObjectId));
		return objectResponse;
	}
	
	
	@ApiOperation(value = "Insert Loyalty Class", notes = "Insert Loyalty Class")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Loyalty Class Insert Successfully")})
	@PostMapping("/loyaltyclass/insert")
	public GenericJson loyaltyClassInsertHanlder (@RequestBody LoyaltyClassModel loyaltyClassModel) {
			GenericJson classResponse = null;
			loyaltyClass loyaltyClass = new loyaltyClass();
			loyaltyClass.prepareAllValues(loyaltyClassModel);
			classResponse = loyaltyService.insertLoyaltyClass(loyaltyClass.getLoyaltyClass());
			return classResponse;
	}
	
	@ApiOperation(value = "Insert Loyalty Object", notes = "Insert Loyalty Object")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Loyalty Object Insert Successfully")})
	@PostMapping(value = "/loyaltyobject/insert")
	public String loyaltyObjectInsertHanlder (@RequestBody LoyaltyObjectModel loyaltyObjectModel) {
			GenericJson objectResourcePayload = null; //for generate JWT
			loyaltyObject loyaltyObject = new loyaltyObject();//for setting insert object
			GenericJson objectInsertResponse = null; //for insert response
			loyaltyObject.prepareAllValues(loyaltyObjectModel);
			objectInsertResponse = loyaltyService.insertLoyaltyObject(loyaltyObject.getLoyaltyObject());
			if(!StringUtils.isEmpty(objectInsertResponse.get("code"))) {
				if(!success.equals(objectInsertResponse.get("code")) && !alreadyExist.equals(objectInsertResponse.get("code"))) {
					return objectInsertResponse.toString();
				}
			}	
			objectResourcePayload = (LoyaltyObject) loyaltyObject.getLoyaltyObject();
            Jwt googlePassJwt = new Jwt();
            Gson gson = new Gson();
            googlePassJwt.addLoyaltyObject(gson.toJsonTree(objectResourcePayload));
            signedJwt = googlePassJwt.generateSignedJwt();
			return saveLink+signedJwt;
	}
	
	@ApiOperation(value = "Update Loyalty Object", notes = "Update Loyalty Object")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Loyalty Update Successfully")})
	@PostMapping("/loyaltyobject/update")
	public GenericJson loyaltyUpdateHandler(@RequestBody LoyaltyObjectModel loyaltyObjectModel) {
		GenericJson objectResponse = null;
		loyaltyObject loyaltyObject = new loyaltyObject();
		GenericJson updateSuccess = new GenericJson();
		loyaltyObject.prepareAllValues(loyaltyObjectModel);
		objectResponse = loyaltyService.getLoyaltyObject(loyaltyObject.getObjectId());
		if(!StringUtils.isEmpty(objectResponse.get("code"))) {//failed return google server response
			if(!success.equals(objectResponse.get("code"))) { 
				return objectResponse;
			}
		}
		loyaltyObject.setLoyaltyObject((LoyaltyObject) objectResponse);
		loyaltyObject.setUpdateValue((LoyaltyObject)objectResponse, loyaltyObjectModel);
		loyaltyObject.getLoyaltyObject().setVersion(loyaltyObjectModel.getVersion()+1L);
		updateSuccess = loyaltyService.updateLoyaltyObject(loyaltyObject.getLoyaltyObject());
		return updateSuccess;
	}
	
	@ApiOperation(value = "Get Loyalty Class", notes = "Get Loyalty Class")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Get Loyalty Class Successfully")})
	@PostMapping("/loyaltyclass/get")
	public GenericJson loyaltyClassGetHandler(@PathVariable String loyaltyClassId) {
		GenericJson objectResponse = null;
		String id = config.getISSUER_ID()+"."+loyaltyClassId;
		objectResponse = loyaltyService.getLoyaltyObject(id);
		return objectResponse;
	}
	
	@ApiOperation(value = "Get Loyalty Object", notes = "Get Loyalty Object")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Get Loyalty Successfully")})
	@PostMapping("/loyaltyobject/get")
	public GenericJson loyaltyGetHandler(@PathVariable String loyaltyObjectId) {
		GenericJson objectResponse = null;
		String id = config.getISSUER_ID()+"."+loyaltyObjectId;
		objectResponse = loyaltyService.getLoyaltyObject(id);
		return objectResponse;
	}
	
	@ApiOperation(value = "Insert Offer Class", notes = "Offer Class")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Offer Class Insert Successfully")})
	@PostMapping(value = "/offerclass/insert")
	public GenericJson offerClassInsertHandler (OfferClassModel offerClassModel) {
			GenericJson classResponse = null;
			offerClass offerClass = new offerClass();
			offerClass.prepareAllValues(offerClassModel);
			classResponse = offerService.insertOfferClass(offerClass.getLoyaltyClass());
			return classResponse;
	}

}
