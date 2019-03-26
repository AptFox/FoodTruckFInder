package main.java.worksample.FoodTruckFinder.DTO;

import java.util.List;
import javax.ws.rs.core.GenericType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FoodTruckDTO {
	public static final GenericType<List<FoodTruckDTO>> LIST_TYPE = new GenericType<List<FoodTruckDTO>>() {};
	private String name;
	private String address;
	private Integer dayorder;
	private String openingHour;
	private String closingHour;
	
	@JsonCreator
	public FoodTruckDTO(@JsonProperty("applicant") String name,
						@JsonProperty("location") String address,
						@JsonProperty("dayorder") Integer dayorder,
						@JsonProperty("start24") String openingHour,
						@JsonProperty("end24") String closingHour) {
		this.name = name;
		this.address = address;
		this.dayorder = dayorder;
		this.openingHour = openingHour;
		this.closingHour = closingHour;
	}
	
	@JsonProperty("applicant")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("location")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonProperty("dayorder")
	public Integer getDayorder() {
		return dayorder;
	}

	public void setDayorder(Integer dayorder) {
		this.dayorder = dayorder;
	}
	
	@JsonProperty("start24")
	public String getOpeningHour() {
		return openingHour;
	}

	public void setOpeningHour(String openingHour) {
		this.openingHour = openingHour;
	}
	
	@JsonProperty("end24")
	public String getClosingHour() {
		return closingHour;
	}

	public void setClosingHour(String closingHour) {
		this.closingHour = closingHour;
	}
}
