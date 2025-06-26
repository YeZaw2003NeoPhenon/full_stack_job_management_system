package com.example.job_management_system.response;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T>{

    private List<T> content;
    private int size;
    private int page;
    private int totalPages;
    private long totalElements;

    public PageResponse(){}

    public PageResponse(Page<T> page){
        this.content = page.getContent();
        this.size = page.getSize();
        this.page = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
}
