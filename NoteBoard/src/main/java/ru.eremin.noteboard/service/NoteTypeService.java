package ru.eremin.noteboard.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.noteboard.dto.NoteTypeDTO;
import ru.eremin.noteboard.entity.NoteType;
import ru.eremin.noteboard.repository.NoteTypeRepository;
import ru.eremin.noteboard.service.api.INoteTypeService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Artem Eremin on 20.12.2018.
 */

@Service(NoteTypeService.NAME)
public class NoteTypeService implements INoteTypeService {

    public static final String NAME = "noteTypeService";

    @Autowired
    private NoteTypeRepository repository;

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteTypeDTO> finadAll() {
        final List<NoteType> noteTypes = repository.findAll();
        if (noteTypes == null || noteTypes.isEmpty()) return null;
        return noteTypes.stream().map(NoteTypeDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteTypeDTO> findAll(final int page, final int size) {
        final List<NoteType> noteTypes = repository.findAll(PageRequest.of(page, size)).getContent();
        if (noteTypes == null || noteTypes.isEmpty()) return null;
        return noteTypes.stream().map(NoteTypeDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public NoteTypeDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        NoteType noteType = repository.findNoteTypeById(id);
        if (noteType == null) return null;
        return new NoteTypeDTO(noteType);
    }

    @Override
    @Transactional
    public void insert(@Nullable final NoteTypeDTO noteTypeDTO) {
        if (noteTypeDTO == null) return;
        final NoteType noteType = getEntity(noteTypeDTO);
        if (repository.findNoteTypeById(noteTypeDTO.getId()) != null) return;
        repository.save(noteType);
    }

    @Override
    @Transactional
    public void merge(@Nullable final NoteTypeDTO noteTypeDTO) {
        if (noteTypeDTO == null) return;
        final NoteType noteType = getEntity(noteTypeDTO);
        repository.save(noteType);
    }

    @Override
    @Transactional
    public void deleteById(@Nullable final String id) {
        if (id != null && !id.isEmpty()) repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(@Nullable final NoteTypeDTO noteTypeDTO) {
        if (noteTypeDTO == null) return;
        final NoteType noteType = getEntity(noteTypeDTO);
        repository.delete(noteType);
    }

    @Override
    @Transactional
    public void update(@Nullable final NoteTypeDTO noteTypeDTO) {
        if (noteTypeDTO == null) return;
        if (repository.findNoteTypeById(noteTypeDTO.getId()) == null) return;
        final NoteType noteType = getEntity(noteTypeDTO);
        repository.save(noteType);
    }

    @Override
    @NotNull
    public NoteType getEntity(@NotNull NoteTypeDTO noteTypeDTO) {
        final NoteType noteType = new NoteType();
        noteType.setId(noteTypeDTO.getId());
        noteType.setTypeName(noteTypeDTO.getTypeName());
        return noteType;
    }
}
