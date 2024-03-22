package daelim.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LearningController {

    @GetMapping("/writing")
    public String write(){
        return "write";
    }
}
