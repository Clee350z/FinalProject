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

class TrailCommentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private TrailComment tc;

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
		tc = em.find(TrailComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		tc = null;
	}

	@Test
	@DisplayName("test mapping to entity")
	void test1() {
		assertNotNull(tc);
		assertEquals("The trail was so beautiful!", tc.getCommentBody());
	}
	
	@Test
	@DisplayName("test trail comment to trail mapping")
	void test2() {
		assertNotNull(tc);
		assertEquals("Eldorado Canyon State Park Trails", tc.getTrail().getName());
	}

}
