package ru.eremin.noteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eremin.noteboard.entity.Board;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Repository(BoardRepository.NAME)
public interface BoardRepository extends JpaRepository<Board, String> {

    String NAME = "boardRepository";

    Board findBoardById(String id);

}
