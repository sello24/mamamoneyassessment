package com.mamamoney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UssdRequest1 {

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getUserEntry() {
		return userEntry;
	}

	public void setUserEntry(String userEntry) {
		this.userEntry = userEntry;
	}

	@JsonProperty("sessionId")
	private String sessionId;
	
	@JsonProperty("msisdn")
	private String msisdn;
	
	@JsonProperty("userEntry")
	private String userEntry;
	
	
	public UssdRequest1() {

	}

	//@Builder
	public UssdRequest1(String sessionId, String msisdn, String userEntry) {
		this.sessionId = sessionId;
		this.msisdn = msisdn;
		this.userEntry = userEntry;
	}
}
