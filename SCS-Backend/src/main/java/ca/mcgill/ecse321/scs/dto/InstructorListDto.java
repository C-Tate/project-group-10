package ca.mcgill.ecse321.scs.dto;

import java.util.ArrayList;
import java.util.List;

public class InstructorListDto {
    private List<InstructorResponseDto> instructors;

    public InstructorListDto() {
        this.instructors = new ArrayList<>();
    }

    public InstructorListDto(List<InstructorResponseDto> instructors) {
        this.instructors = instructors;
    }

    public List<InstructorResponseDto> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<InstructorResponseDto> instructors) {
        this.instructors = instructors;
    }
}
