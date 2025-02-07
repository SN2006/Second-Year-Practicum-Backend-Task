package com.example.app.libraryapi.service;

import com.example.app.libraryapi.dtos.book.BookDto;
import com.example.app.libraryapi.dtos.book.CreateBookDto;
import com.example.app.libraryapi.entity.Author;
import com.example.app.libraryapi.entity.Book;
import com.example.app.libraryapi.entity.Genre;
import com.example.app.libraryapi.exception.NotFoundException;
import com.example.app.libraryapi.repository.AuthorRepository;
import com.example.app.libraryapi.repository.BookRepository;
import com.example.app.libraryapi.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final ModelMapper mapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> mapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookDto findById(Long id) {
        return mapper.map(
                bookRepository.findById(id)
                        .orElseThrow(
                                () -> new NotFoundException("Book with id " + id + " not found")
                        ),
                BookDto.class
        );
    }

    @Transactional(readOnly = true)
    public List<List<String>> getCsvBooks() {
        return bookRepository.findAll().stream()
                .map(book -> {
                    List<String> row = new ArrayList<>();

                    row.add(String.valueOf(book.getId()));
                    row.add(book.getName());
                    row.add(String.valueOf(book.getPublicationYear()));

                    StringBuilder authorsBuilder = new StringBuilder();
                    authorsBuilder.append(book.getAuthors().get(0).getFullName());
                    for (int i = 1; i < book.getAuthors().size(); i++) {
                        authorsBuilder.append("|").append(book.getAuthors().get(i).getFullName());
                    }
                    row.add(authorsBuilder.toString());

                    StringBuilder genresBuilder = new StringBuilder();
                    genresBuilder.append(book.getGenres().get(0).getName());
                    for (int i = 1; i < book.getGenres().size(); i++) {
                        genresBuilder.append("|").append(book.getGenres().get(i).getName());
                    }
                    row.add(genresBuilder.toString());

                    return row;
                })
                .toList();
    }

    @Transactional
    public BookDto save(CreateBookDto bookDto) {
        Book book = mapper.map(bookDto, Book.class);
        book.setAuthors(
                book.getAuthors().stream()
                        .map(author -> {
                            Optional<Author> authorFromDb = authorRepository.findByFullNameIgnoreCase(author.getFullName());
                            return authorFromDb.orElseGet(() -> authorRepository.save(author));
                        })
                        .collect(Collectors.toList())
        );
        book.setGenres(
                book.getGenres().stream()
                        .map(genre -> {
                            Optional<Genre> genreFromDb = genreRepository.findByNameIgnoreCase(genre.getName());
                            return genreFromDb.orElseGet(() -> genreRepository.save(genre));
                        })
                        .collect(Collectors.toList())
        );
        return mapper.map(bookRepository.save(book), BookDto.class);
    }

    @Transactional
    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book with id " + id + " not found")
        );
        bookRepository.delete(book);
    }
}
