package com.example.bookrentalsystem.services;

import com.example.bookrentalsystem.models.Book;
import com.example.bookrentalsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(@Qualifier("books") BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void updateBookStock(Long bookId, int newStock) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setStock(newStock);
            bookRepository.save(book);
        }
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<Book> searchBookByTitle(String keyword) {
        return bookRepository.findByTitleContaining(keyword);
    }

    public List<Book> searchBookByAuthor(String author) {
        return bookRepository.findByAuthorContaining(author);
    }

//    public List<Book> searchBookByGenre(String genre) {
//        return bookRepository.findByGenreContainingIgnoreCase(genre);
//    }
}
