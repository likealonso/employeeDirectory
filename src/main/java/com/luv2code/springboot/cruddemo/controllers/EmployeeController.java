package com.luv2code.springboot.cruddemo.controllers;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository emp;

    @GetMapping("/allEmployees")
    public String showEmployees(Model model) {
        model.addAttribute("allEmployees", emp.findAllByOrderByLastNameAsc());
        return "employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model){
        Optional<Employee> empl = emp.findById(id);
        model.addAttribute("employee", empl);
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        Employee empl = emp.saveAndFlush(employee);
        return "redirect:/allEmployees";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id){
        emp.deleteById(id);
        return "redirect:/allEmployees";
    }
}
