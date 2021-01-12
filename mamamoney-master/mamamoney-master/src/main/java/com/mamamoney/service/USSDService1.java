package com.mamamoney.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

import com.mamamoney.constant.MenuScreensEnum;
import com.mamamoney.exception.MenuExceptionClass;
import com.mamamoney.model.UssdRequest1;
import com.mamamoney.model.UssdResponse1;
import com.mamamoney.model.TransactionInformation1;

@Service
public class USSDService1 {
	
	private static Map<String, TransactionInformation1> userSession = new HashMap<>();

	Logger log;
	
	public UssdResponse1 process(UssdRequest1 ussdRequest) {
		UssdRequest1 request = new UssdRequest1();
		UssdResponse1 result = new UssdResponse1();
		TransactionInformation1 transactionInfo = null;
		
		if (userSession.get(ussdRequest.getSessionId()) == null) {
			transactionInfo = initializeTransaction(ussdRequest);
		} else {
			transactionInfo = updateTransaction(ussdRequest);
		}
		
		try {
			result.setSessionId(ussdRequest.getSessionId());
			result.setMessage(transactionInfo.computeMessage());
		} catch (MenuExceptionClass e) {
			log.severe("Oops! something went wrong. Your Transaction could not be completed");
			userSession.remove(ussdRequest.getSessionId());
			request.setMsisdn(ussdRequest.getMsisdn());
			request.setSessionId(ussdRequest.getSessionId());
	
		}
		
		if (!transactionInfo.hasNextMenuScreen()) {
			userSession.remove(ussdRequest.getSessionId());
		}
		
		return result; 
	}
	
	@Scheduled(fixedDelay = 2000) //Auto clear all timedout sessions
	public void cleanupTimedoutSessions() {
		
		LocalDateTime now = LocalDateTime.now();
			userSession.keySet().removeAll(userSession.entrySet().stream().filter(value -> value.getValue().getLastReponse().plusSeconds(20).isBefore(now)).map(map -> map.getKey()).collect(Collectors.toList()));
	}

	private TransactionInformation1 updateTransaction(UssdRequest1 ussdRequest) {
		TransactionInformation1 transactionInfo;
		
		transactionInfo = userSession.get(ussdRequest.getSessionId()); 
		transactionInfo.getParameters().add(ussdRequest.getUserEntry());
		
		return transactionInfo;
	}

	private TransactionInformation1 initializeTransaction(UssdRequest1 ussdRequest) {
		TransactionInformation1 transactionInfo = new TransactionInformation1();
		MenuScreensEnum menu = MenuScreensEnum.INSTANCE;
		
		transactionInfo.setActiveMenu(menu);
	
		userSession.put(ussdRequest.getSessionId(), transactionInfo);
		
		return transactionInfo;
	}
}
