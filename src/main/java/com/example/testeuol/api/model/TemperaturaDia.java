package com.example.testeuol.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TemperaturaDia {
	
	@Column(name = "temperatura_minima_dia")
	private String temperaturaMinima;
	
	@Column(name = "temperatura_maxima_dia")
	private String temperaturaMaxima;

	public String getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(String temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

	public String getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(String temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}
}