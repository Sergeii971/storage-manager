package com.os.course.service;

import com.os.course.model.dto.DeletedFilesDto;
import com.os.course.model.dto.StorageDto;

import java.util.List;

public interface StorageService {
    StorageDto save(StorageDto storageDto);

    DeletedFilesDto delete(String ids);

    List<StorageDto> getAll();
}
