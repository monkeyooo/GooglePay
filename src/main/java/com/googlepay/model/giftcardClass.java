package com.googlepay.model;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.walletobjects.model.GiftCardClass;
import com.google.api.services.walletobjects.model.Image;
import com.google.api.services.walletobjects.model.ImageModuleData;
import com.google.api.services.walletobjects.model.ImageUri;
import com.google.api.services.walletobjects.model.LinksModuleData;
import com.google.api.services.walletobjects.model.Message;
import com.google.api.services.walletobjects.model.TextModuleData;
import com.google.api.services.walletobjects.model.Uri;
import com.googlepay.utility.utility;

import lombok.Data;


@Data
public class giftcardClass {
	private String classId = null;
	private String issuerName = null;
	private String merchantName = null;
	private String pinLabel = null;
	private String reviewStatus = "underReview";
	private String textHeader = null;
	private String textBody = null;
	private String cardImageUri = null;
	private String programLogoUri = null;
	private String heroImageUri = null;
	private String eventHeader = null;
	private String messageHeader = null;
	private String messageBody = null;
	private String multipleDevicesAndHoldersAllowedStatus = null;
	private String webSiteUri = null;
	private String phoneDesc = null;
	private String phoneUri = null;
	private String webDesc = null;
	private String webUri = null;
	private String status = null;
	private String countryCode = null;
	
	GiftCardClass giftcardClass = new GiftCardClass();

	List<TextModuleData> textModulesData = new ArrayList<TextModuleData>();
	List<ImageModuleData> imageModulesData = new ArrayList<ImageModuleData>();
	LinksModuleData linksModuleData = new LinksModuleData();
	List<Message> messages = new ArrayList<Message>();
//	Config config = Config.getInstance();
	utility tool = new utility();
	
	private void defineReviewStatus(String status) {
		if ("1".equals(status)) {
			this.reviewStatus = "UNDER_REVIEW";
		} else {
			this.reviewStatus = "DRAFT";
		}
	}
	
	public void setHeroImage() {
		Image heroImage = new Image();
		ImageUri sourceUri = new ImageUri();
		sourceUri.setUri(heroImageUri);
		heroImage.setSourceUri(sourceUri);
		giftcardClass.setHeroImage(heroImage);
	}
	
	public void setProgramLogo() {
		Image programLogo = new Image();
		ImageUri sourceUri = new ImageUri();
		sourceUri.setUri(programLogoUri);
		programLogo.setSourceUri(sourceUri);
		giftcardClass.setProgramLogo(programLogo);
	}
	
	public void setEventHeader() {
		giftcardClass.setEventNumberLabel(eventHeader);
	}
	
	//set message label, header and body
	public void setMessageModuleData() {
		Message message = new Message();
		message.setHeader(messageHeader)
			   .setBody(messageBody);
		messages.add(message);
		giftcardClass.setMessages(messages);
	}
	
	//set text modules label, header and body
	public void setTextModuleData() {
		TextModuleData textModuleData = new TextModuleData();
		textModuleData.setHeader(textHeader)
					  .setBody(textBody);
		textModulesData.add(textModuleData);
		giftcardClass.setTextModulesData(textModulesData);
	}
	
	//set card image, image model data
	public void setCardImage() {
		ImageModuleData cardImage = new ImageModuleData();
		Image cardImg = new Image();
		ImageUri sourceUri = new ImageUri();
		sourceUri.setUri(cardImageUri);
		cardImg.setSourceUri(sourceUri);
		cardImage.setMainImage(cardImg);
		imageModulesData.add(cardImage);
		giftcardClass.setImageModulesData(imageModulesData);
	}
	
	//set links module data (phone, website link, location)
	public void setLinkModuleData() {
		List<Uri> uris = new ArrayList<Uri>();
//		Uri phone_Uri = new Uri().setDescription(phoneDesc).setUri("tel:" + phoneUri);
//		uris.add(phone_Uri);
		Uri webSiteUri = new Uri().setDescription(webDesc).setUri(webUri);
		uris.add(webSiteUri);		
//		Uri mapUri = new Uri().setDescription("Loaction").setUri(map);
//		uris.add(mapUri);
		
		linksModuleData.setUris(uris);
		giftcardClass.setLinksModuleData(linksModuleData);
	}
	
	/*Set Giftcard Class Required field
	 * class Id
	 * Issuer Name
	 * Review Status					*/
	public void setAll() {
		giftcardClass.setId(classId)
		 			 .setIssuerName(issuerName)
		 			 .setMerchantName(merchantName)
		 			 .setReviewStatus(reviewStatus)
		 			 .setPinLabel(pinLabel)
		 			 .setCountryCode(countryCode)
		 			 .setAllowBarcodeRedemption(true);
		if (heroImageUri != null) setHeroImage();
		if (programLogoUri != null)	setProgramLogo();
		setEventHeader();
		setTextModuleData();
		setMessageModuleData();
		setLinkModuleData();
	}
	
	public void prepareAllValues(GiftcardClassModel giftcardClassModel) {
		this.classId = tool.makeId(giftcardClassModel.getIssuerId(), giftcardClassModel.getClassId());
		this.issuerName = giftcardClassModel.getIssuerName();
		this.merchantName = giftcardClassModel.getMerchantName();
		this.heroImageUri = giftcardClassModel.getHeroImageUri();
		this.programLogoUri = giftcardClassModel.getProgramLogoUri();
		this.pinLabel = giftcardClassModel.getPinLabel();
		this.eventHeader = giftcardClassModel.getEventHeader();
		this.textHeader = giftcardClassModel.getTextHeader();
		this.textBody = giftcardClassModel.getTextBody();
		this.messageHeader = giftcardClassModel.getMessageHeader();
		this.messageBody = giftcardClassModel.getMessageBody();
		this.phoneDesc = giftcardClassModel.getPhoneDesc();
		this.phoneUri = giftcardClassModel.getPhoneUri();
		this.webDesc = giftcardClassModel.getWebDesc();
		this.webUri = giftcardClassModel.getWebUri();
		this.status = giftcardClassModel.getStatus();
		this.countryCode = giftcardClassModel.getCountryCode();
		
		defineReviewStatus(status);
		setAll();
	}
}