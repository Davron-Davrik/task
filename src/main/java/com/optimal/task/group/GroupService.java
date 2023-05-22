package com.optimal.task.group;

import com.optimal.task.faculty.Faculty;
import com.optimal.task.faculty.FacultyRepo;
import com.optimal.task.message.StateMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GroupService implements IGroupService {

    private final GroupRepo groupRepo;
    private final FacultyRepo facultyRepo;

    public StateMessage add(GroupDTO dto) {

        boolean res = groupRepo.existsAllByNameContainingIgnoreCase(dto.getName());

        if (res)
            return new StateMessage().suchANameExists();

        Optional<Faculty> optionalFaculty = facultyRepo.findById(dto.getFacultyId());

        if (optionalFaculty.isEmpty()) {
            return new StateMessage().wrongInformation();
        }

        Faculty faculty = optionalFaculty.get();

        GroupEntity group = groupRepo.save(new GroupEntity(
                dto.getName(),
                dto.getYear(),
                faculty
        ));

        return new StateMessage().successAdd();
    }

    public StateMessage edit(GroupDTO dto, Long id) {
        Optional<Faculty> optionalFaculty = facultyRepo.findById(dto.getFacultyId());
        Optional<GroupEntity> optionalGroup = groupRepo.findById(id);

        if (optionalFaculty.isEmpty() || optionalGroup.isEmpty()) {
            return new StateMessage().wrongInformation();
        }
        GroupEntity group = optionalGroup.get();
        Faculty faculty = optionalFaculty.get();

        if (!group.getName().equals(dto.getName())) {
            boolean res = groupRepo.existsAllByNameContainingIgnoreCase(dto.getName());
            if (res) {
                return new StateMessage().suchANameExists();
            }
            group.setName(dto.getName());
        }
        group.setFaculty(faculty);
        group.setYear(dto.getYear());
        groupRepo.save(group);
        return new StateMessage().successEdit();
    }

    public StateMessage delete(Long id) {
        Optional<GroupEntity> optionalGroup = groupRepo.findById(id);

        if (optionalGroup.isEmpty())
            return new StateMessage().wrongInformation();


        groupRepo.deleteById(id);
        return new StateMessage().successDelete();
    }

    public GroupResDTO getOne(Long id) {
        Optional<GroupEntity> optionalGroup = groupRepo.findById(id);
        return optionalGroup.map(this::parseToDTO).orElse(null);
    }

    public List<GroupResDTO> getAll() {
        List<GroupResDTO> list = new ArrayList<>();

        for (GroupEntity group : groupRepo.findAll()) {
            list.add(parseToDTO(group));
        }
        return list;
    }

    public List<GroupResDTO> getAll(Long facultyId) {
        List<GroupResDTO> list = new ArrayList<>();

        for (GroupEntity group : groupRepo.findAllByFaculty_Id(facultyId)) {
            list.add(parseToDTO(group));
        }
        return list;
    }

    public GroupResDTO parseToDTO(GroupEntity group) {
        return new GroupResDTO(
                group.getId(),
                group.getName(),
                group.getYear(),
                group.getCreateDate(),
                group.getUpdateDate(),
                group.getStudentList().size()
        );
    }
}
