package daelim.learning.controller;

import daelim.learning.Entity.WritingTbl;
import daelim.learning.service.LearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LearningController {

    @Autowired
    private final LearningService learningService;

    public LearningController(LearningService learningService) {
        this.learningService = learningService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list",learningService.writingTblList());
//        System.out.println(model.getAttribute("list"));
        return "index";
    }

    @GetMapping("/writing")
    public String write(){
        return "write";
    }

    @PostMapping("/writing/writepro")
    public String writePro(WritingTbl writingTbl){

        learningService.write(writingTbl);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,@PathVariable(name = "id") Integer id){
        model.addAttribute("list",learningService.writingView(id));
        System.out.println(id);
        return "detail";
    }
}
