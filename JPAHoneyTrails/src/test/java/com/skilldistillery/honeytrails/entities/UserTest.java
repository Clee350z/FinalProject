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

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	@DisplayName("test mappings to entity")
	void test1() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
		assertEquals("Honeycomb", user.getFirstName());
	}
	@Test
	@DisplayName("test User to Hike Report mappings to entity")
	void test2() {
		user = em.find(User.class, 2);
		assertNotNull(user);
		assertTrue(user.getHikeReports().size() > 0);
	}
	
	@Test
	@DisplayName("test user to comment mapping")
	void test3() {
		user = em.find(User.class, 2);
		assertTrue(user.getComments().size() >= 0);
	}
	
	@Test
	@DisplayName("test user to favorite trails mapping")
	void test4() {
		user = em.find(User.class, 2);
		assertTrue(user.getFavoriteTrails().size() >= 0);
	}
	
	@Test
	@DisplayName("test user to planned trails mapping")
	void test5() {
		user = em.find(User.class, 2);
		assertTrue(user.getPlannedHikes().size() >= 0);
	}

}
