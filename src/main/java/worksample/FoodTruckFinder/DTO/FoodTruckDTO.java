package main.java.worksample.FoodTruckFinder.DTO;

import java.util.List;

import javax.ws.rs.core.GenericType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FoodTruckDTO {
	
	public static final GenericType<List<FoodTruckDTO>> LIST_TYPE = new GenericType<List<FoodTruckDTO>>() {};
	private String applicant;
	private String location;
	private Integer dayorder;
	private String start24;
	private String end24;
	
	@JsonCreator
	public FoodTruckDTO(@JsonProperty("applicant") String applicant,
						@JsonProperty("location") String location,
						@JsonProperty("dayorder") Integer dayorder,
						@JsonProperty("start24") String start24,
						@JsonProperty("end24") String end24) {
		this.applicant = applicant;
		this.location = location;
		this.dayorder = dayorder;
		this.start24 = start24;
		this.end24 = end24;
	}
	
	@JsonProperty("applicant")
	public String getApplicant() {
		return applicant;
	}


	public void setApplicant(String name) {
		this.applicant = name;
	}

	@JsonProperty("location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String address) {
		this.location = address;
	}
	
	@JsonProperty("dayorder")
	public Integer getDayorder() {
		return dayorder;
	}

	public void setDayorder(Integer dayorder) {
		this.dayorder = dayorder;
	}
	
	@JsonProperty("start24")
	public String getStart24() {
		return start24;
	}

	public void setStart24(String start24) {
		this.start24 = start24;
	}
	
	@JsonProperty("end24")
	public String getEnd24() {
		return end24;
	}

	public void setEnd24(String end24) {
		this.end24 = end24;
	}
}
