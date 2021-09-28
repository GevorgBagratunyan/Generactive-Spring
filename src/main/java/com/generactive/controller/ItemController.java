package com.generactive.controller;

import com.generactive.model.Item;
import com.generactive.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/{id}")
    public Item get(@PathVariable long id) {
            return itemService.read(id)
                    .orElseThrow(NoSuchElementException::new);
    }

    @GetMapping
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @PostMapping
    public Item save(@RequestBody Item item) {
        return itemService.create(item);
    }

    @PutMapping
    public Item update(@RequestBody Item item) {
        return itemService.update(item)
                .orElseThrow(IllegalArgumentException::new);
    }

    @DeleteMapping
    public Item delete(@PathVariable long id) {
        return itemService.delete(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @PutMapping("/{itemId}/{groupId}")
    public Item setGroup(@PathVariable long itemId,
                         @PathVariable long groupId) {
        return itemService.setGroup(itemId, groupId)
                .orElseThrow(IllegalArgumentException::new);
    }

    @GetMapping("/{from}/{to}")
    public List<Item> allByPriceRange(@PathVariable double from,
                                      @PathVariable double to) {
        return itemService.allByPriceRange(from, to);
    }

    @GetMapping("/item/{name}")
    public Item getByName(@PathVariable String name) {
        return itemService.getByName(name)
                .orElseThrow(NoSuchElementException::new);
    }
}
