package techEquipment;

import financial.Bank;
import financial.Currency;
import financial.CurrencyPair;
import pr.Message;

/* now it check for:
 *  1.Empty input;
 *  2.Format correctness (3 values divided by space
 *  3.Sum correctness
 *  4.Existence of currency pair in list and correctness of currency names input simultaneously 
 */
public class Verifier {
	private static boolean isVerified = true;
	private static String getVerifyVerdict;
	private static boolean directConversion;
	private static int numberOfPairToConvert;
	private static boolean pairExistenceChecker = false; //using of two previous for check is incorrect at second user entry and next
	
	public static boolean verifyRequest(String request){
		//empty entry detection (symptom to exit)
		if(request.equals("")){
			isVerified = false;
			getVerifyVerdict = Message.requestToExit;
		} else{
		//extract sum and source&target currencies
			String[] userStringSplitted = request.split(" ");
			if(userStringSplitted.length == 3){
				String userSumString = userStringSplitted[0];
				String userCurrency = userStringSplitted[1];
				String targetCurrency = userStringSplitted[2];
				
				//verify sum correctness
				if(!isNumberCorrect(userSumString)){
					isVerified = false;
					getVerifyVerdict = Message.incorrectSum;
				} else {
					//finding pair in Bank
					CurrencyPair[] currencyPairs = Bank.getQuotes();
					for(int i = 0; i < currencyPairs.length; i++){
						Currency firstCurrency = currencyPairs[i].getFirstCurrency();
						Currency secondCurrency = currencyPairs[i].getSecondCurrency();
	
						if(firstCurrency.getName().equals(userCurrency)&&secondCurrency.getName().equals(targetCurrency)){
							directConversion = true;
							numberOfPairToConvert = i;
							pairExistenceChecker = true;
							isVerified = true;
							break;
						}
						if(firstCurrency.getName().equals(targetCurrency)&&secondCurrency.getName().equals(userCurrency)){
							directConversion = false;
							numberOfPairToConvert = i;
							pairExistenceChecker = true;
							isVerified = true;
							break;
						}
					}
					//if pair is not found in the Bank
					if(pairExistenceChecker==false){ 
						isVerified = false;
						getVerifyVerdict = Message.wrongPair;
					} else{
						//pair exist and checker reset for next user entry
						pairExistenceChecker = false;
					}
				}
			} else {
				isVerified = false;
				getVerifyVerdict = Message.incorrectEntry;
			}
		}
		
		return isVerified;
	}

	
	private static boolean isNumberCorrect(String numberString){
		boolean is = false;
		if(numberString.matches("\\d+\\.\\d+")||numberString.matches("\\d+")){
			is = true;
		}
		return is;
	}
	
	public static String getVerifyVerdict(){
		return getVerifyVerdict;
	}
	
	public static boolean getDirectionOfConversion(){
		return directConversion;
	}
	
	public static int getNumberOfPairToConvert(){
		return numberOfPairToConvert;
	}
}