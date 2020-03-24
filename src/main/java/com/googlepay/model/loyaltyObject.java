package com.googlepay.model;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.walletobjects.model.Barcode;
import com.google.api.services.walletobjects.model.DateTime;
import com.google.api.services.walletobjects.model.Image;
import com.google.api.services.walletobjects.model.ImageModuleData;
import com.google.api.services.walletobjects.model.LinksModuleData;
import com.google.api.services.walletobjects.model.LoyaltyObject;
import com.google.api.services.walletobjects.model.LoyaltyPoints;
import com.google.api.services.walletobjects.model.LoyaltyPointsBalance;
import com.google.api.services.walletobjects.model.Message;
import com.google.api.services.walletobjects.model.TextModuleData;
import com.google.api.services.walletobjects.model.Uri;
import com.googlepay.utility.Config;
import com.googlepay.utility.utility;

import lombok.Data;



@Data
public class loyaltyObject {
	
	private String classId = null;
	private String objectId = null;
	private String cardNo = null;
	private String loyaltyPoints = null;
	private String faceValue = null;
	private String barcodeType = "code128"; //default setting
	private String uriLink = null;
	private String messageHeader = null;
	private String messageBody = null;
	private String textHeader = null;
	private String textBody = null;
	private String eventNo = null;
	private long version = 1L;
	
	
	DateTime updateTime = new DateTime();
	private LoyaltyPoints points = new LoyaltyPoints();
	LoyaltyPointsBalance balance = new LoyaltyPointsBalance();
	Barcode barcode = new Barcode();
	List<TextModuleData> textModulesData = new ArrayList<TextModuleData>();
	List<ImageModuleData> imageModulesData = new ArrayList<ImageModuleData>();
	LinksModuleData linksModuleData = new LinksModuleData();
	List<Message> messages = new ArrayList<Message>();
	LoyaltyObject loyaltyObject = new LoyaltyObject();
	utility tool = new utility();
	Config config = Config.getInstance();
	private final String issuerId = config.getISSUER_ID();

	public void setBarcode() {
		barcode = new Barcode()
				.setType(barcodeType)
				.setAlternateText(cardNo)
				.setValue(cardNo);	//	The value encoded in the barcode.
		loyaltyObject.setBarcode(barcode);
	}
	
	public void setLoyaltyPoints() {
		points.setLabel("Points")
	    	  .setBalance(new LoyaltyPointsBalance().setString(loyaltyPoints));
		loyaltyObject.setLoyaltyPoints(points);
	}
	
	//set message label, header and body
	public void setMessageModuleData(String header, String body) {
		Message message = new Message();
		message.setHeader(header)
			   .setBody(body);
		messages.add(message);
		loyaltyObject.setMessages(messages);
	}
	
	//set text modules label, header and body
	public void setTextModuleData(String header, String body) {
		TextModuleData textModuleData = new TextModuleData();
		textModuleData.setHeader(header)
					  .setBody(body);
		textModulesData.add(textModuleData);
		loyaltyObject.setTextModulesData(textModulesData);
	}
	
	
	//set card image, image model data
	public void setCardImage(String imgPath) {
		ImageModuleData cardImage = new ImageModuleData();
		Image objectImg = new Image();
		cardImage.setMainImage(objectImg);
		imageModulesData.add(cardImage);
//		Image mainImg = new Image(); sample code
//		Uri sourceUri = new Uri().setUri("https://www.maccosmetics.co.uk/media/export/cms/giftcards/giftcard-physical-gold.png");
//		mainImg.setSourceUri(sourceUri);
		loyaltyObject.setImageModulesData(imageModulesData);
	}
	
	//set links module data (phone, website link, location)
	public void setLinkModuleData(String phone, String webSite, String map) {
		List<Uri> uris = new ArrayList<Uri>();
		if (!phone.isEmpty()) {
			Uri phoneUri = new Uri().setDescription(phone).setUri("tel:" + phone);
			uris.add(phoneUri);
		}
		if (!webSite.isEmpty()) {
			Uri webSiteUri = new Uri().setDescription("Website link").setUri(webSite);
			uris.add(webSiteUri);
		}
		if (!map.isEmpty()) {
			Uri mapUri = new Uri().setDescription("Loaction").setUri(map);
			uris.add(mapUri);
		}
		linksModuleData.setUris(uris);
		loyaltyObject.setLinksModuleData(linksModuleData);
	}

	//define object
	/**	Class Id	**
	 ** Id			**
	 **	State   	*/
	public void setLoyaltyObjectRequired() {
		loyaltyObject.setClassId(classId)
			  	     .setId(objectId)
			  	     .setState("active")
			  	     .setLoyaltyPoints(points.setBalance(balance.setString(loyaltyPoints)));
	}

	public void prepareAllValues(LoyaltyObjectModel loyaltyObjectModel) {
		this.classId = issuerId + "." + loyaltyObjectModel.getClassId();
		this.objectId = issuerId + "." + loyaltyObjectModel.getObjectId();
		this.cardNo = loyaltyObjectModel.getCardNo();
		this.barcodeType = loyaltyObjectModel.getBarcodeType();
		this.uriLink = loyaltyObjectModel.getImageUri();
		this.messageHeader = loyaltyObjectModel.getMessageHeader();
		this.messageBody = loyaltyObjectModel.getMessageBody();
		this.textHeader = loyaltyObjectModel.getTextHeader();
		this.textBody = loyaltyObjectModel.getTextBody();
		this.eventNo = loyaltyObjectModel.getEventNo();
		setBarcode();
		setLoyaltyPoints();
		setAll();
	}
	public void setAll() {
		loyaltyObject.setClassId(classId)
					 .setId(objectId)
					 .setState("active")
					 .setVersion(version)
					 .setLoyaltyPoints(points.setBalance(balance.setString(loyaltyPoints)));
		setBarcode();
	}
	
	public void setUpdateValue(LoyaltyObject objectResponse, LoyaltyObjectModel loyaltyObjectModel) {
		this.loyaltyPoints = loyaltyObjectModel.getLoyaltyPoints();
		this.barcodeType = loyaltyObjectModel.getBarcodeType();
		setBarcode();
		setLoyaltyPoints();
	}
}
