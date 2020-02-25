package br.com.fiap.smartcities.ejb;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class HistoricoPesquisasService {
	
	@EJB
	private ContadorPesquisasService contadorService;
	
	private Set<String> historico = new LinkedHashSet<String>();
	
	public void registrarPesquisa(String termo) {
		this.historico.add(termo);
		this.contadorService.novaPesquisa();
	}

	public Set<String> getHistorico() {
		return historico;
	}
	
}
