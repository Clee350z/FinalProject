package com.skilldistillery.honeytrails.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GroupHikeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private GroupHike gh;

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
		gh = em.find(GroupHike.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		gh = null;
	}

	@Test
	@DisplayName("test mappings to entity")
	void test1() {
		assertNotNull(gh);
		assertEquals("Trail Fun Time", gh.getEventName());
	}
	
	@Test
	@DisplayName("test group hike to the user who created it")
	void test2() {
		assertNotNull(gh);
		assertEquals("tester", gh.getCreatedByUser().getUsername());
	}

}
