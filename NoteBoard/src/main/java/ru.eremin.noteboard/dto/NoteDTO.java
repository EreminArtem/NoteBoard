package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.eremin.noteboard.entity.*;

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
@NoArgsConstructor
public class NoteDTO extends AbstractDTO implements Serializable {

    private String id;
    private String authorId;
    private String dataId;
    private String categotyId;
    private String boardId;
    private String typeId;
    private String pictureId;
    private String noteDeadlineId;
    private List<String> commentsId;

    public NoteDTO(final Note note) {
        if(note == null) return;
        this.id = note.getId();
        final User author = note.getAuthor();
        if(author!=null) this.authorId = author.getId();
        final NoteData noteData = note.getData();
        if(noteData!=null) this.dataId = noteData.getId();
        final Category category = note.getCategory();
        if(category != null) this.categotyId = category.getId();
        final Board board = note.getBoard();
        if(board != null) this.boardId = board.getId();
        final NoteType type = note.getType();
        if(type!=null) this.typeId = type.getId();
        final NotePicture picture = note.getPicture();
        if(picture != null) this.pictureId = picture.getId();
        final NoteDeadline deadline = note.getDeadline();
        if(deadline!=null) this.noteDeadlineId = deadline.getId();
        final List<Comment> comments = note.getComments();
        if(comments != null && !comments.isEmpty()){
            this.commentsId = new ArrayList<>();
            for (Comment comment:comments) {
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
