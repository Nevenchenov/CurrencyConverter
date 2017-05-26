package financial;

public class CurrencyPair {
	private Currency firstCurrency;
	private Currency secondCurrency;
	private Float bid;
	private Float ask;
	
	public CurrencyPair(Currency firstCurrency, Currency secondCurrency, Float bid, Float ask) {
		this.firstCurrency = firstCurrency;
		this.secondCurrency = secondCurrency;
		this.bid = bid;
		this.ask = ask;
	}
	
	public Currency getFirstCurrency(){
		return firstCurrency;
	}
	
	public Currency getSecondCurrency(){
		return secondCurrency;
	}
	
	public Float getBid(){
		return bid;
	}
	
	public Float getAsk(){
		return ask;
	}
	
	// the next are setters in case of dynamically updated CurrencySet
	
	public void setBid(Float newBid){
		bid = newBid;
	}
	public void setAsk(Float newAsk){
		ask = newAsk;
	}
}
