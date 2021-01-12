package com.mamamoney.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mamamoney.constant.MenuScreensEnum;
import com.mamamoney.exception.MenuExceptionClass;

public class TransactionInformation1 {


	private LocalDateTime lastReponse;
	
	public LocalDateTime getLastReponse() {
		return lastReponse;
	}

	public void setLastReponse(LocalDateTime lastReponse) {
		this.lastReponse = lastReponse;
	}

	public MenuScreensEnum getActiveMenu() {
		return activeMenu;
	}

	public void setActiveMenu(MenuScreensEnum activeMenu) {
		this.activeMenu = activeMenu;
	}

	public List<String> getParameters() {
		return parameters;
	}

	private MenuScreensEnum activeMenu;
	
	private final List<String> parameters = new ArrayList<>();

	public TransactionInformation1(MenuScreensEnum activeMenu) {
		this.activeMenu = activeMenu;
		
		lastReponse = LocalDateTime.now();
	}
	
	public TransactionInformation1(){}
	
	
	public String computeMessage() throws MenuExceptionClass {
		String result = null;
		
		try {
			result = activeMenu.getNextMenu().getMessage(parameters);
			
			activeMenu = activeMenu.getNextMenu();
		} catch (MenuExceptionClass e) {
			parameters.remove(parameters.size() - 1);
			
			result = activeMenu.getMessage(parameters);
		}
		
		lastReponse = LocalDateTime.now();
		
		return result;
	}
	
	public boolean hasNextMenuScreen() {
		return activeMenu.hasNextMenuScreen();
	}
}
