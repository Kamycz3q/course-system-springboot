package com.kamycz3q.coursesystemspringboot.customer.personalData;

import com.kamycz3q.coursesystemspringboot.customer.companyData.CompanyData;
import com.kamycz3q.coursesystemspringboot.customer.companyData.CompanyDataRepository;
import com.kamycz3q.coursesystemspringboot.customer.companyData.models.CreateCompanyDataRequest;
import com.kamycz3q.coursesystemspringboot.customer.personalData.models.CreatePersonalRequest;
import com.kamycz3q.coursesystemspringboot.customer.personalData.models.PersonalDataDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PersonalDataService {
    private final PersonalDataRepository personalDataRepository;
    private final CompanyDataRepository companyDataRepository;
    private final List<Integer> regonWeights9 = new ArrayList<>(List.of(8,9,2,3,4,5,6,7));
    private final List<Integer> regonWeights14 = new ArrayList<>(List.of(2,4,8,5,0,9,7,3,6,1,2,4,8));

    @Autowired
    public PersonalDataService(PersonalDataRepository personalData, CompanyDataRepository companyDataRepository) {
        this.personalDataRepository = personalData;
        this.companyDataRepository = companyDataRepository;
    }

    private boolean validateCompanyDataNIP(@NonNull String nip) {
        //nip sprwadzanie
        int sum = 0;
        if (nip.length() >= 8 && nip.length() <= 11) {
            return true;
        }
        //sprawdzanie regon

        else if (nip.length() == 9) {
            for (int i = 0; i < 9;i++) {
                int number = Integer.parseInt(String.valueOf(String.valueOf(nip).charAt(i)));
                int weight = regonWeights9.get(i);
                sum += number * weight;
            }
            if (sum % 11 != 10) {
                return true;
            }
        } else if (nip.length() == 14) {
            for (int i = 0; i < 14;i++) {
                int number = Integer.parseInt(String.valueOf(String.valueOf(nip).charAt(i)));
                int weight = regonWeights9.get(i);
                sum += number * weight;
            }
            if (sum % 11 != 10) {
                return true;
            }
        }

        return false;
    }

    private boolean checkPesel(String pesel) {
        if (pesel.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(pesel);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
    private PersonalData setPersonalDataForObject(PersonalData personalData, CreatePersonalRequest req) {
        if (!checkPesel(req.pesel())) {
            return null;
        }
        personalData.setName(req.name());
        personalData.setSurname(req.surname());
        personalData.setEmail(req.email());
        personalData.setBirthDate(new Date(req.birthDateTimestamp()));
        personalData.setCity(req.city());
        personalData.setAddress(req.address());
        personalData.setPostCode(req.postcode());
        personalData.setPesel(req.pesel());
        if (req.companyData() != null) {
            CompanyData companyData = new CompanyData();
            CreateCompanyDataRequest data_companyData = req.companyData();
            if (validateCompanyDataNIP(data_companyData.nip())) {
                companyData.setCompanyName(data_companyData.companyName());
                companyData.setNip(data_companyData.nip());
                companyData.setAddress(data_companyData.address());
                companyData.setCity(data_companyData.city());
                companyData.setAddress(data_companyData.address());
                companyData.setPostCode(data_companyData.postcode());
                companyData = companyDataRepository.save(companyData);
                personalData.setCompanyId(companyData.getId());
            } else {
                return null;
            }

        }
        return personalData;
    }
    @SuppressWarnings("UnnecessaryLocalVariable")
    private PersonalDataDTO getPersonalDataDTO(Long id) {
        Optional<PersonalData> optionalPersonalData = personalDataRepository.findById(id);
        if (optionalPersonalData.isEmpty()) {
            return null;
        }
        PersonalData personalData = optionalPersonalData.get();
        CompanyData companyData = null;
        if (personalData.getCompanyId() != null) {
            Optional<CompanyData> optionalCompanyData = companyDataRepository.findById(personalData.getCompanyId());
            if (optionalPersonalData.isPresent()) {
                companyData = optionalCompanyData.get();
            }
        }
        PersonalDataDTO personalDataDTO = new PersonalDataDTO(
                personalData.getName(),
                personalData.getSurname(),
                personalData.getEmail(),
                personalData.getCity(),
                personalData.getAddress(),
                personalData.getPostCode(),
                companyData
        );
        return personalDataDTO;

    }
    public PersonalDataDTO createPersonalData(CreatePersonalRequest req) {
        PersonalData personalData = new PersonalData();
        if (setPersonalDataForObject(personalData, req) == null) {
            return null;
        }
        return  getPersonalDataDTO( personalDataRepository.save(personalData).getId());
    }

    public PersonalDataDTO editPersonalData(long id, CreatePersonalRequest req) {
        Optional<PersonalData> personalDataOptional = personalDataRepository.findById(id);
        if (personalDataOptional.isEmpty()) {
            return null;
        }
        PersonalData personalData = setPersonalDataForObject(personalDataOptional.get(), req);
        personalDataRepository.save(personalData);
        return getPersonalDataDTO(personalData.getId());
    }

    public PersonalDataDTO getPersonalData(Long id) {
        return getPersonalDataDTO(id);
    }
    public List<PersonalDataDTO> getPersonalDataList() {
        List<PersonalDataDTO> personalDataDTOList = new ArrayList<>();
        for(PersonalData personalData : personalDataRepository.findAll()) {
            personalDataDTOList.add(getPersonalDataDTO(personalData.getId()));
        }
        return personalDataDTOList;
    }

    public void deletePersonalData(Long id) {
        Optional<PersonalData> personalDataOptional = personalDataRepository.findById(id);
        if (personalDataOptional.isEmpty()) {
            return;
        }
        companyDataRepository.deleteById(personalDataOptional.get().getCompanyId());
        personalDataRepository.deleteById(id);
    }




}
