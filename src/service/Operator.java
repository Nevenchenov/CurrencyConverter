package service;

import financial.Bank;
import financial.CurrencyPair;
import pr.Message;
import techEquipment.*;;

public class Operator extends Thread{
	// get currency quotes list
	private static CurrencyPair[] quotes;
	private static String userRequest;
	private static boolean isVerify;
	private static String operatingResult;
	
	public void run(){
		//get quotes from Bank
		quotes = Bank.getQuotes();

		// show quotes
		showCurrenciesPairs();
		pause(2);
		
		// show currency pair available
		showAvailableConversions();
		pause(2);
		
		// prompt to start with explanation how to
		howToConvertExplanation();
		
		// loop operating
		while(true){
			//read request
			userRequest = Reader.readRequest();
			// verify
			isVerify = Verifier.verifyRequest(userRequest);		
			// convert if correct or 
			if(isVerify){
				operatingResult = Converter.convert(userRequest) + Message.promptToNextConversion;
			} else {
				// ask to confirm exit if empty or ask to reenter if incorrect
				if(Verifier.getVerifyVerdict().equals(Message.requestToExit)){
			
						Writer.write(Message.requestToExit);
						// confirm exit
						if(Reader.readRequest().equals("")){
							
							break;
						}
						//not confirm exit: prompt to input next
						else {
							Writer.write(Message.promptToNextConversion);
							continue;
						}
				} else {
					operatingResult = Verifier.getVerifyVerdict();
				}
			}
			Writer.write(operatingResult);
		}
	}
		
			
	// Show Currencies pairs
	private static void showCurrenciesPairs(){
		Writer.write(Message.quotesBelow);
		for(int i = 0; i < quotes.length; i++){
			String currentPair = quotes[i].getFirstCurrency().getName() + "/" + quotes[i].getSecondCurrency().getName();
			Float bid = quotes[i].getBid();
			Float ask = quotes[i].getAsk();
			Writer.write((i+1) + ". " + currentPair + ", bid = " + bid + ", ask = " + ask);
		}
		Writer.write(" ");
	}
	
	// Show what currencies available to conversion
	private static void showAvailableConversions(){
		Writer.write(Message.pairsOverview);
		for(int i = 0; i < quotes.length; i++){
			
			Writer.writeInLine("  " + quotes[i].getFirstCurrency().getName() + "-" + quotes[i].getSecondCurrency().getName());
			// five pairs are in one line to improve readability
			if(i>0&&(i+1)%5==0){
				Writer.write("");	
			}
		}
		Writer.write("");
		Writer.write("");
	}
	
	// explanation how to input and prompt to input
	private static void howToConvertExplanation(){
		Writer.write(Message.convertingRules);
		Writer.write(Message.userInputPrompt);
	}
	
	private static void pause(int delay){
		try {
			Thread.sleep(delay*1000);
		} catch (InterruptedException e1) {
			Writer.write(Message.ifException);
			// log e1.printStackTrace();
		}
	}

	public void finish(){
			Reader.closeReader();
	}
	
}