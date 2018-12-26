package ru.eremin.noteboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.eremin.noteboard.dto.UserDTO;
import ru.eremin.noteboard.entity.User;
import ru.eremin.noteboard.service.api.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @autor Artem Eremin on 25.12.2018.
 */

@Controller
public class IndexController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/loginAction", method = RequestMethod.POST)
    public String login(
            final HttpServletRequest request,
            final HttpServletResponse response,
            @ModelAttribute("login") String login,
            @ModelAttribute("password") String password
    ) {
        if (login == null || login.isEmpty()) return "redirect:/index";
        if (password == null || password.isEmpty()) return "redirect:/index";

        final UserDTO user = userService.findUserByLogin(login);
        if (user == null) return "redirect:/index";

        final boolean check = passwordEncoder.matches(user.getHashPassword(), passwordEncoder.encode(password));
        if (!check) return "redirect:/index";

        final HttpSession session = request.getSession();
        session.setAttribute("auth", true);
        session.setAttribute("userId", user.getId());
        return "redirect:enter/boards-view";
    }

    @GetMapping("/logout")
    @PostMapping("/logout")
    public String logout(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/index";
    }
}
