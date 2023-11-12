package com.epam.javaAdvanced.rest.web;

import com.epam.javaAdvanced.rest.api.UserService;
import com.epam.javaAdvanced.rest.domain.User;
import com.epam.javaAdvanced.rest.domain.UserRequestDto;
import com.epam.javaAdvanced.rest.domain.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ConversionService conversionService;

    @Operation(summary = "Get a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                            content = {@Content(mediaType = "application/hal+json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("userId") Long userId) {
        Optional<User> userOpt = userService.getUser(userId);
        return userOpt
                .map(user -> {
                    UserResponseDto userResponseDto = conversionService.convert(user, UserResponseDto.class);
                    Link selfLink = linkTo(methodOn(UserController.class).getUser(userId)).withSelfRel();
                    userResponseDto.add(selfLink);
                    return ResponseEntity.ok(userResponseDto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/hal+json", schema = @Schema(implementation = UserResponseDto.class))})
    })
    @GetMapping
    public ResponseEntity<CollectionModel<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtoList = userService
                .getAllUsers()
                .stream()
                .map(user -> {
                    UserResponseDto userResponseDto = conversionService.convert(user, UserResponseDto.class);
                    Link selfLink = linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel();
                    userResponseDto.add(selfLink);
                    return userResponseDto;
                })
                .toList();

        Link selfLink = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();

        return ResponseEntity.ok(CollectionModel.of(userResponseDtoList, selfLink));
    }

    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping()
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        User user = conversionService.convert(userRequestDto, User.class);
        if (user == null) return ResponseEntity.badRequest().build();
        user = userService.saveUser(user);

        return ResponseEntity.ok(conversionService.convert(user, UserResponseDto.class));
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto) {
        User user = conversionService.convert(userRequestDto, User.class);
        Optional<User> savedUser = Optional.ofNullable(user).flatMap(u -> userService.getUser(user.getId()));
        if (savedUser.isEmpty()) return ResponseEntity.notFound().build();
        userService.saveUser(user);

        return ResponseEntity.ok(conversionService.convert(user, UserResponseDto.class));
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book")
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
