package API.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking{

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("additionalneeds")
	private String additionalneeds;

	@JsonProperty("bookingdates")
	private BookingDates bookingdates;

	@JsonProperty("totalprice")
	private int totalprice;

	@JsonProperty("depositpaid")
	private boolean depositpaid;

	@JsonProperty("lastname")
	private String lastname;

	public void setFirstName(String firstname){
		this.firstname = firstname;
	}

	public String getFirsName(){
		return firstname;
	}

	public void setAdditionalNeeds(String additionalneeds){
		this.additionalneeds = additionalneeds;
	}

	public String getAdditionalNeeds(){
		return additionalneeds;
	}

	public void setBookingDates(BookingDates bookingdates){
		this.bookingdates = bookingdates;
	}

	public BookingDates getBookingDates(){
		return bookingdates;
	}

	public void setTotalprice(int totalprice){
		this.totalprice = totalprice;
	}

	public int getTotalprice(){
		return totalprice;
	}

	public void setDepositpaid(boolean depositpaid){
		this.depositpaid = depositpaid;
	}

	public boolean isDepositpaid(){
		return depositpaid;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}
}