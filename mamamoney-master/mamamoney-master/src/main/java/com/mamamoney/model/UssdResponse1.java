package com.mamamoney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UssdResponse1 {

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("sessionId")
	private String sessionId;
	
	@JsonProperty("message")
	private String message;
	
	
	public UssdResponse1 () {

	}
	
	
	public UssdResponse1 (String sessionId, String message) {
		this.sessionId = sessionId;
		this.message = message;
	}
	
	
	
}
