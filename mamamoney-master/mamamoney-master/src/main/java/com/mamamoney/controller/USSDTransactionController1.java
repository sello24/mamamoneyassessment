package com.mamamoney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mamamoney.model.UssdRequest1;
import com.mamamoney.model.UssdResponse1;
import com.mamamoney.service.USSDService1;



@Controller
public class USSDTransactionController1 {
	
	@Autowired
	private USSDService1 ussdTransactionService;

	@PostMapping("/ussd")
	public ResponseEntity<UssdResponse1> processRequest(@RequestBody UssdRequest1 ussdRequest) {
		
		UssdResponse1 result = ussdTransactionService.process(ussdRequest);
		
		return ResponseEntity.ok(result);
	}
}
