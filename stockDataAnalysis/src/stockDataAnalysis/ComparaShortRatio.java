package stockDataAnalysis;

import java.util.Comparator;

public class ComparaShortRatio implements Comparator<StockItem> {

	@Override
	public int compare(StockItem o1, StockItem o2) {
		
		return o1.shortRatio >= o2.shortRatio ? -1:1;
	}

}
