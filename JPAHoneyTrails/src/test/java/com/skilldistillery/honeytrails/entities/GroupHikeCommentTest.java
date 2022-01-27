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

class GroupHikeCommentTest {


	private static EntityManagerFactory emf;
	private EntityManager em;
	private GroupHikeComment ghc;

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
		ghc = em.find(GroupHikeComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		ghc = null;
	}
	
	@Test
	@DisplayName("test basic mappings to entity")
	void test1() {
		assertNotNull(ghc);
		assertEquals(2021, ghc.getCreateDate().getYear());
	}
	
	@Test
	@DisplayName("test mapping group hike comment to user")
	void test2() {
		assertNotNull(ghc);
		assertEquals("tester", ghc.getUserId().getUsername());
	}
	
	@Test
	@DisplayName("test mapping group hike comment to hike report")
	void test3() {
		assertNotNull(ghc);
		assertEquals("Slightly damp trail", ghc.getHikeReport().getHikeTitle());
	}

}
