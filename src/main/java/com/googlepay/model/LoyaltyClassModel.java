package com.googlepay.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(description = "Loyalty Class Model")
public class LoyaltyClassModel {
	@ApiModelProperty(notes = "Class ID", required = true)
	private String classId = null;
	@ApiModelProperty(notes = "Issuer ID", required = true)
	private String issuerName = null;
	@ApiModelProperty(notes = "programName", required = true)
	private String programName = null;
	@ApiModelProperty(notes = "programLogo", required = true)
	private String programLogo = null;
	@ApiModelProperty(notes = "Status", required = true)
	private String reviewStatus = null;
	
}
