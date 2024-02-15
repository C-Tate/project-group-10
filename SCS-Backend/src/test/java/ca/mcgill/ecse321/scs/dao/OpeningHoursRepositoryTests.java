package ca.mcgill.ecse321.scs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.scs.model.OpeningHours;
import ca.mcgill.ecse321.scs.model.OpeningHours.DayOfWeek;

@SpringBootTest
public class OpeningHoursRepositoryTests {
    @Autowired
    private OpeningHoursRepository openingHoursRepository;

    @BeforeEach
    @AfterEach
	public void clearDatabase() {
		openingHoursRepository.deleteAll();
	}

    @Test
    public void testPersistAndLoadOpeningHours() {
        // create opening hours
        DayOfWeek dayOfWeek = DayOfWeek.TUESDAY;
        Time openTime = Time.valueOf("08:30:00");
        Time closeTime = Time.valueOf("18:00:00");

        OpeningHours openingHours = new OpeningHours();
        openingHours.setDayOfWeek(dayOfWeek);
        openingHours.setOpenTime(openTime);
        openingHours.setCloseTime(closeTime);

        // save opening hours
        openingHoursRepository.save(openingHours);

        // read opening hours from db
        openingHours = openingHoursRepository.findOpeningHoursByDayOfWeek(dayOfWeek);

        assertNotNull(openingHours);
        assertEquals(dayOfWeek, openingHours.getDayOfWeek());
        assertEquals(openTime, openingHours.getOpenTime());
        assertEquals(closeTime, openingHours.getCloseTime());
    }
}
