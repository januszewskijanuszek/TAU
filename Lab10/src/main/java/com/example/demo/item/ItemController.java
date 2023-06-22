package com.example.demo.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // View
    @RequestMapping(value = "/avBooks")
    public String avBooks(Model model){
        model.addAttribute("avBooks", itemService.getAvaliableItems(false));
        return "avBooks";
    }

    @RequestMapping(value = "/unBooks")
    public String unBooks(Model model){
        model.addAttribute("unBooks", itemService.getAvaliableItems(true));
        return "unBooks";
    }

    // Add
    @RequestMapping(value = "/addBooks", method = RequestMethod.GET)
    public String addBooks(Model model){
        model.addAttribute("item", new Item());
        return "addBooks";
    }

    @RequestMapping( value = "/addBooks", method = RequestMethod.POST)
    public String submitItem(@ModelAttribute Item item, Model model){

        model.addAttribute("name", item.getName());
        model.addAttribute("author", item.getAuthor());

        itemService.createItem(item);
        return "redirect:/avBooks";
    }

    // Delete
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteBook(Model model, @PathVariable Long id){
        itemService.deleteItem(id);
        return "redirect:/avBooks";
    }

    // Toggle Taken
    @RequestMapping(value = "take/{id}", method = RequestMethod.GET)
    public String toggleBook(Model model, @PathVariable Long id){
        itemService.toggleTaken(id);
        Item item = itemService.getById(id);
        String text = "";
        if(item.isTaken()){
            return "redirect:/avBooks";
        } else return "redirect:/unBooks";
    }
}
