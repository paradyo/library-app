package com.engineer.library.controller;

import com.engineer.library.constant.Constant;
import com.engineer.library.exception.RequestBodyException;
import com.engineer.library.exception.RoleNotFoundException;
import com.engineer.library.exception.UserNotFoundException;
import com.engineer.library.model.Book;
import com.engineer.library.model.GuestBook;
import com.engineer.library.model.Role;
import com.engineer.library.model.User;
import com.engineer.library.repository.GuestBookRepository;
import com.engineer.library.repository.RoleRepository;
import com.engineer.library.repository.UserRepository;
import com.engineer.library.service.BookDTO;
import com.engineer.library.service.CustomPageResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private GuestBookRepository guestBookRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, GuestBookRepository guestBookRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.guestBookRepository = guestBookRepository;
    }

    @GetMapping(path = "/users", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public CustomPageResponse getUsersV1dot0(
            @RequestParam(required = false) Integer role_id,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "id") String sortBy,
            Pageable pageable
    ){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        // Prepare the filtering criteria
        Specification<User> spec = null;
        if (role_id != null) {
            spec = (root, query, cb) -> cb.equal(root.get("role").get("id"), role_id);
        }
        Page<User> result;
        if (spec != null) {
            result = userRepository.findAll(spec, pageable);
        } else {
            result = userRepository.findAll(pageable);
        }

        // Set the 'next' and 'prev' links in the response
        UriComponentsBuilder nextUriBuilder = UriComponentsBuilder.fromPath("/users");
        UriComponentsBuilder prevUriBuilder = UriComponentsBuilder.fromPath("/users");

        if (role_id != null) {
            nextUriBuilder.queryParam("role_id", role_id);
            prevUriBuilder.queryParam("role_id", role_id);
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
        return new CustomPageResponse(result.getContent(), nextPageUrl, prevPageUrl, result.getPageable(), result.getTotalPages(), result.getTotalElements(), result.getNumberOfElements(), result.isFirst(), result.isLast());
    }

    @GetMapping(path = "/users/{id}", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public ResponseEntity<User> getUserById(@PathVariable int id) throws UserNotFoundException {
        // Find the user with the given ID in the database
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            // If the user with the specified ID is not found, return a 404 Not Found response
            throw new UserNotFoundException("There is no user with this id.");
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "/users/{id}", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) throws UserNotFoundException {
        // Find the user with the given ID in the database
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            // If the user with the specified ID is not found, return a 404 Not Found response
            throw new UserNotFoundException("There is no user with this id.");
        }

        // Now delete the User
        userRepository.delete(user);

        // Return a 204 No Content response indicating successful deletion
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/users/{id}", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) throws UserNotFoundException, RequestBodyException {
        System.out.println(user.getFullName());
        if (user.getFullName() == null || user.getEmail() == null) {
            throw new RequestBodyException("All user fields are required for PUT method.");
        }

        // Validate that the ID in the request body matches the ID in the path
        if (user.getId() != id) {
            throw new RequestBodyException("The ID in the request body does not match the ID in the path.");
        }

        // Find the user with the given ID in the database
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            // If the user with the specified ID is not found, return a 404 Not Found response
            throw new UserNotFoundException("There is no user with this id.");
        }

        // Update the user's fields only if they are different from the current values
        if (!existingUser.getFullName().equals(user.getFullName())) {
            existingUser.setFullName(user.getFullName());
        }
        if (!existingUser.getEmail().equals(user.getEmail())) {
            existingUser.setEmail(user.getEmail());
        }
        if (!existingUser.getPassword().equals(user.getPassword())) {
            existingUser.setPassword(user.getPassword());
        }

        // Save the updated user back to the database
        User updatedUser = userRepository.save(existingUser);

        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping(path = "/users/{id}", headers = Constant.API_VERSION_HEADER_NAME + "=v1.0")
    public ResponseEntity<User> updateUserPartial(@PathVariable int id, @RequestBody User user) throws UserNotFoundException {
        // Find the user with the given ID in the database
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            // If the user with the specified ID is not found, return a 404 Not Found response
            throw new UserNotFoundException("There is no user with this id.");
        }

        // Update the user's fields if they are present and different from the current values
        if (user.getFullName() != null && !existingUser.getFullName().equals(user.getFullName())) {
            existingUser.setFullName(user.getFullName());
        }

        if (user.getEmail() != null && !existingUser.getEmail().equals(user.getEmail())) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getPassword() != null && !existingUser.getPassword().equals(user.getPassword())) {
            existingUser.setPassword(user.getPassword());
        }

        // Save the updated user back to the database
        User updatedUser = userRepository.save(existingUser);

        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/roles/{id}/users")
    public ResponseEntity<User> createUser(@PathVariable int id, @RequestBody User user) throws RoleNotFoundException {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isEmpty()){
            throw new RoleNotFoundException("There is no role with this id.");
        }
        // PPerform validation and any necessary operations before creating the book
        User userToCreate = new User();
        userToCreate.setFullName(user.getFullName());
        userToCreate.setEmail(user.getEmail());
        userToCreate.setPassword(user.getPassword());
        userToCreate.setRole(role.get());
        User createdUser = userRepository.save(userToCreate);

        return ResponseEntity.ok(createdUser);
    }
}
