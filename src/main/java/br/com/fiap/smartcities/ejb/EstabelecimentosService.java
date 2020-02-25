package br.com.fiap.smartcities.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.fiap.smartcities.domain.Estabelecimento;

@Stateless
public class EstabelecimentosService {

	@PersistenceContext
	private EntityManager entityManager;

	public List<String> pesquisar(String termo) {
		List<String> resultado = new ArrayList<String>();

		List<Estabelecimento> estabelecimentos = this.entityManager
				.createQuery("select e from Estabelecimento e \" \r\n" + "+ \"where e.nome like :nome")
				.setParameter("nome", "%" + termo + "%").getResultList();

		for (Estabelecimento estabelecimento : estabelecimentos) {
			resultado.add(estabelecimento.getNome() + "(" + estabelecimento.getTipo().getNome() + ")");
		}
		return resultado;
	}

}
