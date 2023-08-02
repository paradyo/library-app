package com.engineer.library.controller;

import com.engineer.library.constant.Constant;
import com.engineer.library.exception.BookNotFoundException;
import com.engineer.library.exception.RequestBodyException;
import com.engineer.library.model.Book;
import com.engineer.library.repository.BookRepository;
import com.engineer.library.repository.UserRepository;
import com.engineer.library.service.BookDTO;
import com.engineer.library.service.BookPageResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping(path = "/books", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    @Cacheable("books")
    public BookPageResponse getBooksV1dot0(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "true", required = false) String visible,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "id") String sortBy,
            Pageable pageable
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<Book> result;

        if (name != null && author != null) {
            // Filter by both name and author
            result = bookRepository.findByNameAndAuthor(name, author, pageable);
        } else if (name != null) {
            // Filter by name only
            if (search != null) {
                result = bookRepository.findByNameContainingIgnoreCase(name, pageable);
            } else {
                result = bookRepository.findByName(name, pageable);
            }
        } else if (author != null) {
            // Filter by author only
            if (search != null) {
                result = bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
            } else {
                result = bookRepository.findByAuthor(author, pageable);
            }
        } else if (visible != null) {
            // Filter by visibility only
            result = bookRepository.findByIsVisible(Boolean.parseBoolean(visible), pageable);
        } else if (search != null) {
            // Search in all books if no filtering criteria provided
            result = bookRepository.findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(search, search, pageable);
        } else {
            // Return all books if no filtering criteria provided
            result = bookRepository.findAll(pageable);
        }

        // Set the 'next' and 'prev' links in the response
        UriComponentsBuilder nextUriBuilder = UriComponentsBuilder.fromPath("/books");
        UriComponentsBuilder prevUriBuilder = UriComponentsBuilder.fromPath("/books");

        if (name != null) {
            nextUriBuilder.queryParam("name", name);
            prevUriBuilder.queryParam("title", name);
        }
        if (author != null) {
            nextUriBuilder.queryParam("author", author);
            prevUriBuilder.queryParam("author", author);
        }
        if (search != null) {
            nextUriBuilder.queryParam("search", search);
            prevUriBuilder.queryParam("search", search);
        }

        String nextPageUrl = null;
        String prevPageUrl = null;

        if (result.hasNext()) {
            nextUriBuilder.queryParam("page", result.getNumber() + 1);
            nextPageUrl = nextUriBuilder.build().toString();
        }

        if (result.hasPrevious()) {
            prevUriBuilder.queryParam("page", result.getNumber() - 1);
            prevPageUrl = prevUriBuilder.build().toString();
        }

        // Create the response object with content and pagination information
        return new BookPageResponse(result.getContent(), nextPageUrl, prevPageUrl, result.getPageable(), result.getTotalPages(), result.getTotalElements(), result.getNumberOfElements(), result.isFirst(), result.isLast());
    }

    @GetMapping(path = "/books/{id}", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public ResponseEntity<BookDTO> getBookV1dot0(@PathVariable int id) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new BookNotFoundException("There is no book with this id.");
        }
        Book book = optionalBook.get();
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setImageS3Id(book.getImageS3Id());
        bookDTO.setVisible(book.isVisible());
        return ResponseEntity.ok(bookDTO);
    }

    @PutMapping(path = "/books/{id}", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public ResponseEntity<BookDTO> updateWholeBookV1dot0(@PathVariable int id, @RequestBody Book book) throws RequestBodyException {
        if (book == null || book.getName() == null || book.getAuthor() == null || book.getImageS3Id() == null) {
            throw new RequestBodyException("All book fields are required for PUT method.");
        }

        // Validate that the ID in the request body matches the ID in the path
        if (book.getId() != id) {
            throw new RequestBodyException("The ID in the request body does not match the ID in the path.");
        }

        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook == null) {
            throw new BookNotFoundException("There is no book with this id.");
        }

        // Update the book's fields only if they are different from the current values
        if (!existingBook.getName().equals(book.getName())) {
            existingBook.setName(book.getName());
        }

        if (!existingBook.getAuthor().equals(book.getAuthor())) {
            existingBook.setAuthor(book.getAuthor());
        }

        if (!existingBook.getImageS3Id().equals(book.getImageS3Id())) {
            existingBook.setImageS3Id(book.getImageS3Id());
        }

        if (!existingBook.isVisible() == book.isVisible()) {
            existingBook.setVisible(book.isVisible());
        }


        Book updatedBook = bookRepository.save(existingBook);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(updatedBook.getId());
        bookDTO.setName(updatedBook.getName());
        bookDTO.setAuthor(updatedBook.getAuthor());
        bookDTO.setVisible(updatedBook.isVisible());
        bookDTO.setImageS3Id(updatedBook.getImageS3Id());
        return ResponseEntity.ok(bookDTO);
    }

    @PatchMapping(path = "/books/{id}", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public ResponseEntity<BookDTO> updatePartialBookV1dot0(@PathVariable int id, @RequestBody Book book) throws RequestBodyException {
        // Validate the request body for PATCH method to ensure at least one field is present
        if (book == null || (book.getName() == null && book.getAuthor() == null && book.getImageS3Id() == null)) {
            throw new RequestBodyException("At least one book field is required for PATCH method.");
        }

        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook == null) {
            throw new BookNotFoundException("There is no book with this id.");
        }

        // Update the book's fields if they are present and different from the current values
        if (book.getName() != null && !existingBook.getName().equals(book.getName())) {
            existingBook.setName(book.getName());
        }

        if (book.getAuthor() != null && !existingBook.getAuthor().equals(book.getAuthor())) {
            existingBook.setAuthor(book.getAuthor());
        }

        if (book.getImageS3Id() != null && !existingBook.getImageS3Id().equals(book.getImageS3Id())) {
            existingBook.setImageS3Id(book.getImageS3Id());
        }

        if (existingBook.isVisible() != book.isVisible()) {
            existingBook.setVisible(book.isVisible());
        }

        Book updatedBook = bookRepository.save(existingBook);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(updatedBook.getId());
        bookDTO.setName(updatedBook.getName());
        bookDTO.setAuthor(updatedBook.getAuthor());
        bookDTO.setImageS3Id(updatedBook.getImageS3Id());
        bookDTO.setVisible(updatedBook.isVisible());
        return ResponseEntity.ok(bookDTO);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        // Check if the book with the given ID exists in the database
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook == null) {
            throw new BookNotFoundException("There is no book with this id.");
        }
        // TODO: 2.08.2023 | Delete the guest_book references first.
        //bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/books")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        // Perform validation and any necessary operations before creating the book
        // For example, you can convert the BookDTO to Book entity and save it to the database
        Book bookToCreate = new Book();
        bookToCreate.setName(bookDTO.getName());
        bookToCreate.setAuthor(bookDTO.getAuthor());
        bookToCreate.setImageS3Id(bookDTO.getImageS3Id());
        bookToCreate.setVisible(bookDTO.isVisible());
        // Set other fields as needed

        Book createdBook = bookRepository.save(bookToCreate);

        // Convert the created book back to BookDTO and return it in the response
        BookDTO createdBookDTO = new BookDTO();
        createdBookDTO.setId(createdBook.getId());
        createdBookDTO.setName(createdBook.getName());
        createdBookDTO.setAuthor(createdBook.getAuthor());
        createdBookDTO.setImageS3Id(createdBook.getImageS3Id());
        createdBookDTO.setVisible(createdBook.isVisible());

        return ResponseEntity.ok(createdBookDTO);
    }

    /*
    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id) throws RuntimeException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Can't find the user.");
        }
        return user.get().getPosts();
    }
    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) throws RuntimeException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
     */
}
