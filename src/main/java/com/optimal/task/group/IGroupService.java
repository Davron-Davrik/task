package com.optimal.task.group;

import com.optimal.task.message.StateMessage;

import java.util.List;

public interface IGroupService {
    StateMessage add(GroupDTO dto);
    StateMessage edit(GroupDTO dto, Long id);
    StateMessage delete(Long id);
    GroupResDTO getOne(Long id);
    List<GroupResDTO> getAll();
    List<GroupResDTO> getAll(Long facultyId);
    GroupResDTO parseToDTO(GroupEntity group);
}
