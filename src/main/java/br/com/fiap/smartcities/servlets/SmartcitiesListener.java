package br.com.fiap.smartcities.servlets;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;

import br.com.fiap.smartcities.ejb.ContadorPesquisasService;

@WebListener
public class SmartcitiesListener implements HttpSessionIdListener {

	public void sessionCreated(HttpSessionEvent se) {
		try {
			InitialContext ic = new InitialContext();

			se.getSession().setAttribute("estabelecimentosService", ic.lookup("java:module/EstabelecimentosService"));

			se.getSession().setAttribute("historicoService", ic.lookup("java:module/HistoricoPesquisasService"));

			ContadorPesquisasService contadorService = (ContadorPesquisasService) ic
					.lookup("java:module/ContadorPesquisasService");

			contadorService.novoUsuario();

			se.getSession().setAttribute("contadorService", contadorService);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		ContadorPesquisasService contadorService = (ContadorPesquisasService) se.getSession()
				.getAttribute("contadorService");
		
		contadorService.usuarioSaiu();
	}

	@Override
	public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
		// TODO Auto-generated method stub
		
	}

}
