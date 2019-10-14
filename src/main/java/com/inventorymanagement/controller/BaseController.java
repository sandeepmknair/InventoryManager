package com.inventorymanagement.controller;

import com.inventorymanagement.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public abstract class BaseController<T> {
    private final BaseService<T> baseService;

    public BaseController(final BaseService<T> baseService) {
        this.baseService = baseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<T>> listAll() {
        return Optional.ofNullable(baseService.findAll())
                .filter(e -> !e.isEmpty())
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> get(@PathVariable("id") long id) {
        return baseService.
                findById(id).map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<T> create(@RequestBody @Valid final T commonInfo) {
        return Optional.ofNullable(baseService.save(commonInfo))
                .filter(e -> e.isPresent())
                .map(e -> new ResponseEntity(e, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<T> update(@PathVariable("id") long id,
                                    @RequestBody @Valid T entityObject) {
        return baseService.update(id, entityObject)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<T> delete(@PathVariable("id") long id) {
        baseService.delete(id);
        return new ResponseEntity("Record deleted Successfully.", HttpStatus.OK);
    }

    @RequestMapping(value = "/getcount", method = RequestMethod.GET)
    public Long getCount() {
        return baseService.getCount();
    }
}