package com.googlepay.model;

import com.google.api.services.walletobjects.model.Image;
import com.google.api.services.walletobjects.model.OfferClass;
import com.googlepay.utility.Config;

import lombok.Data;


@Data
public class offerClass {
	private String classId = null;
	private String issuerName = "Ogloba";
	private String merchantName = "Ogloba-Store";
	private String reviewStatus = "underReview";
	private String programName = "Ogloba-Loyalty";
	private String provider = "Ogloba";
	private String details = "Hello Ogloba";
	Image titleImage = new Image();
	Image heroImage = new Image();
	OfferClass offerClass = new OfferClass();
	Config config = Config.getInstance();

	public void setHeroImage() {
		offerClass.setHeroImage(heroImage);
	}
	
	public void setTitleImage() {	//required
		offerClass.setTitleImage(titleImage);
	}
	
	/*Required field
	 * classId
	 * issuerName
	 * programName
	 * reviewStatus
	 * programLogo	*/
	public void setLoyaltyClass() {
		offerClass.setId(config.getISSUER_ID()+"."+classId)
				  .setIssuerName(issuerName)
				  .setReviewStatus(reviewStatus);
	}
	
	public OfferClass getLoyaltyClass() {
		return this.offerClass;
	}

	public void prepareAllValues(OfferClassModel offerClassModel) {
		// TODO Auto-generated method stub
		
	}

}
