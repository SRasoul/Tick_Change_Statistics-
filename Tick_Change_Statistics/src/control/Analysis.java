package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.DataRow;
import model.Stock;
import model.StockHistory;

public class Analysis implements CsvDao{


	private String ulr;
	List<Stock> stocks = new ArrayList<>();

	/**
	 * @param ulr
	 */
	public Analysis(String ulr) {
		this.ulr = ulr;
		setStocks();
	}

	/**
	 * @return the stocks
	 */
	public List<Stock> getStocks() {
		return stocks;
	}

	/**
	 * @param stocks the stocks to set
	 */
	public void setStocks() {
		scanAllstOcks(getDataList());
	}




	@Override
	public List<DataRow> getDataList() {
		List<DataRow> dataRows = new ArrayList<>(); 
		Path pathToFile = Paths.get(this.ulr); 
		// create an instance of BufferedReader 
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			// read the first line from the text file 
			String line = br.readLine(); 
			// loop until all lines are read 
			while (line != null) { 
			// use string.split to load a string array with the values from 
			// each line of the file, using a comma as the delimiter 
				String[] attributes = line.split(","); 
				DataRow dataRow = createDataRow(attributes); 
				// adding DataRow into ArrayList 
				dataRows.add(dataRow); 
				// read next line before looping 
				// if end of file reached, line would be null 
				line = br.readLine();
				} 
			} catch (IOException ioe)  { 
				ioe.printStackTrace(); 
				} 
		
		 return dataRows;
	}



	@Override
	public void scanAllstOcks(List<DataRow> dataRows) {
		Collections.sort(dataRows, DataRow.RowNameComparator);;
	//Read history for individual stocks put them in list
		StockHistory sH;
		List<DataRow> oneStockAlldataRows = new ArrayList<>();
		Stock stock;
		
		for(int i=0; i< dataRows.size()-1; i++) {
			if(dataRows.get(i).getIdentifier().equalsIgnoreCase(dataRows.get(i+1).getIdentifier())) {
				
				oneStockAlldataRows.add(dataRows.get(i));
			}
			else {
				oneStockAlldataRows.add(dataRows.get(i));
				sH = new StockHistory(dataRows.get(i).getIdentifier());
				sH.setOneStockAlldataRows(oneStockAlldataRows);
				stock = new Stock(dataRows.get(i).getIdentifier());
				stock.setHistory(sH);				
				oneStockAlldataRows = new ArrayList<>();
			}
		}
	}


	private static DataRow createDataRow(String[] metadata) { 

		String identifier = metadata[0] ;
		int auctionId = Integer.parseInt( metadata[1]);
		double bidPrice = Double.parseDouble( metadata[2]);
		double askPrice = Double.parseDouble(metadata[3]);
		double tradePrice = Double.parseDouble(metadata[4]);
		int bidVolume = Integer.parseInt(metadata[5]);
		int askVolume = Integer.parseInt(metadata[6]);
		int tradeVolume = Integer.parseInt(metadata[7]);
		byte update = Byte.parseByte( metadata[8]);
		byte date = formatDate(metadata[10]);
		int time = (metadata[11]!=null ||metadata[11]!="")? 
				(int) Math.round((Double.parseDouble(metadata[11].trim()))): 0;

				double openingPrice = Double.parseDouble(metadata[12]);
				String conditionCodes = metadata[14];

				return new DataRow(identifier, auctionId, bidPrice, askPrice, 
						tradePrice,bidVolume, askVolume, tradeVolume, update,
						date, time, openingPrice, conditionCodes) ;
	}


	private static byte formatDate(String metadata) {	
		String subStr = metadata.substring(metadata.length() - 2);	
		return Byte.parseByte(subStr);

	}



}






