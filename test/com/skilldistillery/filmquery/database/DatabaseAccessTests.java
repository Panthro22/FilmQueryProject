package com.skilldistillery.filmquery.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

class DatabaseAccessTests {
	private DatabaseAccessor db;

	@BeforeEach
	void setUp() throws Exception {
		db = new DatabaseAccessorObject();
	}

	@AfterEach
	void tearDown() throws Exception {
		db = null;
	}

	@Test
	void test_getFilmById_with_invalid_id_returns_null() {
		Film f = db.findFilmById(-42);
		assertNull(f);
	}
	@Test
	void test_getFilmById_with_valid_id_return() {
		Film f = db.findFilmById(1);
		 assertEquals(f, db.findFilmById(1));
		
	}
	@Test
	void test_getActorById_with_invalid_id_returns_null() {
		Actor f = db.findActorById(-42);
		assertNull(f);
	}
	@Test
	void test_getActorById_with_valid_id_return() {
		Actor f = db.findActorById(1);
		assertEquals(f, db.findActorById(1));
		
	}
	@Test
	void test_getActorByFilmId_with_invalid_id_returns_null() {
		List<Actor> f = db.findActorsByFilmId(-42);
		assert(f.isEmpty());
	}
	@Test
	void test_getActorByFilmId_with_valid_id_return() {
		List<Actor> f = db.findActorsByFilmId(1);
		assertEquals(f, db.findActorsByFilmId(1));
		
	}
	@Test
	void test_getFilmsBySearchWord_with_invalid_wordSearch_returns_null() {
		List<Film> f = db.findFilmByFilmWordSearch("aint nothing like a peanut in this colletion of asdnufnqwi fqwinqw");
		assert(f.isEmpty());
	}
	@Test
	void test_getFilmsBySearchWord_with_valid_wordSearch_return() {
		List<Film> f = db.findFilmByFilmWordSearch("fri");
		assert(!f.isEmpty());
		
	}
	@Test
	void test_getActorsBySearchWord_with_invalid_wordSearch_return() {
		List<Actor> f = db.findActorsByFilmWordSearch("jdnjavniuwenv jewl iwerv oew mlsm obgnwej nsjdnvje wnornguih ando nao");
		assert(f.isEmpty());
	}
	@Test
	void test_getActorsBySearchWord_with_valid_wordSearch_return() {
		List<Actor> f = db.findActorsByFilmWordSearch("fri");
		assert(!f.isEmpty());
	}
}
