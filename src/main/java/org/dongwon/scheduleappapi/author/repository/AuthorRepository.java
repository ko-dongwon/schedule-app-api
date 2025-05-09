package org.dongwon.scheduleappapi.author.repository;

import org.dongwon.scheduleappapi.entity.Author;

import java.util.Optional;

public interface AuthorRepository {
    Long save(Author author);

    Optional<Author> findById(Long id);

    void update(Author author);

    void deleteById(Long id);
}
