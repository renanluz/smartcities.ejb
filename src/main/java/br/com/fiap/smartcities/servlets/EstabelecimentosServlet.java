package br.com.fiap.smartcities.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.smartcities.ejb.EstabelecimentosService;
import br.com.fiap.smartcities.ejb.HistoricoPesquisasService;

@WebServlet(value = "/estabelecimentos")
public class EstabelecimentosServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EstabelecimentosService estabelecimentosService = (EstabelecimentosService) req.getSession()
				.getAttribute("estabelecimentosService");

		HistoricoPesquisasService historicoService = (HistoricoPesquisasService) req.getSession()
				.getAttribute("historicoService");

		String termo = req.getParameter("termo");

		this.pesquisar(termo, req, estabelecimentosService);
		this.registrarHistorico(termo, req, historicoService);

		RequestDispatcher dispacher = req.getRequestDispatcher("estabelecimentos.jsp");
		dispacher.forward(req, resp);
	}

	private void pesquisar(String termo, HttpServletRequest req, EstabelecimentosService estabelecimentosService) {
		List<String> resultadoPesquisa = estabelecimentosService.pesquisar(termo);

		StringBuilder sbResultado = new StringBuilder();
		for (String resultado : resultadoPesquisa) {
			sbResultado.append("<li>" + resultado + "</li>");
		}

		req.setAttribute("termo", termo);
		req.setAttribute("encontrados", resultadoPesquisa.size());
		req.setAttribute("resultado", sbResultado.toString());
	}

	private void registrarHistorico(String termo, HttpServletRequest req, HistoricoPesquisasService historicoService) {
		historicoService.registrarPesquisa(termo);

		Set<String> pesquisas = historicoService.getHistorico();

		StringBuilder sbHistorico = new StringBuilder();
		for (String pesquisa : pesquisas) {
			sbHistorico.append("<li><i>" + pesquisa + "</i></li>");
		}

		req.setAttribute("historico", sbHistorico);
	}
}
