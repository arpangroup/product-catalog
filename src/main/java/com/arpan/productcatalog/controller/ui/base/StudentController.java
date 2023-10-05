package com.arpan.productcatalog.controller.ui.base;

import com.arpan.productcatalog.controller.ui.base.BaseCrudController;
import com.arpan.productcatalog.entity.Student;
import com.arpan.productcatalog.repository.StudentRepository;
import com.arpan.productcatalog.util.WebUriConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RestController
@RequestMapping("/students")
public class StudentController extends BaseCrudController<Student, StudentRepository> {
    protected StudentController(StudentRepository repository) {
        super(repository);
    }

    @ModelAttribute("uriMap")
    public WebUriConstants getUrls() {
        return new WebUriConstants();
    }

    @GetMapping("/home")
    public String home() {
        return "Hel;lo World: " + getModelName();
    }

}
