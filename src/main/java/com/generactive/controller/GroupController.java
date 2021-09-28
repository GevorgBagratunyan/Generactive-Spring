package com.generactive.controller;

import com.generactive.model.Group;
import com.generactive.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{id}")
    public Group get(@PathVariable long id) {
        return groupService.read(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @GetMapping
    public List<Group> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/group/{name}")
    public Group getByName(@PathVariable String name) {
        return groupService.getByName(name)
                .orElseThrow(NoSuchElementException::new);
    }

    @PostMapping
    public Group save(@RequestBody Group group) {
        return groupService.create(group);
    }

    @PutMapping
    public Group update(@RequestBody Group group) {
        return groupService.update(group)
                .orElseThrow(IllegalArgumentException::new);
    }

    @DeleteMapping("/{id}")
    public Group delete(@PathVariable long id) {
        return groupService.delete(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @PutMapping("/{groupId}/{parentId}")
    public Group setParent(@PathVariable long groupId,
                           @PathVariable long parentId) {
        return groupService.setParent(groupId, parentId)
                .orElseThrow(NoSuchElementException::new);
    }

}
