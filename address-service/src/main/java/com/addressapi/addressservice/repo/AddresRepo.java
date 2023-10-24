package com.addressapi.addressservice.repo;

import com.addressapi.addressservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresRepo extends JpaRepository<Address,Integer> {
}
