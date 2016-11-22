package pl.poblock.connection.decode.ryan.model;

public class Fare {
	private String type;
	private String amount;
	private String count;
	private String hasDiscount;
	private String publishedFare;
	private String discountInPercent;
	private String hasPromoDiscount;
	public Fare(String type, String amount, String count, String hasDiscount, String publishedFare,
			String discountInPercent, String hasPromoDiscount) {
		this.type = type;
		this.amount = amount;
		this.count = count;
		this.hasDiscount = hasDiscount;
		this.publishedFare = publishedFare;
		this.discountInPercent = discountInPercent;
		this.hasPromoDiscount = hasPromoDiscount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getHasDiscount() {
		return hasDiscount;
	}
	public void setHasDiscount(String hasDiscount) {
		this.hasDiscount = hasDiscount;
	}
	public String getPublishedFare() {
		return publishedFare;
	}
	public void setPublishedFare(String publishedFare) {
		this.publishedFare = publishedFare;
	}
	public String getDiscountInPercent() {
		return discountInPercent;
	}
	public void setDiscountInPercent(String discountInPercent) {
		this.discountInPercent = discountInPercent;
	}
	public String getHasPromoDiscount() {
		return hasPromoDiscount;
	}
	public void setHasPromoDiscount(String hasPromoDiscount) {
		this.hasPromoDiscount = hasPromoDiscount;
	}
//	@Override
//	public String toString() {
//		return "Fare [type=" + type + ", amount=" + amount + ", count=" + count + ", hasDiscount=" + hasDiscount
//				+ ", publishedFare=" + publishedFare + ", discountInPercent=" + discountInPercent
//				+ ", hasPromoDiscount=" + hasPromoDiscount + "]";
//	}
	@Override
	public String toString() {
		return amount;
	}
}
