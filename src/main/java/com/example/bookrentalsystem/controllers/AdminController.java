package com.example.bookrentalsystem.controllers;


import com.example.bookrentalsystem.models.Book;
import com.example.bookrentalsystem.models.RentalRequest;
import com.example.bookrentalsystem.models.User;
import com.example.bookrentalsystem.services.BookService;
import com.example.bookrentalsystem.services.RentalRequestService;
import com.example.bookrentalsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final BookService bookService;
    private final RentalRequestService rentalRequestService;
    private final UserService userService;

    @Autowired
    public AdminController(BookService bookService, RentalRequestService rentalRequestService, UserService userService) {
        this.bookService = bookService;
        this.rentalRequestService = rentalRequestService;
        this.userService = userService;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId){
        return bookService.getBookById(bookId)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBookStock(@PathVariable Long bookId, @RequestBody int stock) {
        bookService.updateBookStock(bookId, stock);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/rental-requests/{requestId}accept")
    public ResponseEntity<RentalRequest> acceptRentalRequest(@PathVariable Long requestId) {
        rentalRequestService.acceptRentalRequest(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/rental-requests")
    public ResponseEntity<List<RentalRequest>> getAllRentalRequest(){
        List<RentalRequest> rentalRequests = rentalRequestService.getAllRentalRequests();
        return new ResponseEntity<>(rentalRequests, HttpStatus.OK);
    }

//    @GetMapping("/rental-requests")
//    public ResponseEntity<List<RentalRequest>> getRentalRequestByStatus(@RequestParam String status) {
//        List<RentalRequest> rentalRequests = rentalRequestService.getRentalRequestByStatus(status);
//        return new ResponseEntity<>(rentalRequests, HttpStatus.OK);
//    }

    @DeleteMapping("/rental-requests/{requestId}")
    public ResponseEntity<Void> deleteRentalRequest(@PathVariable Long requestId) {
        rentalRequestService.deleteRentalRequest(requestId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/rental-requests/{requestId}/status")
    public ResponseEntity<RentalRequest> updateRentalRequestStatus(@PathVariable Long requestId, @RequestParam String status) {
        RentalRequest rentalRequest = rentalRequestService.updateRentalRequestStatus(requestId, status);
        return new ResponseEntity<>(rentalRequest, HttpStatus.OK);
    }

    @GetMapping("/rental-requests/borrower/{borrower}")
    public ResponseEntity<List<RentalRequest>> getRentalRequestByBorrower(@PathVariable Long borrowerId) {
        User borrower = userService.getUserById(borrowerId).orElse(null);
        if(borrower == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RentalRequest> rentalRequests = rentalRequestService.getRentalRequestsByBorrower(borrower);
        return new ResponseEntity<>(rentalRequests, HttpStatus.OK);
    }

    @GetMapping("/rental-request/book/{bookId}")
    public ResponseEntity<List<RentalRequest>> getRentalRequestByBook(@PathVariable Long bookId){
        Book book = bookService.getBookById(bookId).orElse(null);
        if(book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RentalRequest> rentalRequests = rentalRequestService.getRentalRequestByBook(book);
        return new ResponseEntity<>(rentalRequests, HttpStatus.OK);
    }

//    @GetMapping("/rental-requests/book/{bookId}/status/{status}")
//    public ResponseEntity<List<RentalRequest>> getRentalRequestByBookAndStatus(@PathVariable Long bookId, @PathVariable String status) {
//        Book book = bookService.getBookById(bookId).orElse(null);
//        if(book == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        List<RentalRequest> rentalRequests = rentalRequestService.getRentalRequestByBookAndStatus(book, status);
//        return new ResponseEntity<>(rentalRequests, HttpStatus.OK);
//    }
}
