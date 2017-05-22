
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class CurrencyConverter {
	// The next arr filling I'd prefer to be replaced with keyboard entry or load from DB (site)
	private static int countOfCurrencyPairs = 7;
	private static String[] currenciesPairs = {"USD/JPY", "EUR/USD", "GBP/USD", "AUD/USD", "USD/CHF", "EUR/JPY", "EUR/GBP"};
	private static float[] bids = {125.42f, 1.2544f, 14.2842f, 16.2042f, 1.3750f, 15.2942f, 0.6790f};
	private static float[] asks = {125.43f, 1.2545f, 14.2843f, 16.8543f, 1.3754f, 15.8543f, 0.6794f};

	public static void main(String[] args) throws IOException {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userInput;

		
		Greeting();
//a		fillArrays();
		showCurrenciesPairs();
		showAvailableConversions();
		howToConvertExplanation();
//b		promptToInputData();
		try {			
			while(true){
			userInput = reader.readLine();
			if(!userInput.equals("")){
				convert(userInput);
				System.out.println("Input next or press \"Enter\" to finish converting:");

			}
			else{
				System.out.println("Are You really shure?");
				System.out.println("Press \"Enter\" to exit or any symbol to continue  work:");
				userInput = reader.readLine();
				if(userInput.equals("")){
					System.out.println("Good luck! Welcome for the future conversion!");
					break;
				}
				else continue;
			}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			reader.close();
		}
	}
	
	// Preparing to program start: fill Arrays with data to operate - not apposite here
/*a	private static void fillArrays(){
		int count = 7;
		currenciesPairs = new String[count];
		bids = new float[count];
		asks = new float[count];
		
		currenciesPairs[0] = ... // input from keyboard for example
	}
*/
	// Greeting at program start	1
	private static void Greeting(){
		System.out.println("                   Hi!");
		System.out.println("               Welcome to");
		System.out.println("          Currency Converter!");
		System.out.println(" ");
	}

	// Show Currencies pairs	2
	private static void showCurrenciesPairs(){
		for(int i = 0; i < countOfCurrencyPairs; i++){
		System.out.println((i+1) + ". " + currenciesPairs[i] + ", bid = " + bids[i] + ", ask = " + asks[i]);
		}
		System.out.println(" ");
	}
	
	// Show what currencies available to conversion	3
	private static void showAvailableConversions(){
		System.out.println("  You can convert within next currency pairs:");
		for(int i = 0; i < countOfCurrencyPairs; i++){
			String[] splitted = currenciesPairs[i].split("/");
			System.out.print("  " + splitted[0] + "-" + splitted[1]);
			}
		System.out.println("");
		System.out.println("");
	}
	
	// explanation how to input and prompt to input	4
	private static void howToConvertExplanation(){
		System.out.println("To start conversion You have to input sum with dot as decimal devider first,");
		System.out.println("Your currency how it appears above after space next,");
		System.out.println("and target currency how it appears above after space last");
		System.out.println("Input now, and check it, and press \"Enter\" to process:");
		System.out.println(" ");
	}
/*b	private static void promptToInputData(){
		System.out.println("              Let's start! Press \"Enter\":");
		System.out.println(" ");
	}
*/
	
	private static void convert(String request){
		float targetSum = 0f;
		// extract sum and source&target currencies
		String[] userStringSplitted = request.split(" ");
		String userSumString = userStringSplitted[0];
		String userCurrency = userStringSplitted[1];
		String targetCurrency = userStringSplitted[2];
		
		if(!isNumberCorrect(userSumString)){
			System.out.println("Your sum is incorrect. Input proper value (only digits and dot):");
		} else {
			Float userSum = Float.parseFloat(userSumString);
			for(int i = 0; i < countOfCurrencyPairs; i++){
				String[] splitted = currenciesPairs[i].split("/");
				if(splitted[0].equals(userCurrency)&&splitted[1].equals(targetCurrency)){
					targetSum = userSum*bids[i];
					break;
				}
				if(splitted[0].equals(targetCurrency)&&splitted[1].equals(userCurrency)){
					targetSum = userSum/asks[i];
					break;
				}
			}
		
			if(targetSum==0f){
				System.out.println("Sorry, Your request is impossible. Try another pair exactly from list above:");
			} else{
			System.out.println(" ");
			System.out.println(userSumString + " " + userCurrency + "   ->   " + String.format("%(.2f", targetSum) + " " + targetCurrency);
			//                 ^String.format("%(.2f", userSum)
			System.out.println(" ");
			}
			targetSum = 0f;
		}
	}
	
	private static boolean isNumberCorrect(String numberString){
		boolean is = false;
		if(numberString.matches("\\d+\\.\\d+")||numberString.matches("\\d+")){
			is = true;
		}
		return is;
	}
}
