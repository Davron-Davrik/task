package com.optimal.task.journal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JournalDTO {

    private String name;
    private List<Long> subjectIdList;
    private Long groupId;
}
