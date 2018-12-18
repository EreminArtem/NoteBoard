package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.eremin.noteboard.entity.Note;
import ru.eremin.noteboard.entity.NoteDeadline;

import java.util.Calendar;
import java.util.Objects;

/**
 * @autor Artem Eremin on 18.12.2018.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoteDeadlineDTO extends AbstractDTO {

    private String id;
    private Calendar deadlineDate;
    private String noteId;

    public NoteDeadlineDTO(final NoteDeadline deadline) {
        if (deadline == null) return;
        this.id = deadline.getId();
        this.deadlineDate = deadline.getDeadlineDate();
        final Note note = deadline.getNote();
        if (note != null) this.noteId = note.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteDeadlineDTO that = (NoteDeadlineDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
