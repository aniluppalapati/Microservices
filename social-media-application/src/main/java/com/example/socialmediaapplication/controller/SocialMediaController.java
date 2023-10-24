package com.example.socialmediaapplication.controller;

import com.example.socialmediaapplication.exception.UserNotFoundException;
import com.example.socialmediaapplication.model.User;
import com.example.socialmediaapplication.service.SocialMediaService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SocialMediaController {

    @Autowired
    private SocialMediaService socialMediaService;
    //retrieve all users
    //users
    @GetMapping("/users")
    public MappingJacksonValue getAllUsers(){
        List<User> users= socialMediaService.fildAllUsers();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);

        SimpleBeanPropertyFilter filter =SimpleBeanPropertyFilter.filterOutAllExcept("name");
        FilterProvider filters= new SimpleFilterProvider().addFilter("someUserBean",filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
    //retrieve by id
    //users/{id}
    @GetMapping("/users/{id}")
    public EntityModel<User> getUserById(@PathVariable int id){
        User user=socialMediaService.findById(id);
        if(user==null){
            throw new UserNotFoundException("User with id, "+id+" Not Found");
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));
        return  entityModel;
    }
    //Create User
    //users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser=socialMediaService.createUser(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    //Delete User
    //users/{id}
    @DeleteMapping("/users/{id}")
    public List<User> deleteUser(@PathVariable int id){
        return socialMediaService.removeUser(id);
    }
    //Versioning Rest Api

    //URI VERSIONING
    @GetMapping("/v1/person")
    public String getUserName(){
        return "Uppalapati Anil Kumar";
    }
    @GetMapping("/v2/person")
    public String getUserName_2(){
        return "Uppalapati Anil Kumar , Vengamambapuram , Nellore ,524404";
    }
    //Request Param VERSIONING
    @GetMapping(path = "/person",params = "version=1")
    public String getUserNameRequestParam_VERSIONING(){


        return "Uppalapati Anil Kumar RequestParam_VERSIONING_@1";
    }
    @GetMapping(path = "/person",params = "version=2")
    public String getUserNameRequestParam_VERSIONING_2(){
        return "Uppalapati Anil Kumar RequestParam_VERSIONING_@2";
    }

    //Header VERSIONING
    //@GetMapping(path = "/person/header",headers = "version=1")
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public String getUserNameHeader_VERSIONING(){
        return "Uppalapati Anil Kumar Header VERSIONING@1";
    }
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public String getUserNameHeader_VERSIONING_2(){
        return "Uppalapati Anil Kumar Header VERSIONING@2";
    }

    //Content Negotiation Versioning
    @GetMapping(path = "/person/produces",produces = "application/vnd.company.app-v1+json")
    public String getUserNameContent_Negotiation_VERSIONING(){
        return "Uppalapati Anil Kumar Content Negotiation VERSIONING@1";
    }
    @GetMapping(path = "/person/produces",produces = "application/vnd.company.app-v2+json")
    public String getUserNameContent_Negotiation_VERSIONING_2(){
        return "Uppalapati Anil Kumar Content Negotiation VERSIONING@2";
    }


}
