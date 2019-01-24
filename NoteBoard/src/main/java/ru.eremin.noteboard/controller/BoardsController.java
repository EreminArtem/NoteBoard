package ru.eremin.noteboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.eremin.noteboard.dto.BoardDTO;
import ru.eremin.noteboard.service.api.IBoardService;

import java.util.List;

/**
 * @autor Artem Eremin on 04.01.2019.
 */

@Controller
public class BoardsController {

    @Autowired
    private IBoardService service;

    @GetMapping(value = "/boards")
    public String boards(final Model model) {
        final List<BoardDTO> boardDTOList = service.findAll();
        model.addAttribute("boards", boardDTOList);
        return "boards-view";
    }

}
