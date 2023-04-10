package com.os.course.service.impl;

import com.os.course.model.dto.DeletedFilesDto;
import com.os.course.model.dto.StorageDto;
import com.os.course.model.entity.Storage;
import com.os.course.persistent.repository.StorageRepository;
import com.os.course.service.StorageService;
import com.os.course.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;
    private final ModelMapper modelMapper;

    public StorageServiceImpl(StorageRepository storageRepository, ModelMapper modelMapper) {
        this.storageRepository = storageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StorageDto save(StorageDto storageDto) {
        Storage storage = modelMapper.map(storageDto, Storage.class);
        storage = storageRepository.save(storage);
        return modelMapper.map(storage, StorageDto.class);
    }

    @Override
    public DeletedFilesDto delete(String ids) {
        DeletedFilesDto deletedFilesDto = new DeletedFilesDto();

        deletedFilesDto.setIds(Arrays.stream(ids.split(Constant.COMMA_SEPARATOR))
                .filter(id -> id.matches("[0-9]"))
                .map(id -> storageRepository.findById(Long.parseLong(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(songMetadata -> !songMetadata.isDeleted())
                .peek(file -> file.setDeleted(true))
                .map(storageRepository::save)
                .map(Storage::getId)
                .collect(Collectors.toList()));

        return deletedFilesDto;
    }

    @Override
    public List<StorageDto> getAll() {
        return storageRepository.findAll()
                .stream()
                .map(file -> modelMapper.map(file, StorageDto.class))
                .collect(Collectors.toList());
    }
}
