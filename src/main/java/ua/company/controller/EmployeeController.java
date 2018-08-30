package ua.company.controller;

import com.google.common.io.ByteStreams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.company.config.AppConfig;
import ua.company.entity.Department;
import ua.company.entity.Employee;
import ua.company.service.DepartmentService;
import ua.company.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class EmployeeController {
    private static final Logger logger = Logger.getLogger(AppConfig.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @ModelAttribute("departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAll();
    }

    @GetMapping("/")
    public String showStartPage(Model model) {
        model.addAttribute("employees", employeeService.getPageList(0));
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @GetMapping("/showPageNumber")
    public String showPage(@RequestParam("pageNumber") int pageNumber, Model model) {
        model.addAttribute("employees", employeeService.getPageList(pageNumber));
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "index";
        }
        employeeService.save(employee);
        return "redirect:/";
    }

    @PostMapping("/upload")
    public String saveImage(HttpServletRequest request) {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        byte[] imageByteArray = getFileBytes(request, employeeId) ;
        Employee employee = employeeService.get(employeeId);
        employee.setImage(imageByteArray);
        employeeService.save(employee);
        return "redirect:/";
    }

    private byte[] getFileBytes(HttpServletRequest request, int employeeId) {
        Part file = null;
        InputStream stream= null;
        byte[] result = null;
        try {
            file = request.getPart("file");
            stream = file.getInputStream();
            result = ByteStreams.toByteArray(stream);
        } catch (IOException | ServletException e) {
            logger.warn("Exception during saving file for employeeId - " + employeeId);
        }
        return result;
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        employeeService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model) {
        Employee employee = employeeService.get(id);
        model.addAttribute("employee", employee);
        model.addAttribute("employees", employeeService.getPageList(0));
        return "index";
    }

    @GetMapping("/showEmployeeInfo")
    public String showEmployee(@RequestParam("employeeId") int id, Model model) {
        Employee employee = employeeService.get(id);
        model.addAttribute("employee", employee);
        model.addAttribute("employees", employeeService.getPageList(0));
        model.addAttribute("disableFields", true);
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("searchText") String searchText, Model model) {
        List<Employee> employees = employeeService.searchEmployeesByName(searchText);
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        return "index";
    }
}
