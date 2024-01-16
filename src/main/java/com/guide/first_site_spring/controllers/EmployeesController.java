package com.guide.first_site_spring.controllers;

import com.guide.first_site_spring.models.Employee;
import com.guide.first_site_spring.models.Position;
import com.guide.first_site_spring.repo.EmployeeRepository;
import com.guide.first_site_spring.repo.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class EmployeesController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PositionRepository positionRepository;
    @GetMapping("/")
    public String employees(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("title", "Сотрудники");
        return "employees/employees";
    }
    @GetMapping("/add")
    public String employees_add_get(Model model) {
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("positions", positions);
        return "employees/emp_add";
    }
    @PostMapping("/add")
    public String employees_add_post(@RequestParam String firstName, @RequestParam String secondName, @RequestParam String middleName, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime job_date, @RequestParam Long position_id, Model model) {
        Position position = positionRepository.findById(position_id).orElseThrow();
        Employee employee = new Employee(firstName, secondName, middleName, job_date, position);
        employeeRepository.save(employee);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String employees_edit_get(@PathVariable(value = "id") long id, Model model){
        if(!employeeRepository.existsById(id)){
            return "redirect:/";
        }
        Iterable<Position> positions = positionRepository.findAll();
        Optional<Employee> emp = employeeRepository.findById(id);
        ArrayList<Employee> res = new ArrayList<>();
        emp.ifPresent(res::add);
        model.addAttribute("positions", positions);
        model.addAttribute("employees", res);
        return "employees/emp_edit";
    }
    @PostMapping("/edit/{id}")
    public String employees_edit_post(@PathVariable(value = "id") long id, @RequestParam String firstName, @RequestParam String secondName, @RequestParam String middleName, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime job_date, @RequestParam Long position_id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setPosition(positionRepository.findById(position_id).orElseThrow());
        employee.setFirstName(firstName);
        employee.setSecondName(secondName);
        employee.setMiddleName(middleName);
        employee.setJob_date(job_date);
        return "redirect:/";
    }
    @PostMapping("/delete/{id}")
    public String employees_delete_post(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/";
    }
}
