package ru.eremin.noteboard.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "note_table")
public class Note extends AbstractEntity implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    private User author;

    @OneToOne (cascade = CascadeType.ALL)
    private NoteData data;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Board board;

    @ManyToOne
    private NoteType type;

    @ManyToOne
    private NotePicture picture;

    @OneToOne (cascade = CascadeType.ALL)
    private NoteDeadline deadline;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
