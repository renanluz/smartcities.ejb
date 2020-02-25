package br.com.fiap.smartcities.ejb;

import javax.ejb.Singleton;

@Singleton
public class ContadorPesquisasService {
	
	private int pesquisas;
	
	private int usuarios;
	
	public void novoUsuario() {
		usuarios++;
	}
	
	public void usuarioSaiu() {
		usuarios--;
	}
	
	public void novaPesquisa() {
		pesquisas++;
	}

	public int getPesquisas() {
		return pesquisas;
	}

	public int getUsuarios() {
		return usuarios;
	}

}
