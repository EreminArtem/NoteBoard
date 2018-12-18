package ru.eremin.noteboard.service.api;

import ru.eremin.noteboard.dto.AbstractDTO;

import java.util.List;

/**
 * @autor Artem Eremin on 16.12.2018.
 */
public interface IService<T extends AbstractDTO> {

    List<T> finadAll();

    List<T> findAll(final int page, final int size);

    T findById(final String id);

    void insert(final T t);

    void merge(final T t);

    void deleteById(final String id);

    void delete(final T t);

    void update(final T t);

}
