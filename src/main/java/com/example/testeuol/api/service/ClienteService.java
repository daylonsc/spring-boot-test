package com.example.testeuol.api.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.testeuol.api.model.Cliente;
import com.example.testeuol.api.model.InformacaoGeografica;
import com.example.testeuol.api.model.TemperaturaDia;
import com.example.testeuol.api.model.TempoLocalizacao;
import com.example.testeuol.api.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private HttpServletRequest request;
	
	@Autowired
	private TemperaturaDiaService temperaturaDiaService;
	
	public Cliente criar(Cliente cliente) {
		InformacaoGeografica informacaoGeografica = temperaturaDiaService.getInformacaoGeograficaByIp(request.getRemoteAddr());
		TempoLocalizacao tempoLocalizacao = temperaturaDiaService.getTempoLocalizacaoLongitudeLatitude(informacaoGeografica.getData().getLongitude(), informacaoGeografica.getData().getLatitude());
		TemperaturaDia temperaturaDia = temperaturaDiaService.getTemperaturaDiaPorLocalizacao(tempoLocalizacao);
		
		cliente.setTemperaturaDia(temperaturaDia);
		
		Cliente clienteSalvo = clienteRepository.save(cliente);
		return clienteSalvo;
	}
	
	public Cliente atualizar(Long id, Cliente cliente) {
		Cliente clienteSalvo = clienteRepository.findById(id).orElse(null);
		
		if( clienteSalvo == null ) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		return clienteRepository.save(clienteSalvo);
	}
}
