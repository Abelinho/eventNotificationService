package com.abel.eventbookingservice.repos;

import com.abel.eventbookingservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {


    Role findByName(String name);
}
