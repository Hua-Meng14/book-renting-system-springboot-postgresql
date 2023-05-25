package com.example.bookrentalsystem.services;

import com.example.bookrentalsystem.models.Book;
import com.example.bookrentalsystem.models.RentalRequest;
import com.example.bookrentalsystem.models.User;
import com.example.bookrentalsystem.repositories.RentalRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<RentalRequest> getRentalRequestsByBorrower(User borrower) {
        return rentalRequestRepository.findByBorrower(borrower);
    }

    public List<RentalRequest> getRentalRequestByBook(Book book) {
        return rentalRequestRepository.findByBook(book);
    }

    public List<RentalRequest> getRentalRequestByAccepted(boolean accepted) {
        return rentalRequestRepository.findByAccepted(accepted);
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
}
