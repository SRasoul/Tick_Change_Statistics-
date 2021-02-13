package model;

import java.util.Comparator;
import java.util.Objects;

public class DataRow {
	    private String identifier;
	    private int auctionId;
	    private double bidPrice;
	    private double askPrice;
		private double tradePrice;
		private int bidVolume;
		private int askVolume;
		private int tradeVolume;
		private int update; //type => 1=Trade; 2= Change to Bid (Px or Vol); 3=Change to Ask (Px or Vol)
		private int date;//Formated as number in csv string row e.g (20150420) 
		private int time; //in seconds past midnight
		private double openingPrice;
		private String conditionCodes;
		
				
		/**
		 * @param identifier
		 * @param auctionId
		 * @param bidPrice
		 * @param askPrice
		 * @param tradePrice
		 * @param bidVolume
		 * @param askVolume
		 * @param tradeVolume
		 * @param update
		 * @param date
		 * @param time
		 * @param openingPrice
		 * @param noConditionCode
		 * @param conditionCodes
		 */
		public DataRow(String identifier, int auctionId, double bidPrice, double askPrice, 
				double tradePrice,int bidVolume, int askVolume, int tradeVolume, byte update,
				byte date, int time, double openingPrice, String conditionCodes) {
			this.identifier = identifier;
			this.auctionId = auctionId;
			this.bidPrice = bidPrice;
			this.askPrice = askPrice;
			this.tradePrice = tradePrice;
			this.bidVolume = bidVolume;
			this.askVolume = askVolume;
			this.tradeVolume = tradeVolume;
			this.update = update;
			this.date = date;
			this.time = time;
			this.openingPrice = openingPrice;
			this.conditionCodes = conditionCodes;
		}
		/**
		 * @return the identifier
		 */
		public String getIdentifier() {
			return identifier;
		}
		/**
		 * @param identifier the identifier to set
		 */
		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}
		/**
		 * @return the auctionId
		 */
		public int getAuctionId() {
			return auctionId;
		}
		/**
		 * @param auctionId the auctionId to set
		 */
		public void setAuctionId(int auctionId) {
			this.auctionId = auctionId;
		}
		/**
		 * @return the bidPrice
		 */
		public double getBidPrice() {
			return bidPrice;
		}
		/**
		 * @param bidPrice the bidPrice to set
		 */
		public void setBidPrice(double bidPrice) {
			this.bidPrice = bidPrice;
		}
		/**
		 * @return the askPrice
		 */
		public double getAskPrice() {
			return askPrice;
		}
		/**
		 * @param askPrice the askPrice to set
		 */
		public void setAskPrice(double askPrice) {
			this.askPrice = askPrice;
		}
		/**
		 * @return the tradePrice
		 */
		public double getTradePrice() {
			return tradePrice;
		}
		/**
		 * @param tradePrice the tradePrice to set
		 */
		public void setTradePrice(double tradePrice) {
			this.tradePrice = tradePrice;
		}
		/**
		 * @return the bidVolume
		 */
		public int getBidVolume() {
			return bidVolume;
		}
		/**
		 * @param bidVolume the bidVolume to set
		 */
		public void setBidVolume(int bidVolume) {
			this.bidVolume = bidVolume;
		}
		/**
		 * @return the askVolume
		 */
		public int getAskVolume() {
			return askVolume;
		}
		/**
		 * @param askVolume the askVolume to set
		 */
		public void setAskVolume(int askVolume) {
			this.askVolume = askVolume;
		}
		/**
		 * @return the tradeVolume
		 */
		public int getTradeVolume() {
			return tradeVolume;
		}
		/**
		 * @param tradeVolume the tradeVolume to set
		 */
		public void setTradeVolume(int tradeVolume) {
			this.tradeVolume = tradeVolume;
		}
		/**
		 * @return the update
		 */
		public int getUpdate() {
			return update;
		}
		/**
		 * @param update the update to set
		 */
		public void setUpdate(byte update) {
			this.update = update;
		}

		/**
		 * @return the date
		 */
		public int getDate() {
			return date;
		}
		/**
		 * @param date the date to set
		 */
		public void setDate(byte date) {
			this.date = date;
		}
		/**
		 * @return the time
		 */
		public int getTime() {
			return time;
		}
		/**
		 * @param time the time to set
		 */
		public void setTime(int time) {
			this.time = time;
		}
		/**
		 * @return the openingPrice
		 */
		public double getOpeningPrice() {
			return openingPrice;
		}
		/**
		 * @param openingPrice the openingPrice to set
		 */
		public void setOpeningPrice(double openingPrice) {
			this.openingPrice = openingPrice;
		}


		/**
		 * @return the conditionCodes
		 */
		public String getConditionCodes() {
			return conditionCodes;
		}
		
		
		/**
		 * @param conditionCodes the conditionCodes to set
		 */
		public void setConditionCodes(String conditionCodes) {
			this.conditionCodes = conditionCodes;
		}
		
		   /*Comparator for sorting the list by stock Name*/
	    public static Comparator<DataRow> RowNameComparator = new Comparator<DataRow>() {

		public int compare(DataRow s1, DataRow s2) {
		   String row1 = s1.getIdentifier().toUpperCase();
		   String row2 = s2.getIdentifier().toUpperCase();
		   //ascending order
		   return row1.compareTo(row2);
		   //descending order
		   //return row2.compareTo(row1);
	    }};
		
		@Override
		public int hashCode() {
			return Objects.hash(auctionId, date, identifier, time);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof DataRow)) {
				return false;
			}
			DataRow other = (DataRow) obj;
			return auctionId == other.auctionId && Objects.equals(date, other.date)
					&& Objects.equals(identifier, other.identifier) && time == other.time;
		}
		@Override
		public String toString() {
			return "DataRow [identifier=" + identifier + ", auctionId=" + auctionId + ", bidPrice=" + bidPrice
					+ ", askPrice=" + askPrice + ", tradePrice=" + tradePrice + ", bidVolume=" + bidVolume
					+ ", askVolume=" + askVolume + ", tradeVolume=" + tradeVolume + ", update=" + update + ", date="
					+ date + ", time=" + time + ", openingPrice=" + openingPrice + ", conditionCodes=" + conditionCodes
					+ "]";
		}
		
		
		
		
		
		
		

}
