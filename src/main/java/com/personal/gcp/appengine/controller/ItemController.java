package com.personal.gcp.appengine.controller;

import com.personal.gcp.appengine.entities.Item;
import com.personal.gcp.appengine.exception.ItemIdMismatchException;
import com.personal.gcp.appengine.exception.ItemNotFoundException;
import com.personal.gcp.appengine.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public Iterable<Item> findAll() {
        return itemRepository.findAll();
    }

    @GetMapping("/title/{itemTitle}")
    public List<Item> findByTitle(@PathVariable String description) {
        return itemRepository.findByDescription(description);
    }

    @GetMapping("/{id}")
    public Item findOne(@PathVariable long id) {
        return itemRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody Item item) {
        Item item1 = itemRepository.save(item);
        return item1;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        itemRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);
        itemRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Item updateItem(@RequestBody Item item, @PathVariable long id) {
        if (item.getId() != id) {
            throw new ItemIdMismatchException();
        }
        itemRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);
        return itemRepository.save(item);
    }

}
