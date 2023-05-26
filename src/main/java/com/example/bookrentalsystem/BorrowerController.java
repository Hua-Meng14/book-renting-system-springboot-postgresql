package com.example.bookrentalsystem;


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
//@RequestMapping("api/borrower")
public class BorrowerController {
    private final UserService userService;
    private final BookService bookService;
    private final RentalRequestService rentalRequestService;

    @Autowired
    public BorrowerController(UserService userService, BookService bookService, RentalRequestService rentalRequestService) {
        this.userService = userService;
        this.bookService = bookService;
        this.rentalRequestService = rentalRequestService;
    }


    // Working
    @PostMapping("/api/borrower")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Working
    @GetMapping("/api/borrower/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/api/borrower/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Not Yet test -> dun know what is the expected format for request bodoy
    @PostMapping("/api/borrower/rental-requests")
    public ResponseEntity<RentalRequest> createRentalRequest(@RequestBody RentalRequest rentalRequest) {
        RentalRequest createdRequest = rentalRequestService.createRentalRequest(rentalRequest);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    // Working
    @PutMapping("/api/borrower/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Not Yet test -> have to create book by admin
    @GetMapping("/api/borrower/books/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Not yet test -> to have create book by admin
    @GetMapping("/api/borrower/books/search")
    public ResponseEntity<List<Book>> searchBookByTitle(@RequestParam String keyword) {
        List<Book> books = bookService.searchBookByTitle(keyword);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    // Working
    @GetMapping("/api/borrower/rental-requests")
    public ResponseEntity<List<RentalRequest>> getRentalRequestByBorrowers(@RequestParam Long borrowerId) {
        User borrower = userService.getUserById(borrowerId).orElse(null);
        if (borrower == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RentalRequest> rentalRequests = rentalRequestService.getRentalRequestsByBorrower(borrower);
        return new ResponseEntity<>(rentalRequests, HttpStatus.OK);
    }

    // Working
    @GetMapping("/api/borrower/books/search/author")
    public ResponseEntity<List<Book>> searchBooksByAuthor(@RequestParam String author) {
        List<Book> books = bookService.searchBookByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

//    @GetMapping("/books/search/genre")
//    public ResponseEntity<List<Book>> searchBooksByGenre(@RequestParam String genre) {
//        List<Book> books = bookService.searchBookByGenre(genre);
//        return new ResponseEntity<>(books, HttpStatus.OK);
//    }

    // Working
    @GetMapping("/api/borrower/rental-requests/books/{bookId}")
    public ResponseEntity<List<RentalRequest>> getRentalRequestsByBook(@PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId).orElse(null);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RentalRequest> rentalRequests = rentalRequestService.getRentalRequestByBook(book);
        return new ResponseEntity<>(rentalRequests, HttpStatus.OK);
    }

    // Working
    @GetMapping("/api/borrower/rental-requests-by-accepted")
    public ResponseEntity<List<RentalRequest>> getRentalRequestByAcceptedStatus(@RequestParam boolean accepted) {
        List<RentalRequest> rentalRequests = rentalRequestService.getRentalRequestByAccepted(accepted);
        return new ResponseEntity<>(rentalRequests, HttpStatus.OK);
    }


}
