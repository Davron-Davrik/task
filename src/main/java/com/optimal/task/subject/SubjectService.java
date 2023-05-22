package com.optimal.task.subject;

import com.optimal.task.group.GroupEntity;
import com.optimal.task.group.GroupRepo;
import com.optimal.task.message.StateMessage;
import com.optimal.task.users.Users;
import com.optimal.task.users.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubjectService implements ISubjectService {

    private final SubjectRepo subjectRepo;
    private final GroupRepo groupRepo;
    private final UsersRepo usersRepo;

    public StateMessage add(SubjectDTO dto) {

        boolean res = subjectRepo.existsByNameAndGroupEntity_Id(dto.getName(), dto.getGroupId());

        if (res)
            return new StateMessage().suchANameExists();

        Optional<GroupEntity> optionalGroup = groupRepo.findById(dto.getGroupId());

        if (optionalGroup.isEmpty())
            return new StateMessage().wrongInformation();

        Subject subject = subjectRepo.save(new Subject(dto.getName(), optionalGroup.get()));

        return new StateMessage().successAdd();
    }

    public StateMessage edit(SubjectDTO dto, Long id) {

        Optional<Subject> optionalSubject = subjectRepo.findById(id);
        Optional<GroupEntity> optionalGroup = groupRepo.findById(dto.getGroupId());

        if (optionalGroup.isEmpty() || optionalSubject.isEmpty())
            return new StateMessage().wrongInformation();

        Subject subject = optionalSubject.get();
        if (!subject.getName().equals(dto.getName())) {
            boolean res = subjectRepo.existsByNameAndGroupEntity_Id(dto.getName(), dto.getGroupId());
            if (res)
                return new StateMessage().suchANameExists();
        }
        subject.setGroupEntity(optionalGroup.get());
        subjectRepo.save(subject);

        return new StateMessage().successEdit();
    }

    public StateMessage delete(Long id) {

        Optional<Subject> optionalSubject = subjectRepo.findById(id);

        if (optionalSubject.isEmpty()) {
            return new StateMessage().wrongInformation();
        }

        subjectRepo.deleteById(id);
        return new StateMessage().successDelete();
    }

    public List<SubjectResDTO> getAllByStudentId(Long studentId) {

        List<Subject> allByGroupEntityId = new ArrayList<>();
        List<SubjectResDTO> list = new ArrayList<>();

        Optional<Users> optionalUsers = usersRepo.findById(studentId);

        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            if (users.getGroupEntity() != null) {
                allByGroupEntityId = subjectRepo.findAllByGroupEntity_Id(optionalUsers.get().getId());
            }
        }

        for (Subject subject : allByGroupEntityId) {
            list.add(parseToDTO(subject));
        }

        list.sort(Comparator.comparing(SubjectResDTO::getName));
        return list;
    }

    public SubjectResDTO parseToDTO(Subject subject) {
        return new SubjectResDTO(
                subject.getId(),
                subject.getName(),
                subject.getCreateDate(),
                subject.getUpdateDate()
        );
    }
}
