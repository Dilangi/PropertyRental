package list;

import java.util.Formatter;

public class FormatHelper {
	
	public static String twoDecimalString(Double d) {
	 try (Formatter formatter = new Formatter()) {
		return formatter.format("%.2f", d).toString();
	 	}
	 }
}
