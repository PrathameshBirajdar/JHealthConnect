package com.p.jhealthconnect.jhealthconnect.Controller;

import com.p.jhealthconnect.jhealthconnect.model.Insurance;
import com.p.jhealthconnect.jhealthconnect.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {
    
    @Autowired
    private InsuranceService insuranceService;
    
    @GetMapping
    public String showInsuranceForm(Model model) {
        model.addAttribute("insurance", new Insurance());
        return "Insurance";
    }
    
    @PostMapping
    public String submitInsurance(@Valid @ModelAttribute("insurance") Insurance insurance,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "Insurance";
        }
        
        try {
            // Check if policy number is unique
            if (!insuranceService.isPolicyNumberUnique(insurance.getPolicyNumber())) {
                model.addAttribute("error", "Policy number already exists. Please check your policy number.");
                return "Insurance";
            }
            
            insuranceService.saveInsurance(insurance);
            redirectAttributes.addFlashAttribute("success", 
                "Insurance details submitted successfully! Our admin team will verify your policy.");
            return "redirect:/insurance/success";
            
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while submitting insurance details. Please try again.");
            return "Insurance";
        }
    }
    
    @GetMapping("/success")
    public String insuranceSuccess() {
        return "insurance-success";
    }
    
    @GetMapping("/list")
    public String listAllInsurancePolicies(Model model) {
        List<Insurance> insurancePolicies = insuranceService.getAllInsurancePolicies();
        model.addAttribute("insurancePolicies", insurancePolicies);
        return "insurance-list";
    }
    
    @GetMapping("/search")
    public String searchInsurance(@RequestParam(required = false) String policyNumber,
                                @RequestParam(required = false) String fullName,
                                @RequestParam(required = false) String provider,
                                @RequestParam(required = false) String status,
                                Model model) {
        List<Insurance> insurancePolicies;
        
        if (policyNumber != null && !policyNumber.isEmpty()) {
            Optional<Insurance> insurance = insuranceService.getInsuranceByPolicyNumber(policyNumber);
            insurancePolicies = insurance.map(List::of).orElse(List.of());
        } else if (fullName != null && !fullName.isEmpty()) {
            insurancePolicies = insuranceService.searchByName(fullName);
        } else if (provider != null && !provider.isEmpty()) {
            insurancePolicies = insuranceService.getInsuranceByProvider(provider);
        } else if (status != null && !status.isEmpty()) {
            Insurance.VerificationStatus verificationStatus = Insurance.VerificationStatus.valueOf(status);
            insurancePolicies = insuranceService.getInsuranceByStatus(verificationStatus);
        } else {
            insurancePolicies = insuranceService.getAllInsurancePolicies();
        }
        
        model.addAttribute("insurancePolicies", insurancePolicies);
        return "insurance-list";
    }
    
    @PostMapping("/verify/{id}")
    public String verifyInsurance(@PathVariable Long id,
                                @RequestParam String status,
                                RedirectAttributes redirectAttributes) {
        try {
            Optional<Insurance> insuranceOpt = insuranceService.getInsuranceById(id);
            if (insuranceOpt.isPresent()) {
                Insurance insurance = insuranceOpt.get();
                insurance.setVerificationStatus(Insurance.VerificationStatus.valueOf(status));
                insuranceService.saveInsurance(insurance);
                redirectAttributes.addFlashAttribute("success", "Insurance verification status updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Insurance policy not found!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating verification status!");
        }
        return "redirect:/insurance/list";
    }
    
    @GetMapping("/check/{policyNumber}")
    @ResponseBody
    public String checkPolicyNumber(@PathVariable String policyNumber) {
        boolean isUnique = insuranceService.isPolicyNumberUnique(policyNumber);
        return isUnique ? "available" : "taken";
    }
}