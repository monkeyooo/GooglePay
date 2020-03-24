package com.googlepay.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;


@Data
@ApiModel(description = "Giftcard Object Model")
public class GiftcardObjectModel {
	@ApiModelProperty(notes = "Issuer ID", required = true)
	@NonNull private String issuerId = null;
	@ApiModelProperty(notes = "Class ID", required = true)
	@NonNull private String classId = null;
	@ApiModelProperty(notes = "Object ID", required = true)
	@NonNull private String objectId = null;
	@ApiModelProperty(notes = "Card No", required = true)
	@NonNull private String cardNo = null;
	@ApiModelProperty(notes = "Face Value", required = true)
	@NonNull private BigDecimal faceValue = null;
	@ApiModelProperty(notes = "Pin Code", required = false)
	private String pinCode = null;
	@ApiModelProperty(notes = "Event No", required = false)
	private String eventNo = null;
	@ApiModelProperty(notes = "Barcode Type", required = true)
	@NonNull private String barcodeType = null;//Could change barCode type when you update object each time
	@ApiModelProperty(notes = "Currency Code", required = true)
	@NonNull private String currencyCode = null;
//	@ApiModelProperty(notes = "Text Header", required = false)
//	String textHeader = null;
//	@ApiModelProperty(notes = "Text Body", required = false)
//	String textBody = null;
//	@ApiModelProperty(notes = "Image Uri", required = false)
//	String imageUri = null;
//	@ApiModelProperty(notes = "Message Header", required = false)
//	String messageHeader = null;
//	@ApiModelProperty(notes = "Message Body", required = false)
//	String messageBody = null;
	@ApiModelProperty(notes = "Card Status", required = true)
	@NonNull private String cardStatus = null;
	@ApiModelProperty(notes = "Version", required = false)
	long version = 0L;

}
