package com.example.bookrentalsystem.services;

import com.example.bookrentalsystem.models.Book;
import com.example.bookrentalsystem.models.RentalRequest;
import com.example.bookrentalsystem.models.User;
import com.example.bookrentalsystem.repositories.RentalRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RentalRequestService {
    private final RentalRequestRepository rentalRequestRepository;

    @Autowired
    public RentalRequestService(RentalRequestRepository rentalRequestRepository) {
        this.rentalRequestRepository = rentalRequestRepository;
    }

    public RentalRequest createRentalRequest(RentalRequest rentalRequest) {
        return (RentalRequest) rentalRequestRepository.save(rentalRequest);
    }

    public Optional getRentalRequestById(Long requestId) {
        return rentalRequestRepository.findById(requestId);
    }

    public List<RentalRequest> getAllRentalRequests() {
        return rentalRequestRepository.findAll();
    }

    public List<RentalRequest> getRentalRequestsByBorrower(User borrower) {
        return rentalRequestRepository.findByBorrower(borrower);
    }

    public List<RentalRequest> getRentalRequestByBook(Book book) {
        return rentalRequestRepository.findByBook(book);
    }

    public List<RentalRequest> getRentalRequestByAccepted(boolean accepted) {
        return rentalRequestRepository.findByAccepted(accepted);
    }

    public List<RentalRequest> getRentalRequestByStatus(String status) {
        return rentalRequestRepository.findByStatus(status);
    }

    public RentalRequest updateRentalReqeuestStatus(Long requestId, String status) {
        Optional<RentalRequest> optionalRentalRequest = rentalRequestRepository.findById(requestId);
        if(optionalRentalRequest.isPresent()) {
            RentalRequest rentalRequest = optionalRentalRequest.get();
            rentalRequest.setStatus(status);
            return (RentalRequest) rentalRequestRepository.save(rentalRequest);
        } else  {
            throw new NoSuchElementException("Rental request not found");
        }
    }

    public void acceptRentalRequest(Long requestId) {
        Optional<RentalRequest> optionRequest = rentalRequestRepository.findById(requestId);
        if (optionRequest.isPresent()) {
            RentalRequest request = optionRequest.get();
            request.setAccepted(true);
            rentalRequestRepository.save(request);
        }
    }

    public void deleteRentalRequest(Long requestId) {
        rentalRequestRepository.deleteById(requestId);
    }

    public RentalRequest updateRentalRequestStatus(Long requestId, String status) {
        Optional<RentalRequest> optionalRentalRequest = rentalRequestRepository.findById(requestId);
        if (optionalRentalRequest.isPresent()) {
            RentalRequest rentalRequest = optionalRentalRequest.get();
            rentalRequest.setStatus(status);
            return (RentalRequest) rentalRequestRepository.save(rentalRequest);
        } else {
            throw new NoSuchElementException("Rental request not found");
        }
    }

    public List<RentalRequest> getRentalRequestByBookAndStatus(Book book, String status) {
        return rentalRequestRepository.findByBookAndStatus(book, status);
    }
}
