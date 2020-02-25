package br.com.fiap.smartcities.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.smartcities.ejb.ContadorPesquisasService;

@WebServlet(value = "/contador")
public class ContadorServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ContadorPesquisasService contadorService = 
				(ContadorPesquisasService) req.getSession().getAttribute("contadorservice");
		
		req.setAttribute("usuarios", contadorService.getUsuarios());
		req.setAttribute("pesquisas", contadorService.getPesquisas());
		
		RequestDispatcher dispacher = 
				req.getRequestDispatcher("contador.jsp");
		
		dispacher.forward(req, resp);
	}
}
