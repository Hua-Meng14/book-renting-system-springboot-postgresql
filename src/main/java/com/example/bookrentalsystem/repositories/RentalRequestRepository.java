package com.example.bookrentalsystem.repositories;

import com.example.bookrentalsystem.models.Book;
import com.example.bookrentalsystem.models.RentalRequest;
import com.example.bookrentalsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRequestRepository extends JpaRepository {
    List<RentalRequest> findByBorrower(User borrower);
    List<RentalRequest> findByBook(Book book);
    List<RentalRequest> findByAccepted(boolean accepted);
    List<RentalRequest> findAll();
    List<RentalRequest> findByStatus(String status);

    List<RentalRequest> findByBookAndStatus(Book book, String status);
}
