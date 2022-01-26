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

class HikePhotoTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private HikePhoto photo;

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
		photo = em.find(HikePhoto.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		photo = null;
	}
	
//	+-----------------+
//	| title           |
//	+-----------------+
//	| Small Waterfall |
//	+-----------------+

	@Test
	@DisplayName("test Condition mappings to entity")
	void test1() {
		assertNotNull(photo);
		assertEquals("Small Waterfall", photo.getTitle());
		
	}

}
