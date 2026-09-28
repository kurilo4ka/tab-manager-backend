package com.manager.tab.tabmanager.controller;

import com.manager.tab.tabmanager.entity.Tab;
import com.manager.tab.tabmanager.service.TabService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TabController {
    @Autowired
    private TabService tabService;

    @PostMapping("/tabs")

    public Tab saveTab(
            @Valid @RequestBody Tab tab)
    {
        return tabService.saveTab(tab);
    }

    // Read operation
    @GetMapping("/tabs")

    public List<Tab> fetchTabList()
    {
        return tabService.fetchTabList();
    }

    // Update operation
    @PutMapping("/tabs/{id}")

    public Tab
    updateTab(@RequestBody Tab tab,
                     @PathVariable("id") Long tabId)
    {
        return tabService.updateTab(
                tab, tabId);
    }

    // Delete operation
    @DeleteMapping("/tabs/{id}")

    public String deleteTabById(@PathVariable("id")
                                       Long tabId)
    {
        tabService.deleteTabById(
                tabId);
        return "Deleted Successfully";
    }
}
