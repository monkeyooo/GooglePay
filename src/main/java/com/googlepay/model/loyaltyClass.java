package com.googlepay.model;

import com.google.api.services.walletobjects.model.Image;
import com.google.api.services.walletobjects.model.LoyaltyClass;
import com.googlepay.utility.Config;

import lombok.Data;


@Data
public class loyaltyClass {
	private String classId = null;
	private String issuerName = "Ogloba";
	private String merchantName = "Ogloba-Store";
	private String reviewStatus = "underReview";
	private String programName = "Ogloba-Loyalty";
	private String provider = "Ogloba";
	private String details = "Hello Ogloba";
	Image programLogo = new Image();
	Image heroImage = new Image();
	LoyaltyClass loyaltyClass = new LoyaltyClass();
	Config config = Config.getInstance();
	private final String issuerId = config.getISSUER_ID();

	
	public void setHeroImage() {
		loyaltyClass.setHeroImage(heroImage);
	}
	
	public void setProgramLogo() {	//required
		loyaltyClass.setProgramLogo(programLogo);
	}
	
	/*Required field
	 * classId
	 * issuerName
	 * programName
	 * reviewStatus
	 * programLogo	*/
	public void setAll() {
		loyaltyClass.setId(classId)
					.setIssuerName(issuerName)
					.setProgramName(programName)
					.setProgramLogo(programLogo)
					.setReviewStatus(reviewStatus);
	}
	
	public void prepareAllValues(LoyaltyClassModel loyaltyClassModel) {
		this.classId = issuerId + "." + loyaltyClassModel.getClassId();
		this.issuerName = loyaltyClassModel.getIssuerName();
		this.programName = loyaltyClassModel.getProgramName();
//		this.programLogo = this.programLogo.setSourceUri(new Uri().setUri(loyaltyClassModel.getProgramLogo()));
		this.reviewStatus = loyaltyClassModel.getReviewStatus();
		setAll();
	}
}