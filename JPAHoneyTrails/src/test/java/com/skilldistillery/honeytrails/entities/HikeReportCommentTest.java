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

class HikeReportCommentTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private HikeReportComment report;

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
		report = em.find(HikeReportComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		report = null;
	}

	
	@Test
	@DisplayName("test Hike Report Comment mappings to entity")
	void test1() {
		assertNotNull(report);
		assertEquals(2021, report.getCreateDate().getYear());
	}
	
	
	@Test
	@DisplayName("test Hike Report Comment to Hike Report mappings to entity")
	void test2() {
		assertNotNull(report);
		assertEquals("Slightly damp trail", report.getHikeReportId().getHikeTitle());
		
	}
	
	@Test
	@DisplayName("test Hike Report Comment to User mappings to entity")
	void test3() {
		assertNotNull(report);
		assertEquals("tester", report.getUserId().getUsername());
	}
	


}
