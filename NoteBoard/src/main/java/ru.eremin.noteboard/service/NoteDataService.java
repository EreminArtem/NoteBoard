package ru.eremin.noteboard.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.noteboard.dto.NoteDTO;
import ru.eremin.noteboard.dto.NoteDataDTO;
import ru.eremin.noteboard.entity.Note;
import ru.eremin.noteboard.entity.NoteData;
import ru.eremin.noteboard.repository.NoteDataRepository;
import ru.eremin.noteboard.repository.NoteRepository;
import ru.eremin.noteboard.service.api.INoteDataService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Artem Eremin on 19.12.2018.
 */

@Service(NoteDataService.NAME)
public class NoteDataService implements INoteDataService {

    public static final String NAME = "nodeDataService";

    @Autowired
    private NoteDataRepository repository;

    @Autowired
    private NoteRepository noteRepository;

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public NoteDataDTO findNodeDataByNote(@Nullable final NoteDTO noteDTO) {
        if (noteDTO == null) return null;
        final Note note = noteRepository.findNoteById(noteDTO.getId());
        if (note == null) return null;
        final NoteData noteData = repository.findNoteDataByNote(note);
        if (noteData == null) return null;
        return new NoteDataDTO(noteData);
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteDataDTO> findAll() {
        final List<NoteData> noteDataList = repository.findAll();
        if (noteDataList == null || noteDataList.isEmpty()) return null;
        return noteDataList.stream().map(NoteDataDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteDataDTO> findAll(final int page, final int size) {
        final List<NoteData> noteDataList = repository.findAll(PageRequest.of(page, size)).getContent();
        if (noteDataList == null || noteDataList.isEmpty()) return null;
        return noteDataList.stream().map(NoteDataDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public NoteDataDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final NoteData noteData = repository.findNoteDataById(id);
        if (noteData == null) return null;
        return new NoteDataDTO(noteData);
    }

    @Override
    @Transactional
    public void insert(@Nullable final NoteDataDTO noteDataDTO) {
        if (noteDataDTO == null) return;
        final NoteData noteData = getEntity(noteDataDTO);
        if (repository.findNoteDataById(noteData.getId()) != null) return;
        repository.save(noteData);
    }

    @Override
    @Transactional
    public void merge(@Nullable final NoteDataDTO noteDataDTO) {
        if (noteDataDTO == null) return;
        final NoteData noteData = getEntity(noteDataDTO);
        repository.save(noteData);
    }

    @Override
    @Transactional
    public void deleteById(@Nullable final String id) {
        if (id != null && !id.isEmpty()) repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(@Nullable final NoteDataDTO noteDataDTO) {
        if (noteDataDTO == null) return;
        final NoteData noteData = getEntity(noteDataDTO);
        repository.delete(noteData);
    }

    @Override
    @Transactional
    public void update(@Nullable final NoteDataDTO noteDataDTO) {
        if (noteDataDTO == null) return;
        if (repository.findNoteDataById(noteDataDTO.getId()) == null) return;
        final NoteData noteData = getEntity(noteDataDTO);
        repository.save(noteData);
    }

    @Override
    @NotNull
    public NoteData getEntity(@NotNull final NoteDataDTO noteDataDTO) {
        final NoteData noteData = new NoteData();
        noteData.setId(noteDataDTO.getId());
        noteData.setData(noteDataDTO.getData());
        noteData.setNote(noteRepository.findNoteById(noteDataDTO.getNoteId()));
        return noteData;
    }
}
