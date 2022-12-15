package com.example.blog

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.swing.text.html.parser.Entity

@RestController
@RequestMapping("/api/v1")
class EmployeeController
{
    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    //create get all employee api
    @GetMapping("/employees")
    fun getAllEmployees(): List<Employee>
    {
        return employeeRepository.findAll()
    }

    //create employee
    @PostMapping("/employees")
    fun createEmployee(@Validated @RequestBody employee: Employee): Employee
    {
        return employeeRepository.save(employee)
    }

    //read employeee by id
    @GetMapping("/employees/{id}")
    fun retrieveEmployeeById(@PathVariable id: Long): Employee?
    {
        return employeeRepository.getStudentById(id)
    }

    //update
    @PutMapping("/employees/{id}")
    fun updateEmployeeById(@PathVariable(value = "id") id: Long,
                          @Validated @RequestBody employee: Employee): ResponseEntity<Employee>
    {
        return employeeRepository.findById(id).map { existingEmployee ->
            val updatedEmployee: Employee = existingEmployee.copy(name = employee.name)
            ResponseEntity.ok().body(employeeRepository.save(updatedEmployee))
        }.orElse(ResponseEntity.notFound().build())
    }

    //delete
    @DeleteMapping("employees/{id}")
    fun deleteById(@PathVariable(value = "id") id: Long): ResponseEntity<Void>
    {
        return employeeRepository.findById(id).map {
            employeeRepository.delete(it)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}


