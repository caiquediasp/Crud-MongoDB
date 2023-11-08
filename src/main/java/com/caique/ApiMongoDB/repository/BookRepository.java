package com.caique.ApiMongoDB.repository;

import com.caique.ApiMongoDB.util.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    @Query("{title: '?0'}")
    Book findBookByTitle (String title);

    @Query("{author: '?0'}")
    List<Book> findBooksByAuthor (String author);


}
