package model;

import java.util.List;
import java.util.Objects;

public class StockHistory {

	private String name;
	List<DataRow> oneStockAlldataRows;
	private List <Integer> tardeTimeList;
	private List <Integer> tickTimeList;
	private List <Double> bidAskSpread;


	/**
	 * 
	 */
	public StockHistory(String name) {
		this(name,null,null,null);
	}
	/**
	 * @param name
	 * @param tardeTimeList
	 * @param tickTimeList
	 * @param bidAskSpread
	 */
	public StockHistory(String name, List<Integer> tardeTimeList, List<Integer> tickTimeList, List<Double> bidAskSpread) {
		this.name = name;
		this.tardeTimeList = tardeTimeList;
		this.tickTimeList = tickTimeList;
		this.bidAskSpread = bidAskSpread;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the tardeTimeList
	 */
	public List<Integer> getTardeTimeList() {
		setTardeTimeList();
		return tardeTimeList;
	}

	/**
	 * @param tardeTimeList the tardeTimeList to set
	 */
	public void setTardeTimeList() {
		int previoesTradeDate = 0;
		int previousTradeTime = 0;

		for(DataRow dataRow: oneStockAlldataRows )
		{
			if(!(dataRow.getBidPrice() > dataRow.getAskPrice()) && //bid price larger than ask price (Auction excluded)
					(dataRow.getConditionCodes().equalsIgnoreCase("XT") || // Include XT r
							dataRow.getConditionCodes().isBlank()) && // include blank string
					dataRow.getUpdate()==1 && // Trade made
					dataRow.getTime() < 684000 && // before 7 pm
					dataRow.getTime() > 28800 //after 8 am
					) 
			{

				int difference = calcTimeDiffernce(previoesTradeDate, previousTradeTime,
						dataRow.getDate(), dataRow.getTime());
				this.tardeTimeList.add( difference);							
				previoesTradeDate = dataRow.getDate();
				previousTradeTime = dataRow.getTime();

			}else {
				continue;
			}
		}
	}
	/**
	 * @return the tickTimeList
	 */
	public List<Integer> getTickTimeList() {
		return tickTimeList;
	}
	/**
	 * @param tickTimeList the tickTimeList to set
	 */
	public void setTickTimeList() {
		int previoesTickDate = oneStockAlldataRows.get(0).getDate();
		int previousTickTime = oneStockAlldataRows.get(0).getTime();
		double previousPrice = oneStockAlldataRows.get(0).getOpeningPrice();
		for(DataRow dataRow: oneStockAlldataRows )
		{
			if(dataRow.getOpeningPrice() != previousPrice && //when price changes 
					dataRow.getTime() < 684000 && // before 7 pm
					dataRow.getTime() > 28800 //after 8 am)  
					)
			{

				int difference = calcTimeDiffernce(previoesTickDate, previousTickTime,
						dataRow.getDate(), dataRow.getTime());
				this.tickTimeList.add( difference);							
				previoesTickDate = dataRow.getDate();
				previousTickTime = dataRow.getTime();
				previousPrice = dataRow.getOpeningPrice();

			}else {
				continue;
			}
		}

	}
	/**
	 * @return the bidAskSpread
	 */
	public List<Double> getBidAskSpread() {
		return bidAskSpread;
	}
	/**
	 * @param bidAskSpread the bidAskSpread to set
	 */
	public void setBidAskSpread() {

		for(DataRow dataRow: oneStockAlldataRows )
		{
			if(     (dataRow.getConditionCodes().equalsIgnoreCase("XT") || // Include XT or
					dataRow.getConditionCodes().isBlank()) && // include blank string
					dataRow.getTime() < 684000 && // before 7 pm
					dataRow.getTime() > 28800 //after 8 am
					) 
			{
				double spread = Math.abs(dataRow.getBidPrice() - dataRow.getAskPrice());

				this.bidAskSpread.add( spread);							

			}else {
				continue;
			}
		}

	}

	/**
	 * @return the oneStockAlldataRows
	 */
	public List<DataRow> getOneStockAlldataRows() {
		return oneStockAlldataRows;
	}

	private static int calcTimeDiffernce(int previoesTradeDate, int previousTradeTime, int date, int time) {
		return ((date-previoesTradeDate) * 86400) + (time-previousTradeTime) ;	
	}



	/**
	 * @param oneStockAlldataRows the oneStockAlldataRows to set
	 */
	public void setOneStockAlldataRows(List<DataRow> oneStockAlldataRows) {
		this.oneStockAlldataRows = oneStockAlldataRows;
		setTardeTimeList();
		setTickTimeList();
		setBidAskSpread();
	}
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof StockHistory)) {
			return false;
		}
		StockHistory other = (StockHistory) obj;
		return Objects.equals(name, other.name);
	}




}
