package com.example.testeuol.api.service;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.example.testeuol.api.model.InformacaoGeografica;
import com.example.testeuol.api.model.TemperaturaDia;
import com.example.testeuol.api.model.TempoLocalizacao;

@Service
public class TemperaturaDiaService {

	private final String API_CLIMA = "https://www.metaweather.com/api/location/";
	private final String BUSCA_LAT_LONG = API_CLIMA + "/search/?lattlong=";
	private final String API_IP_VIGILANTE = "https://ipvigilante.com/";
	
	@Autowired
	private RestOperations restTemplate;

	public InformacaoGeografica getInformacaoGeograficaByIp(String ip) {
		InformacaoGeografica InformacaoGeografica = restTemplate.getForObject(API_IP_VIGILANTE + ip, InformacaoGeografica.class);
		return InformacaoGeografica;
	}
	
	public TempoLocalizacao getTempoLocalizacaoLongitudeLatitude(String longitude, String latitude) {
		ResponseEntity<List<TempoLocalizacao>> response = restTemplate.exchange(BUSCA_LAT_LONG + latitude + "," + longitude, HttpMethod.GET, null,new ParameterizedTypeReference<List<TempoLocalizacao>>(){});
		List<TempoLocalizacao> tempoLocalizacaoList = response.getBody();
		TempoLocalizacao tempoLocalizacao = getTempoLocalizacao(tempoLocalizacaoList);
		return tempoLocalizacao;
	
	}
	
	public TemperaturaDia getTemperaturaDiaPorLocalizacao(TempoLocalizacao tempoLocalizacao) {
		TemperaturaDia temperaturaDia = new TemperaturaDia();
		temperaturaDia.setTemperaturaMaxima(getTemperaturaMaxima(tempoLocalizacao));
		temperaturaDia.setTemperaturaMinima(getTemperaturaMinima(tempoLocalizacao));
		return temperaturaDia;
	}
	
	private TempoLocalizacao getTempoLocalizacao(List<TempoLocalizacao> tempoLocalizacaoList) {
		TempoLocalizacao tempoLocalizacao = tempoLocalizacaoList.get(0);
		int distanciaAnterior = tempoLocalizacao.getDistance();

		for (TempoLocalizacao t : tempoLocalizacaoList) {
			if (isMenorDistancia(distanciaAnterior, t.getDistance())) {
				distanciaAnterior = t.getDistance();
				tempoLocalizacao = t;
			}
		}
		return tempoLocalizacao;
	}
	
	
	private boolean isMenorDistancia(int distanciaAnterior, int distanciaAtual) {
		return distanciaAtual < distanciaAnterior ? true : false;
	}
	
	public String getTemperaturaMaxima(TempoLocalizacao tempoLocalizacao) throws JSONException {
		String response = restTemplate.getForObject(API_CLIMA + tempoLocalizacao.getWoeid(), String.class);
		return getTemperatura("max_temp", response);
	}
	
	public String getTemperaturaMinima(TempoLocalizacao tempoLocalizacao) throws JSONException {
		String response = restTemplate.getForObject(API_CLIMA + tempoLocalizacao.getWoeid(), String.class);
		return getTemperatura("min_temp", response);
	}

	private String getTemperatura(String tipoTemperatura, String responseClima) {
		String responseClimaSplit[] = responseClima.split(",");
		String temperatura = "";
		for (int i = 0; i < responseClimaSplit.length; i++) {
			if (responseClimaSplit[i].contains(tipoTemperatura) && temperatura.isEmpty()) {
				String temperaturaV[] = responseClimaSplit[i].split(":|\\.");
				temperatura = temperaturaV[1];
			}
		}
		
		return temperatura;
	}
}
