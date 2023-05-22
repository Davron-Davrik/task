package com.optimal.task.university;


import com.optimal.task.faculty.IFacultyService;
import com.optimal.task.message.StateMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversityService implements IUniversityService {

    private final UniversityRepo universityRepo;
    private final IFacultyService iFacultyService;

    public StateMessage add(UniversityDTO dto) {

        boolean res = universityRepo.existsAllByName(dto.getName());

        if (res) {
            return new StateMessage().suchANameExists();
        }

        University university = universityRepo.save(new University(
                dto.getName(),
                dto.getAddress(),
                dto.getOpenYear()
        ));

        return new StateMessage().successAdd();
    }

    public StateMessage edit(UniversityDTO dto, Long id) {

        Optional<University> optionalUniversity = universityRepo.findById(id);


        if (optionalUniversity.isEmpty())
            return new StateMessage().wrongInformation();

        University university = optionalUniversity.get();

        if (!university.getName().equals(dto.getName())) {
            boolean res = universityRepo.existsAllByName(dto.getName());
            if (res) {
                return new StateMessage().suchANameExists();
            }
            university.setName(dto.getName());
        }

        university.setOpenYear(dto.getOpenYear());
        university.setAddress(dto.getAddress());

        return new StateMessage().successEdit();
    }

    public StateMessage delete(Long id) {
        Optional<University> optionalUniversity = universityRepo.findById(id);

        if (optionalUniversity.isEmpty())
            return new StateMessage().wrongInformation();

        universityRepo.deleteById(id);

        return new StateMessage().successDelete();

    }

    public List<UniversityResDTO> getAll() {

        List<UniversityResDTO> list = new ArrayList<>();

        for (University university : universityRepo.findAll()) {

            list.add(parseToDTO(university));
        }
        list.sort(Comparator.comparing(UniversityResDTO::getName));
        return list;
    }

    public UniversityResDTO getOne(Long id) {

        Optional<University> optionalUniversity = universityRepo.findById(id);
        return optionalUniversity.map(this::parseToDTO).orElse(null);
    }

    public UniversityResDTO parseToDTO(University university) {
        return new UniversityResDTO(
                university.getId(),
                university.getName(),
                university.getAddress(),
                university.getOpenYear(),
                university.getCreateDate(),
                university.getUpdateDate()
        );
    }
}
