package org.dongwon.scheduleappapi.author.repository;

import lombok.RequiredArgsConstructor;
import org.dongwon.scheduleappapi.dto.AuthorRequestDto;
import org.dongwon.scheduleappapi.entity.Author;
import org.dongwon.scheduleappapi.mapper.AuthorMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Long createAuthor(AuthorRequestDto dto) {
        if(Objects.isNull(dto)) throw new IllegalArgumentException("DTO는 null일 수 없습니다.");
        Author author = AuthorMapper.toAuthor(dto);
        return authorRepository.save(author);
    }
}
