package com.algaworks.blog.dbunit.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import com.algaworks.blog.dbunit.model.Carro;

public class CarroDAO  {

	private EntityManager manager;

	public CarroDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Carro> buscarCarrosZero() {
		Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);

		return manager.createQuery("from Carro c where c.anoFabricacao = :ano")
					.setParameter("ano", anoAtual)
					.getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Carro> buscarCarrosComIdadeInferiorA(Integer ano) {
		return manager.createQuery("from Carro c where c.anoFabricacao >= :ano")
					.setParameter("ano", ano)
					.getResultList();		
	}
	
}
