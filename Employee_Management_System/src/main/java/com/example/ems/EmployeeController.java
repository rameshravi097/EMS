package com.example.ems;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@CrossOrigin("http://localhost:3000")
public class EmployeeController {

	@Autowired
    private EmployeeServiceImpl employeeServiceImpl;
 
    @GetMapping(path={"/","/search"})
    public String viewHomePage(Model model,String keyword ) {
    	
    	if(keyword != null) {
    		List<Employee> employee=employeeServiceImpl.getByKeyword(keyword);
        	if(employee.isEmpty()) {
        		model.addAttribute("emp","true");
        		model.addAttribute("NoEmp","There Is No Employee !!");       		
        	}else {
        		model.addAttribute("emplist", employee);        		
        	}
        
    	}else {
    		model.addAttribute("emplist", employeeServiceImpl.getAllEmployee());
    	}
		return "index";
    }
 
    @GetMapping("/addnew")
    public String addNewEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "newemployee";
    }
 
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeServiceImpl.save(employee);
        return "redirect:/";
    }
 
    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeServiceImpl.getById(id);
        model.addAttribute("employee", employee);
        return "update";
    }
 
    @GetMapping("/deleteEmployee/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        employeeServiceImpl.deleteViaId(id);
        return "redirect:/";
 
    }
    
//    @GetMapping("/search")
//    public String searchEmployee(@RequestParam("keyword") String keyword, Model model) {
//    	List<Employee> employee=employeeServiceImpl.getByKeyword(keyword);
//    	model.addAttribute("allemplist", employee);
//    	return "index";
//    	
//    }
}
