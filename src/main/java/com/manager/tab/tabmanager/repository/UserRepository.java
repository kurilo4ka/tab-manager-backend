package com.manager.tab.tabmanager.repository;

import com.manager.tab.tabmanager.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
