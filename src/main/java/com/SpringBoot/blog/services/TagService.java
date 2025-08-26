package com.SpringBoot.blog.services;

import java.util.List;
import java.util.Set;

import com.SpringBoot.blog.domain.entities.Tag;

public interface TagService {
    List<Tag> getTags();
    List<Tag> createTags(Set<String> tagNames);
}
