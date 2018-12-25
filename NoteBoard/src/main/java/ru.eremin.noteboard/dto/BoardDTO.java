package ru.eremin.noteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.eremin.noteboard.entity.Board;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO extends AbstractDTO implements Serializable {

    private String id;
    private String name;
    private Calendar date;

    public BoardDTO(final Board board) {
        if(board == null) return;
        this.id = board.getId();
        this.name = board.getName();
        this.date = board.getDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDTO boardDTO = (BoardDTO) o;
        return Objects.equals(id, boardDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
