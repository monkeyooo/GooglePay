package com.googlepay.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.api.services.walletobjects.model.Barcode;
import com.google.api.services.walletobjects.model.DateTime;
import com.google.api.services.walletobjects.model.GiftCardObject;
import com.google.api.services.walletobjects.model.ImageModuleData;
import com.google.api.services.walletobjects.model.InfoModuleData;
import com.google.api.services.walletobjects.model.LinksModuleData;
import com.google.api.services.walletobjects.model.Message;
import com.google.api.services.walletobjects.model.Money;
import com.google.api.services.walletobjects.model.TextModuleData;
import com.googlepay.utility.utility;

import lombok.Data;



@Data
public class giftcardObject {
	private String classId = null;
	private String objectId = null;
	private String cardNo = null;
	private BigDecimal faceValue = null;
	private String pinCode = null;
	private String eventNo = null;
	private String shortCardNo = null;
	private String barcodeType = null;//"code128";//default setting
	private String uriLink = null;
	private String messageHeader = null;
	private String messageBody = null;
	private String textHeader = null;
	private String textBody = null;
	private String currencyCode = null;
	private String state = null;
	private long version;
	List<TextModuleData> textModulesData = new ArrayList<TextModuleData>();
	List<ImageModuleData> imageModulesData = new ArrayList<ImageModuleData>();
	LinksModuleData linksModuleData = new LinksModuleData();
	List<Message> messages = new ArrayList<Message>();
	
	Money balance = new Money();
	DateTime balanceUpdateTime = new DateTime();
	Barcode barcode = new Barcode();
	InfoModuleData infoModuleData = new InfoModuleData();
	GiftCardObject giftcardObject = new GiftCardObject();
	utility tool = new utility();
//	Config config = Config.getInstance();
	
	public void defineStatus(String cardStatus) {
		this.state = "active";
	}
	
	public void setBarcode() {
		barcode = new Barcode()
				.setType(barcodeType)
				.setAlternateText(cardNo)
				.setValue(cardNo);	//	The value encoded in the barcode.
		giftcardObject.setBarcode(barcode);
	}
	
	public void setBalance() {
		long balanceFaceVal = tool.convertBalance(faceValue, currencyCode);
		balance.setMicros(balanceFaceVal).setCurrencyCode(currencyCode);
		balanceUpdateTime.setDate(new com.google.api.client.util.DateTime(new Date()).toString());
		giftcardObject.setBalance(balance)
			  		  .setBalanceUpdateTime(balanceUpdateTime);
	}
	
	//set message label, header and body
//	public void setMessageModuleData(String header, String body) {
//		WalletObjectMessage message = new WalletObjectMessage();
//		message.setHeader(header)
//			   .setBody(body);
//		messages.add(message);
//		giftcardObject.setMessages(messages);
//	}
//	
//	//set text modules label, header and body
//	public void setTextModuleData(String header, String body) {
//		TextModuleData textModuleData = new TextModuleData();
//		textModuleData.setHeader(header)
//					  .setBody(body);
//		textModulesData.add(textModuleData);
//		giftcardObject.setTextModulesData(textModulesData);
//	}
//	
//	//set card image, image model data
//	public void setCardImage() {
//		ImageModuleData cardImage = new ImageModuleData();
//		Image objectImg = new Image();
//		cardImage.setMainImage(objectImg);
//		imageModulesData.add(cardImage);
////		Image mainImg = new Image(); sample code
////		Uri sourceUri = new Uri().setUri("https://www.maccosmetics.co.uk/media/export/cms/giftcards/giftcard-physical-gold.png");
////		mainImg.setSourceUri(sourceUri);
//		giftcardObject.setImageModulesData(imageModulesData);
//	}
//	
//	//set links module data (phone, website link, location)
//	public void setLinkModuleData(String phone, String webSite, String map) {
//		List<Uri> uris = new ArrayList<Uri>();
//		if (!phone.isEmpty()) {
//			Uri phoneUri = new Uri().setDescription(phone).setUri("tel:" + phone);
//			uris.add(phoneUri);
//		}
//		if (!webSite.isEmpty()) {
//			Uri webSiteUri = new Uri().setDescription("Website link").setUri(webSite);
//			uris.add(webSiteUri);
//		}
//		if (!map.isEmpty()) {
//			Uri mapUri = new Uri().setDescription("Loaction").setUri(map);
//			uris.add(mapUri);
//		}
//		linksModuleData.setUris(uris);
//		giftcardObject.setLinksModuleData(linksModuleData);
//	}
	
	// Define Wallet Instance
	/* 	required fields **
	**	CardNumber		**
	**	ClassId			**
	**	Id				**
	**	State			*/
	public void prepareAllValues(GiftcardObjectModel giftcardObjectModel) {
		this.classId = tool.makeId(giftcardObjectModel.getIssuerId(), giftcardObjectModel.getClassId());
		this.objectId = tool.makeId(giftcardObjectModel.getIssuerId(), giftcardObjectModel.getObjectId());
		this.faceValue = giftcardObjectModel.getFaceValue();
		this.cardNo = giftcardObjectModel.getCardNo();
		this.pinCode = giftcardObjectModel.getPinCode();
		this.barcodeType = giftcardObjectModel.getBarcodeType();
//		this.uriLink = giftcardObjectModel.getImageUri();
//		this.messageHeader = giftcardObjectModel.getMessageHeader();
//		this.messageBody = giftcardObjectModel.getMessageBody();
//		this.textHeader = giftcardObjectModel.getTextHeader();
//		this.textBody = giftcardObjectModel.getTextBody();
		this.eventNo = giftcardObjectModel.getEventNo();
		this.currencyCode = giftcardObjectModel.getCurrencyCode();
		defineStatus(giftcardObjectModel.getCardStatus());
		setAll();
	}
	
	public void setAll() {
		giftcardObject.setClassId(classId)
					  .setId(objectId)
					  .setCardNumber(cardNo)
					  .setEventNumber(eventNo)
					  .setPin(pinCode)
					  .setState(state)
					  .setVersion(version);
		setBalance();
		setBarcode();
	}
	
	public void setUpdateValue(GiftCardObject objectResponse, BigDecimal faceValue, String barcodeType) {
		this.faceValue = faceValue;
		this.barcodeType = barcodeType;
		this.currencyCode = objectResponse.getBalance().getCurrencyCode();
		this.giftcardObject.setVersion(Long.sum(objectResponse.getVersion(), 1L));
		setBalance();
		setBarcode();
	}
}
