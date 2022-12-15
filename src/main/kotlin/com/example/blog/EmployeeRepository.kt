package com.example.blog

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long>
{
    abstract fun getEmployeeByName(name: String): Employee?
    abstract fun deleteByName(name: String): List<Employee>
    abstract fun getStudentById(id: Long): Employee?
}