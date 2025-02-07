package com.example.app.libraryapi.controller;

import com.example.app.libraryapi.dtos.book.BookDto;
import com.example.app.libraryapi.dtos.book.CreateBookDto;
import com.example.app.libraryapi.dtos.user.UserDto;
import com.example.app.libraryapi.exception.BadRequestException;
import com.example.app.libraryapi.service.BookService;
import com.example.app.libraryapi.service.UserService;
import jakarta.validation.Valid;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> allBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> bookById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/csv")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Resource> bookCsv(){
        List<List<String>> csvBody = bookService.getCsvBooks();

        ByteArrayInputStream byteArrayOutputStream ;

        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(
                        new PrintWriter(out),
                        CSVFormat.DEFAULT.withHeader("id", "name", "year", "authors", "genres")
                )
                ){
            for (List<String> row : csvBody) {
                csvPrinter.printRecord(row);
            }

            csvPrinter.flush();

            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        InputStreamResource resource = new InputStreamResource(byteArrayOutputStream);

        String csvFilename = "books.csv";

        HttpHeaders headers = new HttpHeaders();

        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFilename);
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(
                resource,
                headers,
                HttpStatus.OK
        );
    }

    @GetMapping("/favourites")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BookDto>> favouriteBooks(Authentication authentication) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        return ResponseEntity.ok(userDto.getFavouriteBooks());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid CreateBookDto bookDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return ResponseEntity.ok(bookService.save(bookDto));
    }

    @PostMapping("/favourites/add-by-id/{book_id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BookDto> addFavouriteBook(@PathVariable("book_id") Long bookId, Authentication authentication) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        return ResponseEntity.ok(userService.addFavouriteBook(userDto.getEmail(), bookId));
    }

    @DeleteMapping("/favourites/delete-by-id/{book_id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFavouriteBook(@PathVariable("book_id") Long bookId, Authentication authentication) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        userService.removeFavouriteBook(userDto.getEmail(), bookId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
    }
}
