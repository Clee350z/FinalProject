package com.skilldistillery.honeytrails.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DifficultyTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Difficulty dif;

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
		dif = em.find(Difficulty.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		dif = null;
	}

	@Test
	@DisplayName("test mapping to entity")
	void test1() {
		assertNotNull(dif);
		assertEquals("Easy", dif.getName());
	}
	
	@Test
	@DisplayName("test difficulty to trail mapping")
	void test2() {
		assertNotNull(dif);
		assertTrue(dif.getTrails().size() >= 0);
	}

}
