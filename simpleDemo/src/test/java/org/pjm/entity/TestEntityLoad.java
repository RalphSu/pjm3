package org.pjm.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.hibernate.cfg.Environment;

public class TestEntityLoad extends TestCase {

	private EntityManagerFactory sf;
	private static EntityManager manager = null;

	protected void setUp() throws Exception {
		buildSessionFactory();
	}

	private void buildSessionFactory() throws Exception {

		try {
			Map<String, String> props = new HashMap<String, String>();
			props.put(Environment.USER, "pjm");
			props.put(Environment.PASS, "pjm@local!@#");
			props.put(Environment.URL, "jdbc:mysql://localhost:3306/pjm");
			props.put(Environment.HBM2DDL_AUTO, "create-drop");

			sf = Persistence.createEntityManagerFactory("pjmUnit", props);

			EntityManager manager = sf.createEntityManager();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void testLoad() {
		
	}

}