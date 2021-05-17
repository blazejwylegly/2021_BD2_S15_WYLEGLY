package pl.polsl.s15.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.polsl.s15.library.domain.stock.ItemPhoto;
import pl.polsl.s15.library.domain.stock.books.Book;
import pl.polsl.s15.library.repository.ItemPhotoRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
@AllArgsConstructor
public class ItemPhotoService {

    private ItemPhotoRepository itemPhotoRepository;

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    public List<byte[]>convertBufferedImageToByte(List<BufferedImage> bufferedImageList){
        return bufferedImageList.stream().map(bufferedImage -> convertBufferedImageToByte(bufferedImage)).collect(Collectors.toList());
    }

    public byte[] convertBufferedImageToByte(BufferedImage bufferedImage)  {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
    public java.util.List<BufferedImage> getPhotos(Book book){
        final java.util.List<byte[]> list = book.getPhotos().stream().map(ItemPhoto::getFile).collect(Collectors.toList());
        final List<BufferedImage> listOfPhotos = new ArrayList<>();
        for (byte [] photo : list){
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(photo);
                BufferedImage originalImage = ImageIO.read(bis);
                listOfPhotos.add(originalImage);
            } catch (IOException ex) {
            }
        }
        return listOfPhotos;
    }

    public Long createPhoto(MultipartFile file) throws IOException {
        ItemPhoto itemPhoto = new ItemPhoto();
        itemPhoto.setFile(file.getBytes());
        return itemPhotoRepository.save(itemPhoto).getId();
    }

    public byte[] findById(Long id) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(itemPhotoRepository.findById(id).get().getFile());
        BufferedImage originalImage = ImageIO.read(bis);
        return itemPhotoRepository.findById(id).get().getFile();
    }
}
