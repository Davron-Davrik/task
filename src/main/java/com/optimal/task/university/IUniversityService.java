package com.optimal.task.university;

import com.optimal.task.message.StateMessage;

import java.util.List;

public interface IUniversityService {

    StateMessage add(UniversityDTO dto);

    StateMessage edit(UniversityDTO dto, Long id);

    StateMessage delete(Long id);

    List<UniversityResDTO> getAll();

    UniversityResDTO getOne(Long id);

    UniversityResDTO parseToDTO(University university);
}
