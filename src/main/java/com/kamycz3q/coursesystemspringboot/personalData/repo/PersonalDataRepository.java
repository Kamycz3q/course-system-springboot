package com.kamycz3q.coursesystemspringboot.personalData.repo;

import com.kamycz3q.coursesystemspringboot.personalData.personalDataObject.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
}
