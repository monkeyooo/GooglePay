package com.googlepay.model;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.walletobjects.model.Barcode;
import com.google.api.services.walletobjects.model.Image;
import com.google.api.services.walletobjects.model.ImageModuleData;
import com.google.api.services.walletobjects.model.InfoModuleData;
import com.google.api.services.walletobjects.model.LabelValue;
import com.google.api.services.walletobjects.model.LabelValueRow;
import com.google.api.services.walletobjects.model.LinksModuleData;
import com.google.api.services.walletobjects.model.Message;
import com.google.api.services.walletobjects.model.OfferObject;
import com.google.api.services.walletobjects.model.TextModuleData;
import com.google.api.services.walletobjects.model.Uri;
import com.googlepay.utility.utility;

import lombok.Data;



@Data
public class offerObject {
	private String classId = null;
	private String objectId = null;
	private String cardNo = null;
	private String shortCardNo = null;
	private String barcodeType = "code128"; //default setting
	Barcode barcode = new Barcode();
	List<TextModuleData> textModulesData = new ArrayList<TextModuleData>();
	List<ImageModuleData> imageModulesData = new ArrayList<ImageModuleData>();
	LinksModuleData linksModuleData = new LinksModuleData();
	List<Message> messages = new ArrayList<Message>();
	InfoModuleData infoModuleData = new InfoModuleData();
	OfferObject object = new OfferObject();
	utility tool = new utility();

	public void setBarcode(String cardNo) {
		barcode = new Barcode()
				.setType(barcodeType)
				.setAlternateText(shortCardNo)
				.setValue(cardNo);	//	The value encoded in the barcode.
		object.setBarcode(barcode);
	}

	//set message label, header and body
	public void setMessageModuleData(String header, String body) {
		Message message = new Message();
		message.setHeader(header)
			   .setBody(body);
		messages.add(message);
		object.setMessages(messages);
	}
	
	//set text modules label, header and body
	public void setTextModuleData(String header, String body) {
		TextModuleData textModuleData = new TextModuleData();
		textModuleData.setHeader(header)
					  .setBody(body);
		textModulesData.add(textModuleData);
		object.setTextModulesData(textModulesData);
	}
	
	//set info modules label, header and body
	public void setInfoModuleData(String header, String body) {
		LabelValue column = new LabelValue()
								.setLabel(header)
								.setValue(body);
		List<LabelValue> columns = new ArrayList<LabelValue>();
		columns.add(column);
		LabelValueRow labelValueRow = new LabelValueRow()
				.setColumns(columns);
		List<LabelValueRow> labelValueRows = new ArrayList<LabelValueRow>();
		labelValueRows.add(labelValueRow);
		infoModuleData = new InfoModuleData()
							 .setShowLastUpdateTime(true)
							 .setLabelValueRows(labelValueRows);
		object.setInfoModuleData(infoModuleData);
	}
	
	//set card image, image model data
	public void setCardImage(String imgPath) {
		ImageModuleData cardImage = new ImageModuleData();
		Image objectImg = new Image();
		cardImage.setMainImage(objectImg);
		imageModulesData.add(cardImage);
		//Image mainImg = new Image(); sample code
		//Uri sourceUri = new Uri().setUri("https://www.maccosmetics.co.uk/media/export/cms/giftcards/giftcard-physical-gold.png");
		//mainImg.setSourceUri(sourceUri);
		object.setImageModulesData(imageModulesData);
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
		object.setLinksModuleData(linksModuleData);
	}
	
	public void setOfferObjectRequired() {
		object.setClassId(classId)
		      .setId(objectId)
		      .setState("active");
	}


}