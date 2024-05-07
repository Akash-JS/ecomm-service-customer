package org.example.ecommservicecustomer.controller;

import jakarta.transaction.Transactional;
import org.example.ecommservicecustomer.models.CartItem;
import org.example.ecommservicecustomer.models.Customer;
import org.example.ecommservicecustomer.repositories.ICartItemRepository;
import org.example.ecommservicecustomer.repositories.ICustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    ICustomerRepository customerRepository;
    ICartItemRepository cartItemRepository;

    public CustomerController(ICustomerRepository _customerRepository, ICartItemRepository _cartItemRepository) {
        customerRepository = _customerRepository;
        cartItemRepository = _cartItemRepository;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return  ResponseEntity.ok(customers);
    }

    @GetMapping({"{customerId}"})
    public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return  ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        if(customer == null)
        {
            return  ResponseEntity.badRequest().body("Customer is null");
        }

        customer.setId(UUID.randomUUID().toString());
        customerRepository.save(customer);
        return ResponseEntity.ok("Customer added successfully with Id: " + customer.getId());
    }

    @GetMapping({"{customerId}/cart"})
    public ResponseEntity<List<CartItem>> getCustomerCart(@PathVariable String customerId) {
        List<CartItem> cartItems = cartItemRepository.findAllByCustomerId(customerId);
        return  ResponseEntity.ok(cartItems);
    }

    @GetMapping({"{customerId}/cart-item/{itemId}"})
    public ResponseEntity<Optional<CartItem>> getCustomerCartItem(@PathVariable String customerId, @PathVariable String itemId) {
        Optional<CartItem> cartItem = cartItemRepository.findById(itemId);
        return  ResponseEntity.ok(cartItem);
    }

    @PostMapping("{customerId}/cart-item")
    public ResponseEntity<String> addCartItem(@RequestBody CartItem cartItem) {
        if(cartItem == null)
        {
            return  ResponseEntity.badRequest().body("CartItem is null");
        }

        cartItem.setId(UUID.randomUUID().toString());
        cartItemRepository.save(cartItem);
        return ResponseEntity.ok("CartItem added successfully with Id: " + cartItem.getId());
    }

    @DeleteMapping("{customerId}/cart")
    @Transactional
    public  ResponseEntity<String> deleteAllItems(@PathVariable String customerId ) {
        cartItemRepository.deleteByCustomerId(customerId);
        return ResponseEntity.ok("CartItems deleted successfully ");
    }

    @DeleteMapping("{customerId}/cart-item/{itemId}")
    public  ResponseEntity<String> deleteCartItem(@PathVariable String customerId,@PathVariable String itemId ) {
        cartItemRepository.deleteById(itemId);
        return ResponseEntity.ok("CartItem deleted successfully with Id: " + itemId);
    }
}
