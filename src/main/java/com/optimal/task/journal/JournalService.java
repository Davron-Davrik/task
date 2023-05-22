package com.optimal.task.journal;

import com.optimal.task.group.GroupEntity;
import com.optimal.task.group.GroupRepo;
import com.optimal.task.message.StateMessage;
import com.optimal.task.subject.Subject;
import com.optimal.task.subject.SubjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JournalService implements IJournalService {

    private final JournalRepo journalRepo;
    private final GroupRepo groupRepo;
    private final SubjectRepo subjectRepo;


    public StateMessage add(JournalDTO dto) {

        boolean res = journalRepo.existsAllByNameAndGroupEntity_Id(dto.getName(), dto.getGroupId());

        if (res) {
            return new StateMessage().suchANameExists();
        }

        Optional<GroupEntity> optionalGroup = groupRepo.findById(dto.getGroupId());
        List<Subject> list = subjectRepo.findAllById(dto.getSubjectIdList());

        for (Subject subject : list) {
            if (subject.getJournal() != null) {
                return new StateMessage().wrongInformation();
            }
        }

        if (optionalGroup.isEmpty() || list.size() == 0) {
            return new StateMessage().wrongInformation();
        }
        Journal save = journalRepo.save(new Journal(dto.getName(), list, optionalGroup.get()));

        for (Subject subject : list) {
            subject.setJournal(save);
        }

        subjectRepo.saveAll(list);

        return new StateMessage().successAdd();

    }

    public StateMessage edit(JournalDTO dto, Long id) {

        Optional<GroupEntity> optionalGroup = groupRepo.findById(dto.getGroupId());
        List<Subject> list = subjectRepo.findAllById(dto.getSubjectIdList());
        Optional<Journal> optionalJournal = journalRepo.findById(id);


        if (optionalJournal.isEmpty() || optionalGroup.isEmpty() || list.size() == 0) {
            return new StateMessage().wrongInformation();
        }

        for (Subject subject : list) {
            if (subject.getJournal() != null) {
                return new StateMessage().wrongInformation();
            }
        }


        Journal journal = optionalJournal.get();
        if (!journal.getName().equals(dto.getName())) {
            boolean res = journalRepo.existsAllByNameAndGroupEntity_Id(dto.getName(), dto.getGroupId());
            if (res) {
                return new StateMessage().suchANameExists();
            }
            journal.setName(dto.getName());
        }

        journal.setSubjectList(list);
        journalRepo.save(journal);

        return new StateMessage().successEdit();
    }

    public StateMessage delete(Long id) {
        Optional<Journal> optionalJournal = journalRepo.findById(id);

        if (optionalJournal.isEmpty()) {
            return new StateMessage().wrongInformation();
        }
        journalRepo.deleteById(id);
        return new StateMessage().successDelete();
    }
}
