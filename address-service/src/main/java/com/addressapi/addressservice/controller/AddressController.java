package com.addressapi.addressservice.controller;

import com.addressapi.addressservice.model.Address;
import com.addressapi.addressservice.repo.AddresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AddressController {
    
    @Autowired
    private AddresRepo addresRepo;


    @PostMapping("/create/address")
    public ResponseEntity<Address> createEmployee(@RequestBody Address address) {
        addresRepo.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAllEmployeeById(@PathVariable("id") Integer id) {
        Optional<Address> byId = addresRepo.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(byId.get());
    }
}
