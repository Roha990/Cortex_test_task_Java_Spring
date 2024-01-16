package com.guide.first_site_spring.repo;

import com.guide.first_site_spring.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
