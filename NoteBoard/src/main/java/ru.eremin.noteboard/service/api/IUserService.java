package ru.eremin.noteboard.service.api;

import ru.eremin.noteboard.dto.UserDTO;
import ru.eremin.noteboard.entity.User;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

public interface IUserService extends IService<UserDTO, User> {

    UserDTO findUserByEmail(final String email);

    UserDTO findUserByLogin(final String login);

}
