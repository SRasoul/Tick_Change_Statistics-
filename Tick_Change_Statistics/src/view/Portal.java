package view;

import control.Analysis;
import model.Stock;

public final class Portal  {
	

	//Test
	public static void main(String[] args) {
																							
		
		Analysis an = new Analysis("../filename");
		for(Stock stock: an.getStocks()) {
			System.out.println(stock.toString());
		}

	}

   



}
