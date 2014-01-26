package com.algaworks.blog.dbunit.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.blog.dbunit.dao.helper.DbUnitHelper;
import com.algaworks.blog.dbunit.model.Carro;

public class CarroDAOTest {

	private static DbUnitHelper dbUnitHelper;
	private static EntityManagerFactory factory;
	
	private EntityManager manager;
	private CarroDAO carroDAO;

	@BeforeClass
	public static void initClass() {
		dbUnitHelper = new DbUnitHelper("DbUnitXml");
		factory = Persistence.createEntityManagerFactory("artigoTesteIntegracaoDbunitPU");
	}

	@Before
	public void init() {
		dbUnitHelper.execute(DatabaseOperation.DELETE_ALL, "Carro.xml");

		dbUnitHelper.execute(DatabaseOperation.INSERT, "Carro.xml");

		manager = factory.createEntityManager();
		this.carroDAO = new CarroDAO(manager);
	}
	
	@After
	public void end() {
		this.manager.close();
	}

	@Test
	public void deveRetornarCarrosZeroKm() {
		List<Carro> resultado = carroDAO.buscarCarrosZero();
		
		assertThat(resultado, hasItems(new Carro(1L), new Carro(4L)));
	}

	@Test
	public void deveRetornarCarrosMenosDoisAnosUso() {
		Integer doisAnosAntes = Calendar.getInstance().get(Calendar.YEAR) - 2;
		List<Carro> resultado = carroDAO.buscarCarrosComIdadeInferiorA(doisAnosAntes);
		
		assertThat(resultado, hasItems(new Carro(1L), new Carro(2L), new Carro(3L), new Carro(4L)));
	}

}
