package ru.eremin.noteboard.service.api;

import ru.eremin.noteboard.dto.NoteDTO;
import ru.eremin.noteboard.dto.NoteDataDTO;
import ru.eremin.noteboard.entity.NoteData;

/**
 * @autor Artem Eremin on 16.12.2018.
 */

public interface INoteDataService extends IService<NoteDataDTO, NoteData> {

    NoteDataDTO findNodeDataByNote(final NoteDTO noteDTO);

}
