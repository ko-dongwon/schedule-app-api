package org.dongwon.scheduleappapi.author.repository;

import lombok.RequiredArgsConstructor;
import org.dongwon.scheduleappapi.dto.AuthorRequestDto;
import org.dongwon.scheduleappapi.entity.Author;
import org.dongwon.scheduleappapi.mapper.AuthorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional
    public Long createAuthor(AuthorRequestDto dto) {
        if(Objects.isNull(dto)) throw new IllegalArgumentException("DTO는 null일 수 없습니다.");
        Author author = AuthorMapper.toAuthor(dto);
        return authorRepository.save(author);
    }

    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Author(id:" + id + ")가 존재하지 않습니다."));
    }

    @Transactional
    public void updateAuthorName(Long id, String authorName) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Author(id:" + id + ")가 존재하지 않습니다."));
        author.updateName(authorName);
        authorRepository.update(author);
    }

    @Transactional
    public void removeAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Author(id:" + id + ")가 존재하지 않습니다."));
        authorRepository.deleteById(author.getId());
    }
}
