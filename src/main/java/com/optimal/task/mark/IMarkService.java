package com.optimal.task.mark;

import com.optimal.task.message.StateMessage;

import java.util.List;

public interface IMarkService {

    StateMessage add(MarkDTO dto);

    StateMessage edit(MarkDTO dto, Long id);

    List<MarkResDTO> getAllByStudentId(Long studentId);

    MarkResDTO parseToDTO(Mark mark);
}
