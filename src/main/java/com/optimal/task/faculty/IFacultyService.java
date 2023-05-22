package com.optimal.task.faculty;

import com.optimal.task.message.StateMessage;

import java.util.List;

public interface IFacultyService {

    StateMessage add(FacultyDTO dto);

    StateMessage edit(FacultyDTO dto, Long id);

    StateMessage delete(Long id);

    List<FacultyResDTO> getAll();

    List<FacultyResDTO> getAll(Long universityId);

    FacultyResDTO getOne(Long id);

    FacultyResDTO parseToDTO(Faculty faculty);
}
