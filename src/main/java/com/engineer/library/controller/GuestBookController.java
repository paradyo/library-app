package com.engineer.library.controller;

import com.engineer.library.constant.Constant;
import com.engineer.library.exception.BookNotFoundException;
import com.engineer.library.exception.RequestBodyException;
import com.engineer.library.exception.UserNotFoundException;
import com.engineer.library.model.Book;
import com.engineer.library.model.GuestBook;
import com.engineer.library.model.User;
import com.engineer.library.repository.BookRepository;
import com.engineer.library.repository.GuestBookRepository;
import com.engineer.library.repository.RoleRepository;
import com.engineer.library.repository.UserRepository;
import com.engineer.library.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class GuestBookController {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final RoleRepository roleRepository;
    private final GuestBookRepository guestBookRepository;

    public GuestBookController(UserRepository userRepository, RoleRepository roleRepository, GuestBookRepository guestBookRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.guestBookRepository = guestBookRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping(path = "/users/{id}/books", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public List<GuestBookDTO> getUserBooksV1dot0(@PathVariable int id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            // Return an empty list or throw an exception if the user with the given ID is not found
            throw new UserNotFoundException("There is no user with this id.");
        }

        User user = userOptional.get();

        // Retrieve the guest books associated with the user
        List<GuestBook> guestBooks = guestBookRepository.findByUser(user);

        // Map GuestBook entities to GuestBookDTOs
        List<GuestBookDTO> guestBookDTOs = guestBooks.stream()
                .map(this::mapGuestBookToDTO)
                .collect(Collectors.toList());

        return guestBookDTOs;
    }

    @GetMapping(path = "/books/{id}/users", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public List<UserDTO> getUsersForBookV1dot0(@PathVariable int id) throws BookNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            // Return an empty list or throw an exception if the book with the given ID is not found
            throw new BookNotFoundException("There is no book with this id.");
        }

        Book book = bookOptional.get();

        // Retrieve the guest books associated with the book
        List<GuestBook> guestBooks = guestBookRepository.findByBook(book);

        // Map GuestBook entities to UserDTOs
        List<UserDTO> userDTOs = guestBooks.stream()
                .map(guestBook -> mapUserToDTO(guestBook.getUser()))
                .collect(Collectors.toList());

        return userDTOs;
    }

    @PatchMapping(path = "/users/{userId}/books/{bookId}", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public ResponseEntity<GuestBookDTO> updateGuestBookV1dot0(
            @PathVariable int userId,
            @PathVariable int bookId,
            @RequestBody GuestBookUpdateRequest request
    ) throws RequestBodyException {
        // Retrieve the guest book by user ID and book ID
        Optional<GuestBook> guestBookOptional = guestBookRepository.findByUserIdAndBookId(userId, bookId);
        if (guestBookOptional.isEmpty()) {
            throw new RequestBodyException("There is no item with these ids.");
        }

        GuestBook guestBook = guestBookOptional.get();

        // Update the returnedDate and/or dateToReturn if provided in the request body
        LocalDate returnedDate = request.getReturnedDate();
        LocalDate dateToReturn = request.getDateToReturn();

        if (returnedDate != null) {
            guestBook.setReturnedDate(returnedDate);
        }

        if (dateToReturn != null) {
            guestBook.setDateToReturn(dateToReturn);
        }

        // Save the updated guest book
        guestBookRepository.save(guestBook);

        // Map the updated guest book to GuestBookDTO and return it in the response
        GuestBookDTO guestBookDTO = mapGuestBookToDTO(guestBook);
        return ResponseEntity.ok(guestBookDTO);
    }

    @PostMapping(path = "/users/{userId}/books/{bookId}", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public ResponseEntity<GuestBookDTO> createGuestBook(
            @PathVariable int userId,
            @PathVariable int bookId,
            @RequestBody(required = false) GuestBookCreateRequest request
    ) throws RequestBodyException {
        // Check if the user and book exist in the database
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (userOptional.isEmpty() || bookOptional.isEmpty()) {
            throw new RequestBodyException("One of your id parameters are wrong.");
        }

        User user = userOptional.get();
        Book book = bookOptional.get();

        // Create a new GuestBook entity and set the user and book
        GuestBook guestBook = new GuestBook();
        guestBook.setUser(user);
        guestBook.setBook(book);

        // Set the dateToReturn if provided in the request body
        if (request != null && request.getDateToReturn() != null) {
            guestBook.setDateToReturn(request.getDateToReturn());
        }

        // Save the new GuestBook to the database
        GuestBook savedGuestBook = guestBookRepository.save(guestBook);

        // Map the saved GuestBook to GuestBookDTO and return it in the response
        GuestBookDTO guestBookDTO = mapGuestBookToDTO(savedGuestBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(guestBookDTO);
    }

    // Mapper method to convert GuestBook entity to GuestBookDTO
    private GuestBookDTO mapGuestBookToDTO(GuestBook guestBook) {
        GuestBookDTO guestBookDTO = new GuestBookDTO();
        guestBookDTO.setId(guestBook.getId());
        guestBookDTO.setUser(mapUserToDTO(guestBook.getUser()));
        guestBookDTO.setBook(mapBookToDTO(guestBook.getBook()));
        guestBookDTO.setDateToReturn(guestBook.getDateToReturn());
        guestBookDTO.setReturnedDate(guestBook.getReturnedDate());
        return guestBookDTO;
    }

    // Mapper methods for User and Book entities
    private UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        // Map other fields as needed
        return userDTO;
    }

    private BookDTO mapBookToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthor(book.getAuthor());
        // Map other fields as needed
        return bookDTO;
    }
}
