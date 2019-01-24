package ru.eremin.noteboard.data;

import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.eremin.noteboard.Order;
import ru.eremin.noteboard.OrderedRunner;
import ru.eremin.noteboard.api.TestData;
import ru.eremin.noteboard.config.AppConfiguration;
import ru.eremin.noteboard.dto.*;
import ru.eremin.noteboard.entity.*;
import ru.eremin.noteboard.service.*;
import ru.eremin.noteboard.service.api.*;

import java.util.*;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @autor Artem Eremin on 20.12.2018.
 */


@RunWith(OrderedRunner.class)
public class NoteServiceTest {
    private static ApplicationContext context;
    private static INoteService noteService;
    private static IUserService userService;
    private static IBoardService boardService;
    private static ICategoryService categoryService;
    private static INoteDataService noteDataService;
    private static INoteDeadlineService noteDeadlineService;
    private static ICommentService commentService;
    private static INotePictureService pictureService;
    private static UserDTO userDTO;
    private static BoardDTO board;
    private static NoteDTO noteDTO;
    private static NoteDataDTO noteDataDTO;
    private static CategoryDTO categoryDTO;
    private static NoteDeadlineDTO noteDeadline;
    private static NotePictureDTO notePictureDTO;


    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        noteService = context.getBean(NoteService.NAME, INoteService.class);
        userService = context.getBean(UserService.NAME, IUserService.class);
        boardService = context.getBean(BoardService.NAME, IBoardService.class);
        categoryService = context.getBean(CategoryService.NAME, ICategoryService.class);
        noteDataService = context.getBean(NoteDataService.NAME, INoteDataService.class);
        noteDeadlineService = context.getBean(NoteDeadlineService.NAME, INoteDeadlineService.class);
        commentService = context.getBean(CommentService.NAME, ICommentService.class);
        pictureService = context.getBean(NotePictureService.NAME, INotePictureService.class);

        userDTO = new UserDTO();
        board = new BoardDTO();
        categoryDTO = new CategoryDTO();
        noteDTO = new NoteDTO();
        noteDataDTO = new NoteDataDTO();
        noteDeadline = new NoteDeadlineDTO();


        board.setId(UUID.randomUUID().toString());
        board.setDate(new Calendar.Builder().setInstant(new Date()).build());
        board.setName("testBoard");

        categoryDTO.setId(UUID.randomUUID().toString());
        categoryDTO.setName("testCategory");
        categoryDTO.setBoardId(board.getId());

        userDTO.setId(UUID.randomUUID().toString());
        userDTO.setLogin("testUser");
        userDTO.setHashPassword("testpass");
        userDTO.setEmail("@test");


        noteDTO.setId(UUID.randomUUID().toString());
        noteDTO.setBoardId(board.getId());
        noteDTO.setCategotyId(categoryDTO.getId());
        noteDTO.setStatus(NoteStatus.PROCEED);
        noteDTO.setAuthorId(userDTO.getId());

        noteDeadline.setId(UUID.randomUUID().toString());
        noteDeadline.setDeadlineDate(
                new GregorianCalendar(2019,0,10));
        noteDeadline.setNoteId(noteDTO.getId());

        noteDTO.setNoteDeadlineId(noteDeadline.getId());

        noteDataDTO.setId(UUID.randomUUID().toString());
        noteDataDTO.setData("testtest");
        noteDataDTO.setNoteId(noteDTO.getId());

        noteDTO.setDataId(noteDataDTO.getId());

        notePictureDTO = new NotePictureDTO();
        notePictureDTO.setId(UUID.randomUUID().toString());
        notePictureDTO.setPath("../test/testPic");
        notePictureDTO.setPictureName("testPic");
    }

    @Test
    @Order(order = 1)
    @Category(TestData.class)
    public void insertTest() {
        userService.insert(userDTO);
        boardService.insert(board);
        categoryService.insert(categoryDTO);
        noteDataService.insert(noteDataDTO);
        noteDeadlineService.insert(noteDeadline);
        noteService.merge(noteDTO);
    }

    @Test
    @Order(order = 2)
    @Category(TestData.class)
    public void findTest() {
        assertNotNull(userService.findAll());
        assertNotNull(boardService.findAll());
        assertNotNull(categoryService.findAll());
        assertNotNull(noteDataService.findAll());
        assertNotNull(noteDeadlineService.findAll());
        assertNotNull(noteService.findAll());
    }

    @Test
    @Order(order = 3)
    @Category(TestData.class)
    public void mergeTest() {
        assertNotEquals(noteDTO.getType(), NoteType.IMPORTANT);
        noteDTO.setType(NoteType.IMPORTANT);
        noteService.merge(noteDTO);
        assertEquals(noteService.findById(noteDTO.getId()).getType(), NoteType.IMPORTANT);
    }

    @Test
    @Order(order = 4)
    @Category(TestData.class)
    public void roleTest() {
        assertEquals(RoleType.USER, userService.findUserByLogin("admin").getRoles().get(0));
    }

    @Test
    @Order(order = 5)
    @Category(TestData.class)
    public void commentTest() {
        final CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(UUID.randomUUID().toString());
        commentDTO.setAuthorId(userDTO.getId());
        commentDTO.setNoteId(noteDTO.getId());
        commentDTO.setDate(Calendar.getInstance());
        commentDTO.setText("testcomment");
        commentService.insert(commentDTO);
        final List<CommentDTO> comments = commentService.findCommentsByNote(noteDTO);
        assertNotNull(comments);
        assertEquals("testcomment", comments.get(0).getText());
    }

    @Test
    @Order(order = 6)
    @Category(TestData.class)
    public void pictureTest() {
        pictureService.insert(notePictureDTO);
        noteDTO.setPictureId(notePictureDTO.getId());
        noteService.merge(noteDTO);
    }
    @Test
    @Order(order = 7)
    @Category(TestData.class)
    public void deadlineTest(){
        Calendar calendar = new GregorianCalendar(2019, 0, 10);
        assertEquals(noteDeadlineService.findById(noteDTO.getNoteDeadlineId()).getDeadlineDate().getTime(), calendar.getTime());
    }

    @Test
    @Order(order = 8)
    @Category(TestData.class)
    public void updateTest(){
        noteDTO.setType(NoteType.NORMAL);
        noteService.update(noteDTO);
        assertEquals(NoteType.NORMAL, noteService.findById(noteDTO.getId()).getType());
    }

    @Test
    @Order(order = 8)
    @Category(TestData.class)
    @Ignore
    public void deleteTest() {
//        noteService.deleteById(noteDTO.getId());
//        categoryService.deleteById(categoryDTO.getId());
//        boardService.deleteById(board.getId());
//        userService.deleteById(userDTO.getId());
//        pictureService.deleteById(notePictureDTO.getId());

        noteService.delete(noteDTO);
        categoryService.delete(categoryDTO);
        boardService.delete(board);
        userService.delete(userDTO);
        pictureService.delete(notePictureDTO);

        assertNull(noteService.findById(noteDTO.getId()));
        assertNull(categoryService.findById(categoryDTO.getId()));
        assertNull(boardService.findById(board.getId()));
        assertNull(userService.findById(userDTO.getId()));
        assertNull(noteDataService.findById(noteDataDTO.getId()));
        assertNull(noteDeadlineService.findById(noteDeadline.getId()));
        assertNull(pictureService.findById(notePictureDTO.getId()));
    }
}
