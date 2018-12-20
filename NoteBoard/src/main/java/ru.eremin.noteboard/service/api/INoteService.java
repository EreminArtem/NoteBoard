package ru.eremin.noteboard.service.api;

import ru.eremin.noteboard.dto.*;
import ru.eremin.noteboard.entity.*;

import java.util.List;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

public interface INoteService extends IService<NoteDTO, Note> {

    List<NoteDTO> findNotesByAuthor(final UserDTO author);

    List<NoteDTO> findNotesByCategory(final CategoryDTO category);

    List<NoteDTO> findNotesByBoard(final BoardDTO board);

    List<NoteDTO> findNotesByType(final NoteTypeDTO type);

}
