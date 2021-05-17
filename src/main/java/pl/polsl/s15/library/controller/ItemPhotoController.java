package pl.polsl.s15.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.polsl.s15.library.domain.stock.ItemPhoto;
import pl.polsl.s15.library.repository.ItemPhotoRepository;
import pl.polsl.s15.library.service.ItemPhotoService;

import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/library/api/ItemPhoto")
@AllArgsConstructor
public class ItemPhotoController {

    private final ItemPhotoService itemPhotoService;

    @PostMapping
    public Long uploadImage(@RequestPart("file") MultipartFile file) throws Exception {
        return itemPhotoService.createPhoto(file);
    }
    @GetMapping("/{id}")
    @ResponseBody
    public byte[] getImage(@PathVariable Long id)  {
        try {
            return itemPhotoService.findById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
