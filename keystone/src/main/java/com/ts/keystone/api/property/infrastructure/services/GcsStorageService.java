package com.ts.keystone.api.property.infrastructure.services;

import com.ts.keystone.api.property.application.IStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class GcsStorageService implements IStorageService {

    @Override
    public String uploadImage(UUID propertyId, MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            throw new IOException("Failed to store empty file.");
        }
        String fileName = propertyId.toString() + "/" + UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();
        return "https://storage.googleapis.com/your-bucket-name/" + fileName;
    }
}
