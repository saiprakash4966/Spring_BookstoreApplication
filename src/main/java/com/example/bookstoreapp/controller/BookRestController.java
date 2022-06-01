package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.BookDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.model.BookData;
import com.example.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class BookRestController
{
    @Autowired
    private IBookService iBookService;

    @GetMapping("/get_books")
    public ResponseEntity<ResponseDTO> getBooksList() {
        List<BookData> bookDataList = iBookService.getBookList();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", bookDataList, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get_by_id/{bookId}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable("bookId") int bookId) {
        BookData bookData = iBookService.getBookById(bookId);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success for Id", bookData, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/order_ascend")
    public ResponseEntity<ResponseDTO> sortBookAscendingOrder() {
        List<BookData> bookDataList = iBookService.sortBookAscendingOrder();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", bookDataList, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/order_descend")
    public ResponseEntity<ResponseDTO> sortBookDescendingOrder() {
        List<BookData> bookDataList = iBookService.sortBookDescendingOrder();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", bookDataList, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/add_book")
    public ResponseEntity<ResponseDTO> addBook(@RequestBody BookDTO bookDTO) {
        BookData bookData = iBookService.addBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book added successfully", bookData, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
