package com.optimal.task.mark;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarkDTO {

    private int score;
    private Long studentId;
    private Long subjectId;
}
