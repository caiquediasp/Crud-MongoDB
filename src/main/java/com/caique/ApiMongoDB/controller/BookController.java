package com.caique.ApiMongoDB.controller;

import com.caique.ApiMongoDB.exceptions.BookNotFoundException;
import com.caique.ApiMongoDB.repository.BookRepository;
import com.caique.ApiMongoDB.util.Book;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookstore")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/findBookById/{id}")
    public ResponseEntity<Book> findBookById (@PathVariable("id") String idBook) {
        Book book = bookRepository.findById(idBook)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + idBook));
        return ResponseEntity.ok(book);
    }

    @GetMapping("/findBookByTitle/{title}")
    public ResponseEntity<Book> findBookByTitle (@PathVariable("title") String bookTitle) {
        Book book = bookRepository.findBookByTitle(bookTitle);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/findBooksByAuthor/{author}")
    public List<Book> findBooksByAuthor (@PathVariable("author") String authorName) {
        return bookRepository.findBooksByAuthor(authorName);
    }

    @GetMapping("/findAllBooks")
    public List<Book> findAllBooks () {
        return bookRepository.findAll();
    }

    @PostMapping("/insertOneBook")
    public Book insertOneBook (@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PostMapping("/insertManyBooks")
    public ResponseEntity<List<Book>> insertManyBooks (@RequestBody List<Book> books) {
        bookRepository.saveAll(books);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<Book> updateBook (@RequestBody Book book) {
        Book updatedBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + book.getId()));
        updatedBook.setTitle(book.getTitle());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setLikes(book.getLikes());
        updatedBook.setDislikes(book.getDislikes());

        bookRepository.save(updatedBook);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") String idBook) {
        Book book = bookRepository.findById(idBook)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " +  idBook));

        bookRepository.delete(book);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
