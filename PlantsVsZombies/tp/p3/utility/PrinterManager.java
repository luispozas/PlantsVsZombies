package tp.p3.utility;

import tp.p3.printer.*;

public class PrinterManager {
	
	private static BoardPrinter[] availablePrinter = { 
			new DebugPrinter(),
			new ReleasePrinter()
	};

	public static BoardPrinter parseBoardPrinter(String words) { 
		BoardPrinter comP = null;
		for(BoardPrinter newComP : availablePrinter) {
			comP = newComP.parse(words);
			if(comP != null) return comP;
		}
		return null;
	}

}
