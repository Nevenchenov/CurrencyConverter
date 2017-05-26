package techEquipment;

import financial.Bank;
import financial.Currency;
import financial.CurrencyPair;

// should be called exclusively after Verifier!
public class Converter {
	private static Float userSum;
	private static float targetSum = 0f;
	private static Currency userCurrency;
	private static Currency targetCurrency;



	public static String convert(String request){
		//extract float value of user sum
		userSum = Float.parseFloat(request.split(" ")[0]);
		
		int pairNumberIndex = Verifier.getNumberOfPairToConvert();
		CurrencyPair pair = Bank.getQuotes()[pairNumberIndex];
		
		// direct or reverse conversion
		if(Verifier.getDirectionOfConversion()){	
			targetSum = userSum*pair.getBid();
			userCurrency = pair.getFirstCurrency();
			targetCurrency = pair.getSecondCurrency();		
		} else {	
			targetSum = userSum/pair.getAsk();
			userCurrency = pair.getSecondCurrency();
			targetCurrency = pair.getFirstCurrency();
		}

		return (formatter(userSum) + " " + userCurrency.getName() + "   ->   " + formatter(targetSum) + " " + targetCurrency.getName());
	}

	// sets accuracy of calculations
	private static String formatter(Float number){
		return String.format("%(.2f", number);
	}

}