package com.example.bookrentalsystem.repositories;

import com.example.bookrentalsystem.models.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Component
@Qualifier("books")
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String keyword);

    List<Book> findByAuthorContaining(String author);

//    @Query("SELECT b FROM Book b WHERE LOWER(b.genre) LIKE LOWER(CONCAT('%', :genre, '%'))")
//    List<Book> findByGenreContainingIgnoreCase(String genre);

//    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE CONCAT('%', LOWER(:keyword), '%')")
//    List<Book> searchBookByTitle(String keyword);
//
////    @Query("SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))")
//
//    @Query(value = "SELECT * FROM books WHERE LOWER(author) LIKE LOWER(CONCAT('%', :author, '%'))", nativeQuery = true)
//    List<Book> searchBookByAuthor(String author);
//
//    @Query("SELECT b FROM Book b WHERE LOWER(b.genre) LIKE LOWER(CONCAT('%', :genre, '%'))")
//    List<Book> searchBookByGenre(String genre);
}
