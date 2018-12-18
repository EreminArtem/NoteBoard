package ru.eremin.noteboard.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
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
@Table(name = "note_type_table")
public class NoteType extends AbstractEntity implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "type_name")
    private String typeName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteType noteType = (NoteType) o;
        return Objects.equals(id, noteType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
