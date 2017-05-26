package pr;

public class Message {
	private static String newLine = System.getProperty("line.separator");

	//all next strings are used by other! Change only vars content but not list
	public static final String greating = "                   Hi!" + newLine +
										"               Welcome to" + newLine +
										"          Currency Converter!" + newLine;

	public static final String quotesBelow = "  Actual currency quotes for the moment:" + newLine;
	public static final String pairsOverview = "  You can convert within next currency pairs:" + newLine;
	
	public static final String convertingRules = "To start conversion" + newLine + 
												"You have to input sum with dot as decimal devider first," + newLine + 
												"Your currency how it appears above after space next," + newLine +
												"and target currency how it appears above after space last" + newLine;
	
	public static final String userInputPrompt = "Input now, and check it, and press \"Enter\" to process:" + newLine;
	public static final String promptToNextConversion = newLine + "Input next (or \"Enter\" to exit):";
	
	public static final String ifException = "OP-S-S-S! Reading Failed! Soory, look log file." + newLine;
	public static final String incorrectEntry = "Your entry is incorrect! Input correct data, please:" + newLine;
	public static final String incorrectSum = "Your sum is incorrect. Input proper value (only digits and dot):" + newLine;
	public static final String wrongPair = "Sorry, Your request is impossible. Try another pair exactly from list above:" + newLine;
	public static final String requestToExit = "Are You really shure?" + newLine +
												"Press \"Enter\" to exit or any symbol to continue  work:" + newLine;
	public static final String goodBye = "Good luck! Welcome for the future conversion!";
}