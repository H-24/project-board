package org.example.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/boards")
@Controller
public class BoardsController {

    @GetMapping
    public String boards(ModelMap model) {
        model.addAttribute("boards", List.of());
        return "boards/index";
    }
}
