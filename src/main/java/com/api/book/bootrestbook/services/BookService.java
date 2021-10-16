package com.api.book.bootrestbook.services;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
//    private static List<Book> list = new ArrayList<Book>();

//    static {
//        list.add(new Book(1, "Java Complete Reference", "XYZ"));
//        list.add(new Book(2, "Java Complete Reference - 2", "ABC"));
//        list.add(new Book(3, "Java Complete Reference - 3", "PQR"));
//
//    }

    //Get all books
    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    //Get single book by ID
    public Book getBookById(int id) {
        Book book = null;
        try {
//            book = list.stream().filter(e -> e.getId() == id).findFirst().get();
            book = this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    //Adding a Book
    public Book addBook(Book book) {
//        list.add(book);
        Book result = this.bookRepository.save(book);
        return result;
    }

    //Deleting a Book
    public void deleteBook(int bid) {
//        list = list.stream().filter(book -> {
//            return book.getId() != bid;
//        }).collect(Collectors.toList());
        this.bookRepository.deleteById(bid);
    }

    //Updating a Book
    public void updateBook(int bid, Book book) {
//        list = list.stream().map(book -> {
//            if (book.getId() == bid) {
//                book.setTitle(updatedBook.getTitle());
//                book.setAuthor(updatedBook.getAuthor());
//            }
//            return book;
//        }).collect(Collectors.toList());
        book.setId(bid);
        this.bookRepository.save(book);
    }
}
