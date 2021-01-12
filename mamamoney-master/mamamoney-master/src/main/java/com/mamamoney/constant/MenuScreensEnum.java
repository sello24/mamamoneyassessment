package com.mamamoney.constant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.mamamoney.exception.MenuExceptionClass;

public enum MenuScreensEnum {
	INSTANCE {

		@Override
		public String getMessage(List<String> parameters) throws MenuExceptionClass {
			// No message available at initialization
			return null;
		}

		@Override
		public MenuScreensEnum getNextMenu() {
			return WELCOME_SCREEN;
		}

		@Override
		public boolean hasNextMenuScreen() {
			return true;
		}
		
	},
	
	WELCOME_SCREEN {

		@Override
		public String getMessage(List<String> parameters) throws MenuExceptionClass {
			return "Welcome to Mama Money! Where would you like to send money?" + System.lineSeparator() + "1) "
					+ CountriesEnum.KENYA.getDisplayName() + System.lineSeparator() + "2) " + CountriesEnum.MALAWI.getDisplayName();
		}

		@Override
		public MenuScreensEnum getNextMenu() {
			return TRANSACTION_SCREEN;
		}

		@Override
		public boolean hasNextMenuScreen() {
			return true;
		}
		
	},
	
	TRANSACTION_SCREEN {
		@Override
		public String getMessage(List<String> parameters) throws MenuExceptionClass {
			if (parameters == null || parameters.isEmpty() || parameters.get(0).trim().length() == 0
					|| !"12".contains(parameters.get(0))) {
				throw new MenuExceptionClass("Invalid input found");
			}
			
			int selectedCountry = Integer.parseInt(parameters.get(0));
			CountriesEnum CountriesEnum = null;
			
			switch(selectedCountry) {
			case 1:
				CountriesEnum = CountriesEnum.KENYA;
				break;
			case 2:
				CountriesEnum = CountriesEnum.MALAWI;
				break;
			}
			
			return "How much (in Rands) would you like to send to " + CountriesEnum.getDisplayName() + "?";
		}

		@Override
		public MenuScreensEnum getNextMenu() {
			return CONFIRMATION_SCREEN;
		}

		@Override
		public boolean hasNextMenuScreen() {
			return true;
		}
		
	},
	
	CONFIRMATION_SCREEN {

		@Override
		public String getMessage(List<String> parameters) throws MenuExceptionClass {
			if (parameters == null || parameters.size() < 2 || parameters.get(0).trim().length() == 0
					|| !"12".contains(parameters.get(0))
					|| !parameters.get(1).matches("\\d+")) {
				throw new MenuExceptionClass("Invalid Input");
			}
			
			int selectedCountry = Integer.parseInt(parameters.get(0));
			String amount = parameters.get(1);
			BigDecimal transferAmount = null;
			CountriesEnum CountriesEnum = null;
			
			switch(selectedCountry) {
			case 1:
				CountriesEnum = CountriesEnum.KENYA;
				transferAmount = convertAmount(amount, CountriesEnum.KENYA);
				break;
			case 2:
				CountriesEnum = CountriesEnum.MALAWI;
				transferAmount = convertAmount(amount, CountriesEnum.MALAWI);
				break;
			}
			
			return "The person you are sending to will receive an amount of: " + transferAmount + " " + CountriesEnum.getCountryCode() + System.lineSeparator() + "1) OK";
		}

		@Override
		public MenuScreensEnum getNextMenu() {
			return THANK_YOU_SCREEN;
		}

		@Override
		public boolean hasNextMenuScreen() {
			return true;
		}
		
		private BigDecimal convertAmount(String amount, CountriesEnum CountriesEnum) {
			BigDecimal transferAmount = new BigDecimal(amount);
			
			transferAmount = transferAmount.multiply(CountriesEnum.getConversionRate());
			transferAmount = transferAmount.setScale(2, RoundingMode.CEILING);
			
			return transferAmount;
		}
		
	},
	
	THANK_YOU_SCREEN {

		@Override
		public String getMessage(List<String> parameters) throws MenuExceptionClass {
			return "Thank you for using Mama Money!!";
		}

		@Override
		public MenuScreensEnum getNextMenu() {
			return this;
		}

		@Override
		public boolean hasNextMenuScreen() {
			return false;
		}
		
	};
	
	public abstract String getMessage(List<String> parameters) throws MenuExceptionClass;
	
	public abstract MenuScreensEnum getNextMenu();
	
	public abstract boolean hasNextMenuScreen();
}
