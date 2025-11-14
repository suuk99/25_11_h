package com.example.demo.dto;

public class Paging {
    private int page;        // 현재 페이지
    private int amount;      // 페이지당 글 수
    private int totalCount;  // 전체 글 수

    private int startPage;   // 페이지 블럭 시작
    private int endPage;     // 페이지 블럭 끝
    private int totalPage;   // 전체 페이지
    private boolean prev;    
    private boolean next;    

    public Paging() {
        this.page = 1;
        this.amount = 10;
    }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public int getTotalCount() { return totalCount; }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcPages();
    }

    public int getStartPage() { return startPage; }
    public int getEndPage() { return endPage; }
    public int getTotalPage() { return totalPage; }
    public boolean isPrev() { return prev; }
    public boolean isNext() { return next; }

    private void calcPages() {
        this.totalPage = (int)Math.ceil((double)totalCount / amount);
        this.endPage = (int)Math.ceil((double)page / 10) * 10;
        this.startPage = this.endPage - 9;

        if (endPage > totalPage) {
            endPage = totalPage;
        }

        prev = startPage > 1;
        next = endPage < totalPage;
    }

    public int getPageStart() {
        return (page - 1) * amount;
    }
}
