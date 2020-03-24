package com.googlepay.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(description = "Loyalty Object Model")
public class LoyaltyObjectModel {
	@ApiModelProperty(notes = "Class ID", required = true)
	private String classId = null;
	@ApiModelProperty(notes = "Object ID", required = true)
	private String objectId = null;
	@ApiModelProperty(notes = "Card No", required = true)
	private String cardNo = null;
	@ApiModelProperty(notes = "Loyalty Points", required = false)
	private String loyaltyPoints = null;
	@ApiModelProperty(notes = "Event No", required = false)
	private String eventNo = null;
	@ApiModelProperty(notes = "Barcode Type", required = true)
	private String barcodeType = null;//Could change barcode type when you update object each time
	@ApiModelProperty(notes = "Text Header", required = false)
	String textHeader = null;
	@ApiModelProperty(notes = "Text Body", required = false)
	String textBody = null;
	@ApiModelProperty(notes = "Image Uri", required = false)
	String imageUri = null;
	@ApiModelProperty(notes = "Message Header", required = false)
	String messageHeader = null;
	@ApiModelProperty(notes = "Message Body", required = false)
	String messageBody = null;
	@ApiModelProperty(notes = "Version", required = false)
	long version = 0L;


}
