package com.kamycz3q.coursesystemspringboot.customer.companyData;


import com.kamycz3q.coursesystemspringboot.customer.companyData.models.CompanyDataDTO;
import com.kamycz3q.coursesystemspringboot.customer.companyData.models.CreateCompanyDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyDataController {
    private final CompanyDataService companyDataService;

    @Autowired
    public CompanyDataController(CompanyDataService companyDataService) {
        this.companyDataService = companyDataService;
    }


    @GetMapping("")
    public List<CompanyData> listPersonalDataCompany() {
        return companyDataService.getCompanyDataList();
    }

    @GetMapping("/{id}")
    public CompanyDataDTO getPersonalDataCompany(@PathVariable("id") String id) {
        return companyDataService.getCompanyData(Long.parseLong(id));
    }

    @PostMapping("")
    public CompanyDataDTO createPersonalDataForCompany(@NonNull @RequestBody CreateCompanyDataRequest req) {
        return companyDataService.createCompanyData(req);
    }

    @PatchMapping("/{id}")
    public CompanyDataDTO createPersonalDataForCompany(@PathVariable("id") String id, @NonNull @RequestBody CreateCompanyDataRequest req) {
        return companyDataService.editCompanyData(Long.parseLong(id), req);
    }


    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") String id) {
        companyDataService.deleteCompanyData(Long.parseLong(id));
    }

}
