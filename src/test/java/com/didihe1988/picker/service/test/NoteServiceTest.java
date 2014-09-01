package com.didihe1988.picker.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.didihe1988.picker.common.Status;
import com.didihe1988.picker.model.Note;
import com.didihe1988.picker.service.NoteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:servlet-context.xml",
		"classpath:root-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NoteServiceTest {
	@Autowired
	private NoteService noteService;

	@Test
	public void test01() {
		Note note = new Note(1, 3, "what is the matter", "lalala");
		int status = noteService.addNote(note);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * test isPublic在数据库中存储
	 */
	@Test
	public void test02() {
		Note note = new Note(1, 2, "lalala23", "dota3r43", false);
		int status = noteService.addNote(note);
		assertSame(Status.SUCCESS, status);
	}

	/*
	 * test isPublic从数据库查询来是否改变
	 */
	@Test
	public void test03() {
		Note note = noteService.getNoteById(1);
		assertSame(false, note.isPublic());
		note = noteService.getNoteById(2);
		assertSame(true, note.isPublic());
	}

	@Test
	public void test04() {
		List<Note> noteList = noteService.getPublicNoteListByBookId(1);
		System.out.println(noteList);
		noteList = noteService.getALLNoteListByBookId(1);
		System.out.println(noteList);
	}
}
