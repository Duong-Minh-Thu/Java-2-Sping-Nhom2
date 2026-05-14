package com.night.frontend_.controller;

import com.night.frontend_.model.Volunteer;
import com.night.frontend_.service.VolunteerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/volunteers")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping
    public String listVolunteers(Model model) {
        model.addAttribute("volunteers", volunteerService.getAllVolunteers());
        return "volunteers/list"; // Maps to src/main/resources/templates/volunteers/list.html
    }

    @GetMapping("/{id}")
    public String viewVolunteer(@PathVariable Long id, Model model) {
        model.addAttribute("volunteer", volunteerService.getVolunteerById(id));
        return "volunteers/view";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        return "volunteers/form";
    }

    @PostMapping("/create")
    public String createSubmit(@ModelAttribute Volunteer volunteer) {
        volunteerService.createVolunteer(volunteer);
        return "redirect:/volunteers";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("volunteer", volunteerService.getVolunteerById(id));
        return "volunteers/form";
    }

    @PostMapping("/{id}/edit")
    public String editSubmit(@PathVariable Long id, @ModelAttribute Volunteer volunteer) {
        volunteerService.updateVolunteer(id, volunteer);
        return "redirect:/volunteers";
    }

    @GetMapping("/{id}/delete")
    public String deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return "redirect:/volunteers";
    }
}
