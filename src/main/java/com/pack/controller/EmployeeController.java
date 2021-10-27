package com.pack.controller;

 

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 

import com.pack.model.Employee;
import com.pack.service.EmployeeService;

 

@Controller
public class EmployeeController {
	Logger logger=Logger.getLogger(EmployeeController.class);
    
    @Autowired
    private EmployeeService employeeService;
    
    @RequestMapping(value="/")
    public String employeeRegistrationPage(ModelMap map) {
    
        Employee employee=new Employee();
        map.addAttribute("employeeForm", employee);
        return "EmployeeRegister";
    }
    @RequestMapping(value="/insertEmp",method=RequestMethod.POST)
    public String saveEmployee(@Validated @ModelAttribute("employeeForm")Employee empl,
        BindingResult result,ModelMap map) {

    String response="";
    if(result.hasErrors()) {
        response="EmployeeRegister";
    }
    else {
        Random r=new Random();
        int empid=r.nextInt(99999)+10000;
        empl.setEmployeeid(empid);
        employeeService.insertEmployee(empl);
        List<Employee> empList=employeeService.fetchAllEmployee();
        map.addAttribute("employeeList",empList);
        response="EmployeeSuccess";
    }
    return response;
}    
     @RequestMapping(value="/deleteEmp/{id}")
     public String deleteEmployee(@PathVariable("id") Integer empId,ModelMap map) {
     	logger.info("inside delete emp");

    	 employeeService.deleteEmployee(empId);
    	 List<Employee> empList=employeeService.fetchAllEmployee();
         map.addAttribute("employeeList",empList);
    	 return "EmployeeSuccess";
    	 
     }
     @RequestMapping(value="/fetchById/{eid}")
     public String fetchEmployeeById(@PathVariable("eid")Integer empId,ModelMap map) {
     	logger.info("inside fetching emp");

    	 Employee employee=employeeService.fetchEmployeeById(empId);
    	 map.addAttribute("editForm",employee);
		return "EmployeeUpdate";
    	 
     }
     @RequestMapping(value="/updateEmp", method=RequestMethod.POST)
     public String updateEmployee(@Validated @ModelAttribute("editForm")Employee emp, BindingResult result,ModelMap map) {
     	logger.info("inside update emp");

    	 String response="";
    	 if(result.hasErrors()) {
    		 response="EmployeeUpdate";
    		 
    	 }
    	 else {
    		 employeeService.updateEmployee(emp);
    		 List<Employee> empList=employeeService.fetchAllEmployee();
             map.addAttribute("employeeList",empList);
        	 return "EmployeeSuccess";
    		 
    	 }
    	 return response;
    	 
		
    	 
     }
}