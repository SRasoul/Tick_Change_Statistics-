package model;



import java.util.Collections;
import java.util.Objects;

import control.Statistics;

public class Stock implements Statistics{

	private String name;
	private double meanTimeBetweenTrades;
	private double medianTimeBetweenTrades;
	private double meanTimeBetweenTickChanges;
	private double medianTimeBetweenTickChanges;
	private int longestTimeBetweenTrades;
	private int longestTimeBetweenTickChanges;
	private double meanBidAskSpread;
	private double medianBidAskSpread;
	private StockHistory history;
	
	
	/**
	 * 
	 */
	public Stock(String name) {
		this(name,.0,.0,.0,0,0,0,.0,.0);
	}
	
	
	/**
	 * @param name
	 * @param meanTimeBetweenTrades
	 * @param medianTimeBetweenTrades
	 * @param meanTimeBetweenTickChanges
	 * @param medianTimeBetweenTickChanges
	 * @param longestTimeBetweenTrades
	 * @param longestTimeBetweenTickChanges
	 * @param meanBidAskSpread
	 * @param medianBidAskSpread
	 */
	public Stock(String name, double meanTimeBetweenTrades, double medianTimeBetweenTrades,
			double meanTimeBetweenTickChanges, int medianTimeBetweenTickChanges, int longestTimeBetweenTrades,
			int longestTimeBetweenTickChanges, double meanBidAskSpread, double medianBidAskSpread) {
		this.name = name;
		this.meanTimeBetweenTrades = meanTimeBetweenTrades;
		this.medianTimeBetweenTrades = medianTimeBetweenTrades;
		this.meanTimeBetweenTickChanges = meanTimeBetweenTickChanges;
		this.medianTimeBetweenTickChanges = medianTimeBetweenTickChanges;
		this.longestTimeBetweenTrades = longestTimeBetweenTrades;
		this.longestTimeBetweenTickChanges = longestTimeBetweenTickChanges;
		this.meanBidAskSpread = meanBidAskSpread;
		this.medianBidAskSpread = medianBidAskSpread;
	}
	
		
	/**
	 * @return the history
	 */
	public StockHistory getHistory() {
		return history;
	}


	/**
	 * @param history the history to set
	 */
	public void setHistory(StockHistory history) {
		this.history = history;
			
		  setMeanTimeBetweenTrades();
		  setMedianTimeBetweenTrades(); 
		  setMeanTimeBetweenTickChanges(); 
		  setMedianTimeBetweenTickChanges(); 
		  setLongestTimeBetweenTrades();
		  setLongestTimeBetweenTickChanges();
		  setMeanBidAskSpread();
		  setMedianBidAskSpread();
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
	 * @return the meanTimeBetweenTrades
	 */
	public double getMeanTimeBetweenTrades() {
		return meanTimeBetweenTrades;
	}

	/**
	 * @return the medianTimeBetweenTrades
	 */
	public double getMedianTimeBetweenTrades() {
		return medianTimeBetweenTrades;
	}

	/**
	 * @return the meanTimeBetweenTickChanges
	 */
	public double getMeanTimeBetweenTickChanges() {
		return meanTimeBetweenTickChanges;
	}

	/**
	 * @return the medianTimeBetweenTickChanges
	 */
	public double getMedianTimeBetweenTickChanges() {
		return medianTimeBetweenTickChanges;
	}

	/**
	 * @return the longestTimeBetweenTrades
	 */
	public int getLongestTimeBetweenTrades() {
		return longestTimeBetweenTrades;
	}

	/**
	 * @return the longestTimeBetweenTickChanges
	 */
	public int getLongestTimeBetweenTickChanges() {
		return longestTimeBetweenTickChanges;
	}

	/**
	 * @return the meanBidAskSpread
	 */
	public double getMeanBidAskSpread() {
		return meanBidAskSpread;
	}

	/**
	 * @return the medianBidAskSpread
	 */
	public double getMedianBidAskSpread() {
		return medianBidAskSpread;
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
		if (!(obj instanceof Stock)) {
			return false;
		}
		Stock other = (Stock) obj;
		return Objects.equals(name, other.name);
	}




	@Override
	public void setMeanTimeBetweenTrades() {
		int num = history.getTardeTimeList().size();
		int total = 0;
		for (int elemnt: history.getTardeTimeList()) {
			total = total + elemnt;
		}
		if (num==0 ||  history.getTardeTimeList()==null) {
			this.meanTimeBetweenTrades = .0;
		}
		else	
		this.meanTimeBetweenTrades = (double)total/num;		
	}


	@Override
	public void setMedianTimeBetweenTrades() {

		Collections.sort(history.getTardeTimeList());
		int listSize = history.getTardeTimeList().size();
		
		if (listSize % 2 == 0)
		    this.medianTimeBetweenTrades = (history.getTardeTimeList().get(listSize/2) + 
		               history.getTardeTimeList().get((listSize/2)+1)) /2.;
		else
		    this.medianTimeBetweenTrades =  history.getTardeTimeList().get(listSize/2)/2.;		
	}


	@Override
	public void setMeanTimeBetweenTickChanges() {
		int num = history.getTickTimeList().size();
		int total = 0;
		for (int elemnt: history.getTickTimeList()) {
			total = total + elemnt;
		}
		this.meanTimeBetweenTickChanges = total/num;		
	}


	@Override
	public void setMedianTimeBetweenTickChanges() {
		Collections.sort(history.getTickTimeList());
		int listSize = history.getTickTimeList().size();
		
		if (listSize % 2 == 0)
		    this.medianTimeBetweenTickChanges = (history.getTickTimeList().get(listSize/2) + 
		               history.getTickTimeList().get((listSize/2)+1)) /2.;
		else
		    this.medianTimeBetweenTickChanges =  history.getTickTimeList().get(listSize/2)/2.;			
	}


	@Override
	public void setLongestTimeBetweenTrades() {
		
		this.longestTimeBetweenTrades = Collections.max(history.getTardeTimeList());
	}


	@Override
	public void setLongestTimeBetweenTickChanges() {
		this.longestTimeBetweenTickChanges = Collections.max(history.getTickTimeList());
	}


	@Override
	public void setMeanBidAskSpread() {
		int num = history.getBidAskSpread().size();
		double total = 0;
		for (double elemnt: history.getBidAskSpread()) {
			total = total + elemnt;
		}
		this.meanBidAskSpread = total/num;		
	}


	@Override
	public void setMedianBidAskSpread() {
		Collections.sort(history.getBidAskSpread());
		int listSize = history.getBidAskSpread().size();
		
		if (listSize % 2 == 0)
		    this.medianTimeBetweenTickChanges = (history.getBidAskSpread().get(listSize/2) + 
		               history.getBidAskSpread().get((listSize/2)+1)) /2.;
		else
		    this.medianTimeBetweenTickChanges =  history.getBidAskSpread().get(listSize/2)/2.;		
	}


	@Override
	public String toString() {
		return "name [" + meanTimeBetweenTrades + "," + medianTimeBetweenTrades + ", " 
	            + meanTimeBetweenTickChanges + ", " + medianTimeBetweenTickChanges + ", "
				+ longestTimeBetweenTrades + ", " + longestTimeBetweenTickChanges + ", " 
	            + meanBidAskSpread + "," + medianBidAskSpread + "]";
	}
	
	
}
