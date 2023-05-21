package com.optimal.task.page;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CustomPageable {
    private int getPageSize;
    private int getPageNumber;
    private List<?> list;
    private long allPageSize;

    public CustomPageable(int getPageSize, int getPageNumber, List<?> list, long allPageSize) {
        this.getPageSize = getPageSize;
        this.getPageNumber = getPageNumber;
        this.list = list;
        this.allPageSize = (long) Math.ceil((double) allPageSize/getPageSize);
    }
}
