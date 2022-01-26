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

public class HikeReportTest {
	private static EntityManagerFactory emf;
		private EntityManager em;
		private HikeReport report;
	
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
			report = em.find(HikeReport.class, 1);
		}
	
		@AfterEach
		void tearDown() throws Exception {
			em.close();
			report = null;
		}
	
		
		/*
		 *  +---------------------+
			| hike_title          |
			+---------------------+
			| Slightly damp trail |
			+---------------------+
		 */
		@Test
		@DisplayName("test Hike Report mappings to entity")
		void test1() {
			assertNotNull(report);
			assertEquals("Slightly damp trail", report.getHikeTitle());
			assertEquals(2021, report.getHikedDate().getYear());
			assertEquals(12, report.getHikedDate().getMonthValue());
		}
		
//		select c.name from condition_type c JOIN hike_report h ON c.id = h.condition_type_id WHERE h.id =1;
//		+----------+
//		| name     |
//		+----------+
//		| near dry |
//		+----------+
		
		@Test
		@DisplayName("test Hike Report to Condition mappings to entity")
		void test2() {
			assertNotNull(report);
			assertEquals("near dry", report.getCondition().getName());
		}
//		
//		mysql> select t.name from trail t JOIN hike_report h ON t.id = h.trail_id WHERE h.trail_id =2;
//		+-----------------------------------+
//		| name                              |
//		+-----------------------------------+
//		| Eldorado Canyon State Park Trails |
//		+-----------------------------------+

		
		@Test
		@DisplayName("test Hike Report to Trail mappings to entity")
		void test3() {
			report = em.find(HikeReport.class, 2);
			assertNotNull(report);
			assertEquals("Eldorado Canyon State Park Trails", report.getTrails().getName());
		}
		
		
		@Test
		@DisplayName("test Hike Report to User mappings to entity")
		void test4() {
			report = em.find(HikeReport.class, 2);
			assertNotNull(report);
			assertEquals("tester", report.getUser().getUsername());
		}
		
		
		
}

