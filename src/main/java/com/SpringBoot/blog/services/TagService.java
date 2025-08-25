package com.SpringBoot.blog.services;

import java.util.List;

import com.SpringBoot.blog.domain.entities.Tag;

public interface TagService {
    List<Tag> getTags();
}
