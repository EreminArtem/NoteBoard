package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.eremin.noteboard.entity.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Getter
@Setter
@ToString
@XmlRootElement
@NoArgsConstructor
public class NoteDTO extends AbstractDTO implements Serializable {

    private String id;
    private NoteStatus status;
    private String authorId;
    private String dataId;
    private String categotyId;
    private String boardId;
    private NoteType type;
    private String pictureId;
    private String noteDeadlineId;
    private List<String> commentsId;

    public NoteDTO(final Note note) {
        if(note == null) return;
        this.id = note.getId();
        this.status = note.getStatus();
        this.type = note.getType();
        this.authorId = note.getAuthor().getId();
        this.dataId = note.getData().getId();
        this.categotyId = note.getCategory().getId();
        this.boardId = note.getBoard().getId();
        this.pictureId = note.getPicture().getId();
        this.noteDeadlineId = note.getDeadline().getId();
        final List<Comment> comments = note.getComments();
        if(comments != null && !comments.isEmpty()){
            this.commentsId = new ArrayList<>();
            for (final Comment comment:comments) {
                commentsId.add(comment.getId());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteDTO noteDTO = (NoteDTO) o;
        return Objects.equals(id, noteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
