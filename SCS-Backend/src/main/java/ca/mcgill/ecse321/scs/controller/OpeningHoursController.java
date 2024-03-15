// package ca.mcgill.ecse321.scs.controller;

// import ca.mcgill.ecse321.scs.dto.OpeningHoursDto;
// import ca.mcgill.ecse321.scs.service.OpeningHoursService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.LocalTime;
// import java.util.List;
// import java.util.stream.Collectors;
// import ca.mcgill.ecse321.scs.model.OpeningHours; // Add this import statement

// @RestController
// @RequestMapping("/api/openinghours")
// public class OpeningHoursController {
//     @Autowired
//     private OpeningHoursService openingHoursService;

//     @PostMapping
//     public ResponseEntity<OpeningHoursDto> createOpeningHours(@RequestBody OpeningHoursDto openingHoursDto) {
//         OpeningHoursDto createdOpeningHours = convertToDto(openingHoursService.createOpeningHours(openingHoursDto.getDayOfWeek().toString(), LocalTime.parse(openingHoursDto.getOpenTime()), LocalTime.parse(openingHoursDto.getCloseTime()), openingHoursDto.getYear()));
//         return new ResponseEntity<>(createdOpeningHours, HttpStatus.CREATED);
//     }

//     @GetMapping("/{day}")
//     public ResponseEntity<OpeningHoursDto> getOpeningHoursByDay(@PathVariable String day) {
//         OpeningHoursDto openingHours = convertToDto(openingHoursService.getOpeningHoursByDay(day));
//         return new ResponseEntity<>(openingHours, HttpStatus.OK);
//     }

//     @PutMapping
//     public ResponseEntity<OpeningHoursDto> updateOpeningHours(@RequestBody OpeningHoursDto openingHoursDto) {
//         OpeningHoursDto updatedOpeningHours = convertToDto(openingHoursService.updateOpeningHours(LocalTime.parse(openingHoursDto.getOpenTime()), LocalTime.parse(openingHoursDto.getCloseTime()), openingHoursDto.getYear(), openingHoursDto.getDayOfWeek().toString()));
//         return new ResponseEntity<>(updatedOpeningHours, HttpStatus.OK);
//     }

//     @GetMapping
//     public ResponseEntity<List<OpeningHoursDto>> getAllOpeningHours() {
//         List<OpeningHoursDto> openingHours = openingHoursService.getAllOpeningHours().stream().map(this::convertToDto).collect(Collectors.toList());
//         return new ResponseEntity<>(openingHours, HttpStatus.OK);
//     }

//     @DeleteMapping("/{day}")
//     public ResponseEntity<Void> deleteOpeningHours(@PathVariable String day) {
//         openingHoursService.deleteOpeningHours(day);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }

//     @DeleteMapping
//     public ResponseEntity<Void> deleteAllOpeningHours() {
//         openingHoursService.deleteAllOpeningHours();
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }

//     private OpeningHoursDto convertToDto(OpeningHours openingHours) {
//         return new OpeningHoursDto(openingHours.getDayOfWeek(), openingHours.getOpenTime().toString(), openingHours.getCloseTime().toString(), openingHours.getSchedule().getYear());
//     }
// }

package ca.mcgill.ecse321.scs.controller;

import ca.mcgill.ecse321.scs.dto.OpeningHoursDto;
import ca.mcgill.ecse321.scs.model.OpeningHours;
import ca.mcgill.ecse321.scs.service.OpeningHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/openingHours")
public class OpeningHoursController { //will need to add error checking and input sanitation later

    @Autowired
    private OpeningHoursService openingHoursService;

    @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
    public OpeningHoursDto createOpeningHours(@RequestBody OpeningHoursDto openingHoursDto) {
        OpeningHoursDto createdOpeningHours = new OpeningHoursDto(openingHoursService.createOpeningHours(
            openingHoursDto.getDayOfWeek().toString(),
            openingHoursDto.getOpenTime(),
            openingHoursDto.getCloseTime(),
            openingHoursDto.getYear()
        ));

        return createdOpeningHours;
        // return new OpeningHoursDto(openingHoursService.createOpeningHours(
        //         openingHoursDto.getDayOfWeek().toString(),
        //         LocalTime.parse(openingHoursDto.getOpenTime()),
        //         LocalTime.parse(openingHoursDto.getCloseTime()),
        //         openingHoursDto.getYear()
        // ));
    }

    @GetMapping("/{day}")
    public OpeningHoursDto getOpeningHoursByDay(@PathVariable String day) {
        OpeningHours openingHours = openingHoursService.getOpeningHoursByDay(day);
        OpeningHoursDto openingHoursDto = new OpeningHoursDto(openingHours);
        return openingHoursDto;
    }

    @PutMapping
    public OpeningHoursDto updateOpeningHours(@RequestBody OpeningHoursDto openingHoursDto) {
        OpeningHoursDto updatedOpeningHours = new OpeningHoursDto(openingHoursService.updateOpeningHours(
                LocalTime.parse(openingHoursDto.getOpenTime().toString()),
                openingHoursDto.getCloseTime(),
                openingHoursDto.getYear(),
                openingHoursDto.getDayOfWeek().toString()
        ));
        return updatedOpeningHours;
    }

    // @GetMapping
    // public List<OpeningHoursDto> getAllOpeningHours() {
    //     return openingHoursService.getAllOpeningHours().stream().map(OpeningHoursDto::new).collect(Collectors.toList());
    // }

    @DeleteMapping("/{day}")
    public void deleteOpeningHours(@PathVariable String day) {
        openingHoursService.deleteOpeningHours(day);
        // return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public void deleteAllOpeningHours() {
        openingHoursService.deleteAllOpeningHours();
        // return ResponseEntity.ok().build();
    }
}