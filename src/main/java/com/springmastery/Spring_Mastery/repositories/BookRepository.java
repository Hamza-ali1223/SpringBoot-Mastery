package com.springmastery.Spring_Mastery.repositories;

import com.springmastery.Spring_Mastery.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
