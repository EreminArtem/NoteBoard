package ru.eremin.noteboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.eremin.noteboard.dto.NoteDTO;
import ru.eremin.noteboard.service.api.IBoardService;
import ru.eremin.noteboard.service.api.INoteService;

import java.util.List;

/**
 * @autor Artem Eremin on 08.01.2019.
 */

@Controller
public class NoteController {

    @Autowired
    private INoteService noteService;

    @Autowired
    private IBoardService boardService;

    @GetMapping("note/boardId={id}")
    public String noteView(final Model model, @PathVariable("id") final String id) {
//        final List<NoteDTO> noteList = noteService.findNotesByBoardId(id);
//        if (noteList != null || !noteList.isEmpty()) model.addAttribute("notes", noteList);
//        return "note-view";

        model.addAttribute("boardId", id);
        return "note-view";
    }
}
