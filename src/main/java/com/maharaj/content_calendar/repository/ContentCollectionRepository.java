package com.maharaj.content_calendar.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Repository;

import com.maharaj.content_calendar.model.Content;
import com.maharaj.content_calendar.model.Status;
import com.maharaj.content_calendar.model.Type;

import jakarta.annotation.PostConstruct;

@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository(){

    }

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content) {
        if (findById(content.id()).isPresent()){
            contentList.remove(findById(content.id()).get());
            contentList.add(content);
;
        } else {
            contentList.add(content);
        }
        

    }

    public Boolean existById(Integer id) {
        for (int i=0;i<contentList.size();i++){
            if (contentList.get(i).id().equals(id) ){
                return true;
            }

        }
        return false;
    }

    public Boolean deleteContent(Integer id) {
        return contentList.removeIf(c -> c.id().equals(id));
    }


    @PostConstruct
    public void init(){

        Content c = new Content(1, "my first", "first desc", Status.COMPLETED, Type.PROJECT, LocalDateTime.now(), null);
        contentList.add(c);
        
    }

}
