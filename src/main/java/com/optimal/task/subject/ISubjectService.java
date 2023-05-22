package com.optimal.task.subject;

import com.optimal.task.message.StateMessage;

import java.util.List;

public interface ISubjectService {

    StateMessage add(SubjectDTO dto);

    StateMessage edit(SubjectDTO dto, Long id);

    StateMessage delete(Long id);

    List<SubjectResDTO> getAllByStudentId(Long studentId);

    SubjectResDTO parseToDTO(Subject subject);
}
