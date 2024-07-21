package hh.plus.server.cart.controller;

import hh.plus.server.cart.service.CartService;
import hh.plus.server.cart.service.dto.Cart02Dto;
import hh.plus.server.cart.service.dto.CartDto;
import hh.plus.server.order.domain.entity.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Order API")
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @Operation(summary = "Get cart by id", description = "Returns a cart as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The cart does not exist"),
    })
    @GetMapping("/{cartId}")
    public Cart02Dto getCart(@PathVariable long cartId){ return cartService.getCart02ById(cartId);}



}
