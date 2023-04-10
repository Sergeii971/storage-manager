package com.os.course.web.controller;

import com.os.course.model.dto.DeletedFilesDto;
import com.os.course.model.dto.StorageDto;
import com.os.course.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("/v1/storages")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public StorageDto save(@RequestBody StorageDto storageDto) {
        return storageService.save(storageDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<StorageDto> getAll() {
        return storageService.getAll();
    }

    @RequestMapping(method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DeletedFilesDto remove(@RequestParam(value = "id") @Max(200) String ids) {
        return storageService.delete(ids);
    }
}
