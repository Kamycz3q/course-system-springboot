package com.kamycz3q.coursesystemspringboot.personalData.repo;

import com.kamycz3q.coursesystemspringboot.personalData.personalDataObject.PersonalDataCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataCompanyRepository extends JpaRepository<PersonalDataCompany, Long> {
}
