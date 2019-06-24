package com.example.testeuol.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TempoLocalizacao {
	private Integer woeid;
	private Integer distance;
	
	public Integer getWoeid() {
		return woeid;
	}
	public void setWoeid(Integer woeid) {
		this.woeid = woeid;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		return "TempoLocalizacao {woeid=" + woeid + ", distance=" + distance + "}";
	}
}
