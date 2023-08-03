package com.engineer.library.service;

import com.engineer.library.model.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomPageResponse {
    private List<?> content;
    private String nextPageUrl;
    private String prevPageUrl;
    private Pageable pageable;
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
    private boolean isFirst;
    private boolean isLast;

    public CustomPageResponse(List<?> content, String nextPageUrl, String prevPageUrl, Pageable pageable, int totalPages, long totalElements, int numberOfElements, boolean isFirst, boolean isLast) {
        this.content = content;
        this.nextPageUrl = nextPageUrl;
        this.prevPageUrl = prevPageUrl;
        this.pageable = pageable;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.numberOfElements = numberOfElements;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }

    // Other getters and setters

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<Book> content) {
        this.content = content;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
