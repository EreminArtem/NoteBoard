package ru.eremin.noteboard.data;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.eremin.noteboard.config.AppConfiguration;
import ru.eremin.noteboard.dto.UserDTO;
import ru.eremin.noteboard.service.NoteService;
import ru.eremin.noteboard.service.UserService;
import ru.eremin.noteboard.service.api.INoteDataService;
import ru.eremin.noteboard.service.api.INoteService;
import ru.eremin.noteboard.service.api.IUserService;

import java.util.UUID;

/**
 * @autor Artem Eremin on 20.12.2018.
 */

public class NoteServiceTest {

    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        INoteService noteService = context.getBean(NoteService.NAME, INoteService.class);
        IUserService userService = context.getBean(UserService.NAME, IUserService.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID().toString());
        userDTO.setLogin("login1");
        userDTO.setHashPassword("ddfdf");
        userDTO.setEmail("email1");
        userService.insert(userDTO);
    }
}
