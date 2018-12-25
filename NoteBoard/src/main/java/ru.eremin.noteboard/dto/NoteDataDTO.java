package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.eremin.noteboard.entity.Note;
import ru.eremin.noteboard.entity.NoteData;

import java.io.Serializable;
import java.util.Objects;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoteDataDTO extends AbstractDTO implements Serializable {

    private String id;
    private String data;
    private String noteId;

    public NoteDataDTO(final NoteData noteData) {
        if (noteData == null) return;
        this.id = noteData.getId();
        this.data = noteData.getData();
        final Note note = noteData.getNote();
        if (note != null) this.noteId = note.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteDataDTO that = (NoteDataDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
