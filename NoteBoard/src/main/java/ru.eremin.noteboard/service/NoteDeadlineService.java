package ru.eremin.noteboard.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eremin.noteboard.dto.NoteDTO;
import ru.eremin.noteboard.dto.NoteDeadlineDTO;
import ru.eremin.noteboard.entity.Note;
import ru.eremin.noteboard.entity.NoteDeadline;
import ru.eremin.noteboard.repository.NoteDeadlineRepository;
import ru.eremin.noteboard.repository.NoteRepository;
import ru.eremin.noteboard.service.api.INoteDeadlineService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Artem Eremin on 19.12.2018.
 */

@Service(NoteDeadlineService.NAME)
public class NoteDeadlineService implements INoteDeadlineService {

    public static final String NAME = "noteDeadlineService";

    @Autowired
    private NoteDeadlineRepository repository;

    @Autowired
    private NoteRepository noteRepository;

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public NoteDeadlineDTO findNoteDeadlineByNote(@Nullable final NoteDTO noteDTO) {
        if (noteDTO == null) return null;
        final Note note = noteRepository.findNoteById(noteDTO.getId());
        if (note == null) return null;
        final NoteDeadline noteDeadline = repository.findNoteDeadlineByNote(note);
        return new NoteDeadlineDTO(noteDeadline);
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteDeadlineDTO> finadAll() {
        final List<NoteDeadline> noteDeadlines = repository.findAll();
        if (noteDeadlines == null || noteDeadlines.isEmpty()) return null;
        return noteDeadlines.stream().map(NoteDeadlineDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public List<NoteDeadlineDTO> findAll(final int page, final int size) {
        final List<NoteDeadline> noteDeadlines = repository.findAll(PageRequest.of(page, size)).getContent();
        if (noteDeadlines == null || noteDeadlines.isEmpty()) return null;
        return noteDeadlines.stream().map(NoteDeadlineDTO::new).collect(Collectors.toList());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public NoteDeadlineDTO findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        final NoteDeadline noteDeadline = repository.findNoteDeadlineById(id);
        if (noteDeadline == null) return null;
        return new NoteDeadlineDTO(noteDeadline);
    }

    @Override
    @Transactional
    public void insert(@Nullable final NoteDeadlineDTO noteDeadlineDTO) {
        if (noteDeadlineDTO == null) return;
        final NoteDeadline noteDeadline = getEntity(noteDeadlineDTO);
        if (repository.findNoteDeadlineById(noteDeadline.getId()) != null) return;
        repository.save(noteDeadline);
    }

    @Override
    @Transactional
    public void merge(@Nullable final NoteDeadlineDTO noteDeadlineDTO) {
        if (noteDeadlineDTO == null) return;
        final NoteDeadline noteDeadline = getEntity(noteDeadlineDTO);
        repository.save(noteDeadline);
    }

    @Override
    @Transactional
    public void deleteById(@Nullable final String id) {
        if (id != null && !id.isEmpty()) repository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(@Nullable final NoteDeadlineDTO noteDeadlineDTO) {
        if (noteDeadlineDTO == null) return;
        final NoteDeadline noteDeadline = getEntity(noteDeadlineDTO);
        repository.delete(noteDeadline);
    }

    @Override
    @Transactional
    public void update(@Nullable final NoteDeadlineDTO noteDeadlineDTO) {
        if (noteDeadlineDTO == null) return;
        if (repository.findNoteDeadlineById(noteDeadlineDTO.getId()) == null) return;
        final NoteDeadline noteDeadline = getEntity(noteDeadlineDTO);
        repository.save(noteDeadline);
    }

    @Override
    @NotNull
    public NoteDeadline getEntity(@NotNull final NoteDeadlineDTO noteDeadlineDTO) {
        final NoteDeadline noteDeadline = new NoteDeadline();
        noteDeadline.setId(noteDeadlineDTO.getId());
        noteDeadline.setDeadlineDate(noteDeadlineDTO.getDeadlineDate());
        noteDeadline.setNote(noteRepository.findNoteById(noteDeadlineDTO.getNoteId()));
        return noteDeadline;
    }
}
