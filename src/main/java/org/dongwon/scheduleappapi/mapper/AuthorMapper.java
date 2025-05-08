package org.dongwon.scheduleappapi.mapper;

import org.dongwon.scheduleappapi.dto.AuthorRequestDto;
import org.dongwon.scheduleappapi.entity.Author;

public class AuthorMapper {
    public static Author toAuthor(AuthorRequestDto dto) {
        return Author.createAuthor(dto.getAuthorName(), dto.getEmail());
    }
}
