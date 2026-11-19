package com.example.librarymanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.librarymanagement.model.Book;

@Service
public class BookService {

    // Collection to store book objects
    private final List<Book> books = new ArrayList<>();

    // Add a new book
    public boolean addBook(Book book) {

        if (book == null || book.getId() == null
                || book.getAvailableCopies() == null
                || book.getAvailableCopies() < 0) {
            return false;
        }

        // Check whether ID already exists
        for (Book b : books) {

            if (b.getId().equals(book.getId())) {
                return false;
            }
        }

        books.add(book);
        return true;
    }

    // Get all books
    public List<Book> getAllBooks() {

        return new ArrayList<>(books);
    }

    // Get book by ID
    public Optional<Book> getBookById(Integer id) {

        for (Book book : books) {

            if (book.getId().equals(id)) {
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }

    // Update book details
    public boolean updateBook(Integer id, Book updatedBook) {

        Optional<Book> result = getBookById(id);

        if (result.isEmpty() || updatedBook == null
                || updatedBook.getAvailableCopies() == null
                || updatedBook.getAvailableCopies() < 0) {
            return false;
        }

        Book existingBook = result.get();

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setCategory(updatedBook.getCategory());
        existingBook.setAvailableCopies(updatedBook.getAvailableCopies());

        return true;
    }

    // Delete book by ID
    public boolean deleteBook(Integer id) {

        return books.removeIf(book -> book.getId().equals(id));
    }
}
