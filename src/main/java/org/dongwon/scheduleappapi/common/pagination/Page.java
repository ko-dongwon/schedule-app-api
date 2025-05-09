package org.dongwon.scheduleappapi.common.pagination;

import lombok.Getter;

import java.util.List;

@Getter
public class Page <T>{
    private List<T> content;

    // 요청한 페이지
    private int page;

    // 페이지 당 표시할 컨텐츠의 개수
    private int size;

    // 전체 컨텐츠 개수
    private long totalCount;

    // 전체 페이지 개수
    private int totalPages;

    private Page(List<T> content, int page, int size, long totalCount) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;
        this.totalPages = (int) (totalCount / size) + 1;
    }

    public static <T> Page<T> of(List<T> content, int page, int size, long totalCount) {
        return new Page<>(content, page, size, totalCount);
    }
}
