package com.manager.tab.tabmanager.service;

import com.manager.tab.tabmanager.entity.Tab;
import com.manager.tab.tabmanager.repository.TabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TabServiceImpl implements TabService {
    @Autowired
    private TabRepository tabRepository;

    @Override
    public Tab saveTab(Tab tab){
        return tabRepository.save(tab);
    }

    @Override
    public List<Tab> fetchTabList(){
        return (List<Tab>)
                tabRepository.findAll();
    }

    @Override
    public Tab updateTab(Tab tab, Long tabId){
        Tab tabDB = tabRepository.findById(tabId).get();

        if (Objects.nonNull(tab.getTitle())
                && !"".equalsIgnoreCase(
                tab.getTitle())) {
            tabDB.setTitle(
                    tab.getTitle());
        }

        if (Objects.nonNull(
                tab.getArtist())
                && !"".equalsIgnoreCase(
                tab.getArtist())) {
            tabDB.setArtist(
                    tab.getArtist());
        }

        if (Objects.nonNull(tab.getTuning())
                && !"".equalsIgnoreCase(
                tab.getTuning())) {
            tabDB.setTuning(
                    tab.getTuning());
        }

        return tabRepository.save(tabDB);
    }

    @Override
    public void deleteTabById(Long tabId){
        tabRepository.deleteById(tabId);
    }
}
