package com.example.bookrentalsystem.repositories;

import com.example.bookrentalsystem.models.Book;
import com.example.bookrentalsystem.models.RentalRequest;
import com.example.bookrentalsystem.models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Component
@Qualifier("rentalrequests")
@Repository
public interface RentalRequestRepository extends JpaRepository<RentalRequest, Long> {

    List<RentalRequest> findByBorrower(User borrower);

    List<RentalRequest> findByBook(Book book);

    List<RentalRequest> findByAccepted(boolean accepted);

    List<RentalRequest> findAll();

//    List<RentalRequest> findByStatus(String status);

//    List<RentalRequest> findByBookAndStatus(Book book, String status);
//    List<RentalRequest> findByBorrower(User borrower);
//    List<RentalRequest> findByBook(Book book);
//    List<RentalRequest> findByAccepted(boolean accepted);
//    List<RentalRequest> findAll();
//    List<RentalRequest> findByStatus(String status);
//
//    @Query("SELECT rr FROM RentalRequest rr WHERE rr.book = :book AND rr.status = :status")
//    List<RentalRequest> findByBookAndStatus(Book book, String status);
}
