package com.skilldistillery.honeytrails.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConditionTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Condition con;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAHoneyTrails");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		con = em.find(Condition.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		con = null;
	}

	@Test
	@DisplayName("test Condition mappings to entity")
	void test1() {
		assertNotNull(con);
		assertEquals("dry", con.getName());
		
	}
	@Test
	@DisplayName("test Condition to Hike Report mappings to entity")
	void test2() {
		assertNotNull(con);
		assertTrue(con.getHikeReports().size() > 0);
		
	}

}
