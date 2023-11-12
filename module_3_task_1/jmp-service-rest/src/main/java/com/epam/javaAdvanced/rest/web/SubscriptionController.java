package com.epam.javaAdvanced.rest.web;

import com.epam.javaAdvanced.rest.api.SubscriptionService;
import com.epam.javaAdvanced.rest.domain.Subscription;
import com.epam.javaAdvanced.rest.domain.SubscriptionRequestDto;
import com.epam.javaAdvanced.rest.domain.SubscriptionResponseDto;
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
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final ConversionService conversionService;

    @Operation(summary = "Get a subscription by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the subscription",
                    content = {@Content(mediaType = "application/hal+json", schema = @Schema(implementation = SubscriptionResponseDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Subscription not found", content = @Content)})
    @GetMapping("/{subscriptionId}")
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable("subscriptionId") Long subscriptionId) {
        Optional<Subscription> subscriptionOpt = subscriptionService.getSubscription(subscriptionId);

        return subscriptionOpt
                .map(subscription -> {
                    SubscriptionResponseDto subscriptionResponseDto = conversionService.convert(subscription, SubscriptionResponseDto.class);
                    Link selfRel = linkTo(methodOn(SubscriptionController.class).getSubscription(subscriptionId)).withSelfRel();
                    subscriptionResponseDto.add(selfRel);
                    return ResponseEntity.ok(subscriptionResponseDto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all subscriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the subscriptions",
                    content = {@Content(mediaType = "application/hal+json", schema = @Schema(implementation = SubscriptionResponseDto.class))})
    })
    @GetMapping
    public ResponseEntity<CollectionModel<SubscriptionResponseDto>> getAllSubscriptions() {
        List<SubscriptionResponseDto> subscriptionResponseDtoList = subscriptionService.getAllSubscriptions()
                .stream()
                .map(subscription -> {
                    SubscriptionResponseDto subscriptionResponseDto = conversionService.convert(subscription, SubscriptionResponseDto.class);
                    Link selfRel = linkTo(methodOn(SubscriptionController.class).getSubscription(subscription.getId())).withSelfRel();
                    subscriptionResponseDto.add(selfRel);
                    return subscriptionResponseDto;
                })
                .toList();

        Link selfRel = linkTo(methodOn(SubscriptionController.class).getAllSubscriptions()).withSelfRel();

        return ResponseEntity.ok(CollectionModel.of(subscriptionResponseDtoList, selfRel));
    }

    @Operation(summary = "Create a subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the subscription",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = conversionService.convert(subscriptionRequestDto, Subscription.class);
        if (subscription == null) return ResponseEntity.badRequest().build();
        subscription = subscriptionService.save(subscription);

        return ResponseEntity.ok(conversionService.convert(subscription, SubscriptionResponseDto.class));
    }

    @Operation(summary = "Update a subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Subscription not found",
                    content = @Content)
    })
    @PutMapping
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = conversionService.convert(subscriptionRequestDto, Subscription.class);
        Optional<Subscription> savedSubscription = Optional.ofNullable(subscription).flatMap(s -> subscriptionService.getSubscription(s.getId()));
        if(savedSubscription.isEmpty()) return ResponseEntity.notFound().build();
        subscriptionService.save(subscription);

        return ResponseEntity.ok(conversionService.convert(subscription, SubscriptionResponseDto.class));
    }

    @Operation(summary = "Delete a subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the subscription")
    })
    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Object> deleteSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        return ResponseEntity.ok().build();
    }
}
