package com.kamycz3q.coursesystemspringboot.guest.companyData;

import com.kamycz3q.coursesystemspringboot.guest.companyData.models.CompanyDataDTO;
import com.kamycz3q.coursesystemspringboot.guest.companyData.models.CreateCompanyDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyDataService {
    private final CompanyDataRepository companyDataRepository;

    @Autowired
    public CompanyDataService(CompanyDataRepository companyDataRepository) {
        this.companyDataRepository = companyDataRepository;
    }
    private CompanyDataDTO getCompanyDataDTO(CompanyData companyData) {
        return new CompanyDataDTO(
                companyData.getId(),
                companyData.getPersonalDataId(),
                companyData.getCompanyName(),
                companyData.getAddress(),
                companyData.getContactData(),
                companyData.getNip(),
                companyData.getRegon()
        );
    }
    private CompanyData setCompanyDataForObject(CompanyData companyData, CreateCompanyDataRequest req) {
        companyData.setCompanyName(req.companyName());
        companyData.setPersonalDataId(req.personalDataId());
        companyData.setCompanyName(req.companyName());
        companyData.setAddress(req.address());
        companyData.setContactData(req.contactData());
        companyData.setNip(req.nip());
        companyData.setRegon(req.regon());
        return companyData;
    }
    public CompanyDataDTO createCompanyData(CreateCompanyDataRequest req) {
        if (req.nip() == null & req.regon() == null) {
            return null;
        }
        CompanyData companyData = setCompanyDataForObject(new CompanyData(), req);
        return getCompanyDataDTO(companyDataRepository.save(companyData));
    }

    public CompanyDataDTO editCompanyData(Long id, CreateCompanyDataRequest req) {
        Optional<CompanyData> optionalPersonalDataCompany = companyDataRepository.findById(id);
        if (optionalPersonalDataCompany.isEmpty()) {
            return null;
        }
        CompanyData companyData = setCompanyDataForObject(optionalPersonalDataCompany.get(), req);
        return getCompanyDataDTO(companyDataRepository.save(companyData));
    }

    public void deleteCompanyData(Long id) {
        Optional<CompanyData> optionalPersonalDataCompany = companyDataRepository.findById(id);
        if (optionalPersonalDataCompany.isEmpty()) {
            return;
        }
        companyDataRepository.deleteById(id);
    }

    public List<CompanyData> getCompanyDataList() {
        return companyDataRepository.findAll();
    }

    public CompanyDataDTO getCompanyData(Long id) {
        Optional<CompanyData> optionalPersonalDataCompany = companyDataRepository.findById(id);
        return getCompanyDataDTO(optionalPersonalDataCompany.orElse(null));
    }

}
