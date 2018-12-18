package ru.eremin.noteboard.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

/**
 * @autor Artem Eremin on 18.12.2018.
 */

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "note_table")
public class NoteDeadline  extends  AbstractEntity{

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "deadline_date")
    private Calendar deadlineDate;

    @OneToOne
    private Note note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteDeadline that = (NoteDeadline) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
