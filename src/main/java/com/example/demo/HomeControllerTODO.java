package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeControllerTODO {
    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String listTask(Model model){
        model.addAttribute("tasks", todoRepository.findAll());
        return "tasklist";
    }
    @GetMapping("/add")
    public String courseForm(Model model){
        model.addAttribute("todo",new Todo());
        return "taskform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Todo todo, BindingResult result ){
        if (result.hasErrors()){
            return "taskform";
        }
        todoRepository.save(todo);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showTask(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateTask(@PathVariable("id") long id, Model model){
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "taskform";
    }
    @RequestMapping("/delete/{id}")
    public String delTask(@PathVariable("id") long id){
        todoRepository.deleteById(id);
        return "redirect:/";

    }
}
