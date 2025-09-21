package com.jhealthconnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhealthconnect.entity.InsuranceCompany;
import com.jhealthconnect.service.InsuranceService;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping
    public String listInsurance(Model model) {
        model.addAttribute("insuranceList", insuranceService.getAllCompanies());
        return "insurance";
    }

    @GetMapping("/{id}")
    public String insuranceDetails(@PathVariable Long id, Model model) {
        InsuranceCompany company = insuranceService.findById(id).orElse(null);
        model.addAttribute("insurance", company);
        return "insurance_details";
    }

    @GetMapping("/add")
    public String addInsuranceForm(Model model) {
        model.addAttribute("insurance", new InsuranceCompany());
        return "insurance_form";
    }

    @PostMapping("/save")
    public String saveInsurance(@ModelAttribute InsuranceCompany insurance) {
        insuranceService.saveCompany(insurance);
        return "redirect:/insurance";
    }

    @GetMapping("/delete/{id}")
    public String deleteInsurance(@PathVariable Long id) {
        insuranceService.deleteCompany(id);
        return "redirect:/insurance";
    }
}
