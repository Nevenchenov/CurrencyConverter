import java.util.Locale;

import pr.GoodBye;
import pr.Greeting;
import service.Operator;

public class CurrencyConverter {

	public static void main(String[] args){
        Locale locale = new Locale("en");
        Locale.setDefault(locale);

        
		
		Greeting.greet();
		
        Operator operator = new Operator();
		operator.run();
		operator.finish();
		
		GoodBye.bye();
	}
}