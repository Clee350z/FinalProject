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

class TrailTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Trail trail;

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
		trail = em.find(Trail.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		trail = null;
	}

	@Test
	@DisplayName("test mappings to entity")
	void test1() {
		assertNotNull(trail);
		assertEquals("Bear Peak Summit Hike", trail.getName());
		assertEquals("Colorado", trail.getLocation());
		assertEquals(8.40, trail.getLengthMiles());
	}
	
	@Test
	@DisplayName("test trail to difficulty mapping")
	void test2() {
		assertNotNull(trail);
		assertEquals("Strenuous", trail.getDifficulty().getName());
	}
	@Test
	@DisplayName("test Trail to Hike Report mapping")
	void test3() {
		trail = em.find(Trail.class, 2);
		assertNotNull(trail);
		assertTrue(trail.getHikeReports().size() > 0);
		assertEquals(1, trail.getHikeReports().size());
	}
	
	@Test
	@DisplayName("test tail to users favorite")
	void test4() {
		assertNotNull(trail);
		assertTrue(trail.getUsersFavorite().size() >= 0);
	}
	
	@Test
	@DisplayName("test tail to users planned")
	void test5() {
		assertNotNull(trail);
		assertTrue(trail.getUsersPlanned().size() >= 0);
	}

}
