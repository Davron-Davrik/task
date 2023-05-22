package com.optimal.task.mark;

import com.optimal.task.journal.JournalRepo;
import com.optimal.task.message.StateMessage;
import com.optimal.task.subject.ISubjectService;
import com.optimal.task.subject.Subject;
import com.optimal.task.subject.SubjectRepo;
import com.optimal.task.users.IUserService;
import com.optimal.task.users.Users;
import com.optimal.task.users.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MarkService implements IMarkService {


    private final MarkRepo markRepo;
    private final UsersRepo usersRepo;
    private final SubjectRepo subjectRepo;
    private final IUserService iUserService;
    private final ISubjectService iSubjectService;

    public StateMessage add(MarkDTO dto) {

        Optional<Users> optionalUsers = usersRepo.findById(dto.getStudentId());
        Optional<Subject> optionalSubject = subjectRepo.findById(dto.getSubjectId());

        if (optionalSubject.isEmpty() || optionalUsers.isEmpty()) {
            return new StateMessage().wrongInformation();
        }
        Subject subject = optionalSubject.get();

        markRepo.save(new Mark(dto.getScore(), optionalUsers.get(), subject.getJournal(), subject));

        return new StateMessage().successAdd();
    }

    public StateMessage edit(MarkDTO dto, Long id) {

        Optional<Mark> optionalMark = markRepo.findById(id);
        Optional<Users> optionalUsers = usersRepo.findById(dto.getStudentId());
        Optional<Subject> optionalSubject = subjectRepo.findById(dto.getSubjectId());

        if (optionalSubject.isEmpty() || optionalUsers.isEmpty() || optionalMark.isEmpty()) {
            return new StateMessage().wrongInformation();
        }
        Mark mark = optionalMark.get();
        Subject subject = optionalSubject.get();

        mark.setScore(dto.getScore());
        mark.setSubject(subject);
        mark.setJournal(subject.getJournal());
        return new StateMessage().successEdit();
    }

    public List<MarkResDTO> getAllByStudentId(Long studentId) {

        List<MarkResDTO> list = new ArrayList<>();

        for (Mark mark : markRepo.findAllByStudent_Id(studentId)) {
            list.add(parseToDTO(mark));
        }
        return list;
    }

    public MarkResDTO parseToDTO(Mark mark) {
        return new MarkResDTO(
                mark.getId(),
                mark.getScore(),
                mark.getCreateDate(),
                mark.getUpdateDate(),
                iUserService.parseToDTO(mark.getStudent()),
                iSubjectService.parseToDTO(mark.getSubject())
        );
    }
}
