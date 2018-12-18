package ru.eremin.noteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eremin.noteboard.entity.NoteType;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Repository(NoteTypeRepository.NAME)
public interface NoteTypeRepository extends JpaRepository<NoteType, String> {

    String NAME = "noteTypeRepository";

    NoteType findNoteTypeById(String id);

}
