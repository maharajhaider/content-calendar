package com.maharaj.content_calendar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.maharaj.content_calendar.repository.ContentCollectionRepository;

import com.maharaj.content_calendar.model.Content;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/api/content")
public class ContentController {
    private ContentCollectionRepository contentRepo;


    public ContentController(ContentCollectionRepository contentCollectionRepository) {
        this.contentRepo = contentCollectionRepository;

    }


    @GetMapping("")
    public List<Content> findAll() {
        return contentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Content getContentByID(@PathVariable Integer id) {
        return contentRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void postContent(@RequestBody Content content) {
        this.contentRepo.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateContent(@RequestBody Content content, @PathVariable Integer id) {
        if (!contentRepo.existById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        contentRepo.save(content);
    }

    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Integer id) {
        if (!contentRepo.deleteContent(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}
