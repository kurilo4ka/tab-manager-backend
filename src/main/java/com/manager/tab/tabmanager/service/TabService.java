package com.manager.tab.tabmanager.service;

import com.manager.tab.tabmanager.entity.Tab;

import java.util.List;

public interface TabService {
    Tab saveTab(Tab tab);

    List<Tab> fetchTabList();

    Tab updateTab(Tab tab, Long tabId);

    void deleteTabById(Long tabId);
}
