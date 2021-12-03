package com.example.my1119.repository;

import com.example.my1119.domain.Comment;
import com.example.my1119.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
