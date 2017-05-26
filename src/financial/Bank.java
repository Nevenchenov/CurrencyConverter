package financial;

public class Bank {
	private static CurrencyPair[] quotes;

	// !!!! it is a singleton now to do not form quotes every getting. 
	// But if CurrencySet is changed, quotes is not synchronized...
	// !!!! When CurrencySet will dynamic the singleton should be destroyed
	public static CurrencyPair[] getQuotes(){
		if(quotes == null){
			quotes = new CurrencyPair[CurrencySet.countOfCurrencyPairs];
			for(int i = 0; i < CurrencySet.countOfCurrencyPairs; i++){
				//extract currency pair data from table given:
				Currency firstCurrency = new Currency((CurrencySet.currenciesPairs[i].split("/"))[0]);
				Currency secondCurrency = new Currency((CurrencySet.currenciesPairs[i].split("/"))[1]);
				Float bid = CurrencySet.bids[i];
				Float ask = CurrencySet.asks[i];

				//create CurrencyPair object:
				CurrencyPair pair = new CurrencyPair(firstCurrency, secondCurrency, bid, ask);
				
				//add pair created to "quotes" array
				quotes[i] = pair;
			}
		}
		return quotes;
	}
	
}
