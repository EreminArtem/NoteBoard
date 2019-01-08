package ru.eremin.noteboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @autor Artem Eremin on 04.01.2019.
 */

@Controller
public class BoardsController {
    @GetMapping(value = "/boards")
    public String index() {
        System.out.println("FFFFFFFFFFFFF board");
        return "boards-view";
    }

}
