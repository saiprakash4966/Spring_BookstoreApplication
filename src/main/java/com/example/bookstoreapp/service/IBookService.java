package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.BookDTO;
import com.example.bookstoreapp.model.BookData;

import java.util.List;

public interface IBookService {
    BookData addBook(BookDTO bookDTO);

    List<BookData> getBookList();

    BookData getBookById(int bookId);

    List<BookData> sortBookAscendingOrder();

    List<BookData> sortBookDescendingOrder();
}


