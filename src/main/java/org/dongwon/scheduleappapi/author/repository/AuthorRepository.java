package org.dongwon.scheduleappapi.author.repository;

import org.dongwon.scheduleappapi.entity.Author;

public interface AuthorRepository {
    Long save(Author author);
}
