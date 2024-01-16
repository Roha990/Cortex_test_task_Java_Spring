package com.guide.first_site_spring.controllers;

import com.guide.first_site_spring.models.Position;
import com.guide.first_site_spring.repo.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PositionsController {
    @Autowired
    private PositionRepository positionRepository;
    @GetMapping("/positions")
    public String positions(Model model) {
        Iterable<Position> positions = positionRepository.findAll();
        model.addAttribute("positions", positions);
        model.addAttribute("title", "Должности");
        return "positions/positions";
    }
    @GetMapping("/positions/add")
    public String positions_add_get(Model model) {
        return "positions/pos_add";
    }
    @PostMapping("/positions/add")
    public String positions_add_post(@RequestParam String position_name, Model model) {
        Position position = new Position(position_name);
        positionRepository.save(position);
        return "redirect:/positions";
    }
    @GetMapping("/positions/edit/{id}")
    public String positions_edit_get(@PathVariable(value = "id") long id, Model model){
        if(!positionRepository.existsById(id)){
            return "redirect:/positions";
        }
        Optional<Position> position = positionRepository.findById(id);
        ArrayList<Position> res = new ArrayList<>();
        position.ifPresent(res::add);
        model.addAttribute("positions", res);
        return "positions/pos_edit";
    }
    @PostMapping("/positions/edit/{id}")
    public String positions_edit_post(@PathVariable(value = "id") long id, @RequestParam String position_name, Model model) {
        Position position = positionRepository.findById(id).orElseThrow();
        position.setName(position_name);
        positionRepository.save(position);
        return "redirect:/positions";
    }
    @PostMapping("/positions/delete/{id}")
    public String positions_delete_post(@PathVariable(value = "id") long id, Model model) {
        Position position = positionRepository.findById(id).orElseThrow();
        positionRepository.delete(position);
        return "redirect:/positions";
    }
}
