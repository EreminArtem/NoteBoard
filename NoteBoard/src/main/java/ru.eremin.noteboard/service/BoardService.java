package ru.eremin.noteboard.service;

import org.springframework.stereotype.Service;
import ru.eremin.noteboard.dto.BoardDTO;
import ru.eremin.noteboard.service.api.IBoardService;
import sun.security.provider.PolicySpiFile;

import java.util.List;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

@Service(BoardService.NAME)
public class BoardService implements IBoardService {

    public static final String NAME = "boardService";


    @Override
    public List<BoardDTO> finadAll() {
        return null;
    }

    @Override
    public List<BoardDTO> findAll(int page, int size) {
        return null;
    }

    @Override
    public BoardDTO findById(String id) {
        return null;
    }

    @Override
    public void insert(BoardDTO boardDTO) {

    }

    @Override
    public void merge(BoardDTO boardDTO) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void delete(BoardDTO boardDTO) {

    }

    @Override
    public void update(BoardDTO boardDTO) {

    }
}
