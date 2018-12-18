package ru.eremin.noteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eremin.noteboard.entity.Note;
import ru.eremin.noteboard.entity.NoteDeadline;

/**
 * @autor Artem Eremin on 18.12.2018.
 */

@Repository(NoteDeadlineRepository.NAME)
public interface NoteDeadlineRepository extends JpaRepository<NoteDeadline, String> {

    String NAME = "noteDeadlineRepository";

    NoteDeadline findNoteDeadlineById(String id);

    NoteDeadline findNoteDeadlineByNote(Note note);

}
