package com.manager.tab.tabmanager.repository;

import com.manager.tab.tabmanager.entity.Tab;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabRepository extends CrudRepository <Tab, Long> {

}
