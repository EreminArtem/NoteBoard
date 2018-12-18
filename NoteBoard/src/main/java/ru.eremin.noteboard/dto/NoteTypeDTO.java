package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.eremin.noteboard.entity.NoteType;

import java.io.Serializable;
import java.util.Objects;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoteTypeDTO extends AbstractDTO implements Serializable {

    private String id;
    private String typeName;

    public NoteTypeDTO(final NoteType noteType) {
        if (noteType == null) return;
        this.id = noteType.getId();
        this.typeName = noteType.getTypeName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteTypeDTO that = (NoteTypeDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
