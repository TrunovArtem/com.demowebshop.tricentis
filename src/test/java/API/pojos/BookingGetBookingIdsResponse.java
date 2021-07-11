package API.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingGetBookingIdsResponse{

	@JsonProperty("bookingid")
	private int bookingid;

	public int getBookingid(){
		return bookingid;
	}
}