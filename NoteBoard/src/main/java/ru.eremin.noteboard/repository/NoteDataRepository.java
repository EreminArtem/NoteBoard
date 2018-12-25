package ru.eremin.noteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eremin.noteboard.entity.Note;
import ru.eremin.noteboard.entity.NoteData;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Repository(NoteDataRepository.NAME)
public interface NoteDataRepository extends JpaRepository<NoteData, String> {

    String NAME = "noteDataRepository";

    NoteData findNoteDataById(String Id);

    NoteData findNoteDataByNote(Note note);

}
