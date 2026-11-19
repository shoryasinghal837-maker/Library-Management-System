package com.example.librarymanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    // Constructor injection
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Q2(b): Add a new book
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {

        boolean added = bookService.addBook(book);

        if (added) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Book added successfully.");
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid book details or duplicate ID.");
    }

    // Q2(c): Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {

        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Q2(c): Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(
            @PathVariable Integer id) {

        Optional<Book> book = bookService.getBookById(id);

        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }

        return ResponseEntity.notFound().build();
    }

    // Q2(d): Update book
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(
            @PathVariable Integer id,
            @RequestBody Book book) {

        boolean updated = bookService.updateBook(id, book);

        if (updated) {
            return ResponseEntity.ok("Book updated successfully.");
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Book not found or invalid details.");
    }

    // Q2(e): Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(
            @PathVariable Integer id) {

        boolean deleted = bookService.deleteBook(id);

        if (deleted) {
            return ResponseEntity.ok("Book deleted successfully.");
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Book not found.");
    }
}
