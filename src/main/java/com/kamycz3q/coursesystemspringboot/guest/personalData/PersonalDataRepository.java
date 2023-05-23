package com.kamycz3q.coursesystemspringboot.guest.personalData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
    PersonalData findPersonalDataByPesel(String i);
}
