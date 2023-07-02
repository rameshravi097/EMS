package com.example.ems;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	

	 @Query(value="SELECT * FROM employee e WHERE e.name LIKE %?1%"
	            + " OR e.email LIKE %?1%", nativeQuery = true)
	List<Employee> searchEmployee(String keyword);

}
