package com.googlepay.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;



@Data
@ApiModel(description = "Giftcard Class Model")
public class GiftcardClassModel {
	@ApiModelProperty(notes = "Issuer ID", required = true)
	@NonNull private String issuerId = null;
	@ApiModelProperty(notes = "Class ID", required = true)
	@NonNull private String classId = null;
	@ApiModelProperty(notes = "Issuer Name", required = true)
	private String issuerName = null;
	@ApiModelProperty(notes = "Merchant ID", required = true)
	private String merchantName = null;
	@ApiModelProperty(notes = "Pin Label", required = false)
	private String pinLabel = null;
	@ApiModelProperty(notes = "Event Header", required = false)
	private String eventHeader = null;
	@ApiModelProperty(notes = "Text Header", required = false)
	String textHeader = null;
	@ApiModelProperty(notes = "Text Body", required = false)
	String textBody = null;
	@ApiModelProperty(notes = "Message Header", required = false)
	String messageHeader = null;
	@ApiModelProperty(notes = "Message Body", required = false)
	String messageBody = null;
	@ApiModelProperty(notes = "Hero Image Uri", required = false)
	String heroImageUri = null;
	@ApiModelProperty(notes = "Program Logo Uri", required = false)
	String programLogoUri = null;
	@ApiModelProperty(notes = "Phone Description", required = false)
	private String phoneDesc = null;
	@ApiModelProperty(notes = "Phone Uri", required = false)
	private String phoneUri = null;
	@ApiModelProperty(notes = "Website Description", required = false)
	private String webDesc = null;
	@ApiModelProperty(notes = "Website Uri", required = false)
	private String webUri = null;
	@ApiModelProperty(notes = "Country Code", required = false)
	private String countryCode = null;
	@ApiModelProperty(notes = "Class Status", required = true)
	@NonNull private String status = null;
	@ApiModelProperty(notes = "Version", required = false)
	long version = 0L;
	
	
}
