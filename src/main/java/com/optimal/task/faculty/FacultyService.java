package com.optimal.task.faculty;

import com.optimal.task.message.StateMessage;
import com.optimal.task.university.University;
import com.optimal.task.university.UniversityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FacultyService implements IFacultyService {

    private final FacultyRepo facultyRepo;
    private final UniversityRepo universityRepo;

    public StateMessage add(FacultyDTO dto) {

        Optional<University> optionalUniversity = universityRepo.findById(dto.getUniversityId());

        if (optionalUniversity.isEmpty())
            return new StateMessage("Universitet topilmadi", false, 417);

        University university = optionalUniversity.get();

        boolean res = facultyRepo.existsAllByNameAndUniversity_Id(dto.getName(), dto.getUniversityId());

        if (res)
            return new StateMessage().suchANameExists();

        Faculty faculty = facultyRepo.save(new Faculty(dto.getName(), university));

        return new StateMessage().successAdd();
    }

    public StateMessage edit(FacultyDTO dto, Long id) {

        Optional<University> optionalUniversity = universityRepo.findById(dto.getUniversityId());
        Optional<Faculty> optionalFaculty = facultyRepo.findById(id);


        if (optionalUniversity.isEmpty() || optionalFaculty.isEmpty())
            return new StateMessage().wrongInformation();

        University university = optionalUniversity.get();
        Faculty faculty = optionalFaculty.get();

        if (!faculty.getName().equals(dto.getName())) {
            boolean res = facultyRepo.existsAllByNameAndUniversity_Id(dto.getName(), dto.getUniversityId());

            if (res)
                return new StateMessage().suchANameExists();
            faculty.setName(dto.getName());
        }
        faculty.setUniversity(university);

        facultyRepo.save(faculty);
        return new StateMessage().successEdit();
    }

    public StateMessage delete(Long id) {
        Optional<Faculty> optionalFaculty = facultyRepo.findById(id);

        if (optionalFaculty.isEmpty())
            return new StateMessage().wrongInformation();

        facultyRepo.deleteById(id);
        return new StateMessage().successDelete();
    }

    public List<FacultyResDTO> getAll() {

        List<FacultyResDTO> list = new ArrayList<>();

        for (Faculty faculty : facultyRepo.findAll()) {
            list.add(parseToDTO(faculty));
        }
        return list;
    }

    public List<FacultyResDTO> getAll(Long universityId) {

        List<FacultyResDTO> list = new ArrayList<>();

        for (Faculty faculty : facultyRepo.findAllByUniversity_Id(universityId)) {
            list.add(parseToDTO(faculty));
        }
        return list;
    }

    public FacultyResDTO getOne(Long id) {

        Optional<Faculty> optionalFaculty = facultyRepo.findById(id);

        return optionalFaculty.map(this::parseToDTO).orElse(null);
    }

    public FacultyResDTO parseToDTO(Faculty faculty) {
        return new FacultyResDTO(
                faculty.getId(),
                faculty.getName(),
                faculty.getCreateDate(),
                faculty.getUpdateDate(),
                null,
                null
        );
    }
}
