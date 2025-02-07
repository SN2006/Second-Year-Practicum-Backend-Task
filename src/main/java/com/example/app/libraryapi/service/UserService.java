package com.example.app.libraryapi.service;

import com.example.app.libraryapi.dtos.book.BookDto;
import com.example.app.libraryapi.dtos.user.CredentialsDto;
import com.example.app.libraryapi.dtos.user.SignUpDto;
import com.example.app.libraryapi.dtos.user.UserDto;
import com.example.app.libraryapi.entity.Book;
import com.example.app.libraryapi.entity.User;
import com.example.app.libraryapi.entity.enums.Role;
import com.example.app.libraryapi.exception.BadRequestException;
import com.example.app.libraryapi.exception.NotFoundException;
import com.example.app.libraryapi.repository.BookRepository;
import com.example.app.libraryapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, BookRepository bookRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public UserDto login(CredentialsDto credentialsDto){
        User user = userRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new NotFoundException("Unknown user"));

        if (new String(credentialsDto.getPassword()).equals(user.getPassword())){
            return modelMapper.map(user, UserDto.class);
        }

        throw new BadRequestException(Collections.singletonMap("password", "Invalid password"));
    }

    @Transactional
    public UserDto register(SignUpDto signUpDto){
        Optional<User> optionalUser = userRepository.findByEmail(signUpDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new BadRequestException(Collections.singletonMap("email", "Email already in use"));
        }

        User user = modelMapper.map(signUpDto, User.class);
        user.setPassword(new String(signUpDto.getPassword()));
        user.setRole(Role.CLIENT);

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Transactional(readOnly = true)
    public UserDto findUserByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Unknown user"));

        return modelMapper.map(user, UserDto.class);
    }

    @Transactional
    public BookDto addFavouriteBook(String userEmail, Long bookId){
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Unknown user"));
        if (user.getFavouriteBooks().stream().anyMatch(book -> book.getId().equals(bookId))) {
            throw new BadRequestException(Collections.singletonMap("bookId", "Book is already favourite"));
        }
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book with id " + bookId + " not found"));
        user.addFavouriteBook(book);
        userRepository.save(user);
        return modelMapper.map(book, BookDto.class);
    }

    @Transactional
    public void removeFavouriteBook(String userEmail, Long bookId){
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Unknown user"));
        if (user.getFavouriteBooks().stream().noneMatch(book -> book.getId().equals(bookId))) {
            throw new NotFoundException("Book with id " + bookId + " not found in your favourite books");
        }
        user.setFavouriteBooks(
                user.getFavouriteBooks().stream()
                        .filter(book -> !book.getId().equals(bookId))
                        .collect(Collectors.toList())
        );
        userRepository.save(user);
    }

    @Transactional
    public void setRoleByEmail(String email, Role role){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Unknown user"));
        user.setRole(role);
        userRepository.save(user);
    }
}
