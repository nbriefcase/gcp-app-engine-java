package com.personal.gcp.appengine.repository;

import com.personal.gcp.appengine.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByDescription(String description);
}
