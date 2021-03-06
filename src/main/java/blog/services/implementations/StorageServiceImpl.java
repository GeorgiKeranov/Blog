package blog.services.implementations;

import blog.models.Image;
import blog.models.User;
import blog.repositories.ImageRepository;
import blog.repositories.UserRepository;
import blog.services.interfaces.StorageService;
import blog.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageRepository imageRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public String savePostImage(MultipartFile file) {

        try {
            // Creating unique name for the image.
            String nameOfImage = new Date() + file.getOriginalFilename();

            Path location = Paths.get(postsImgsDirectory);
            Files.copy(file.getInputStream(), location.resolve(nameOfImage));
            return nameOfImage;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String saveUserImage(MultipartFile file) {

        try {
            // Getting the authenticated user.
            User authUser = userService.getAuthenticatedUser();

            // Making directory for images for that user url from db.
            String locationForSave = usersImgsDirectory + authUser.getUserUrl();

            // Creating unique name for the image.
            String nameOfImage = new Date() + file.getOriginalFilename();

            Path location = Paths.get(locationForSave);
            if(!Files.exists(location)) Files.createDirectory(location);

            Files.copy(file.getInputStream(), location.resolve(nameOfImage));

            Image image = new Image();
            image.setOriginalName(file.getOriginalFilename());
            image.setImgName(nameOfImage);
            image.setUser(authUser);
            imageRepo.save(image);

            return "Successful saved";

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error, please try again";
    }

    @Override
    public List<Image> getUserImages() {

        User authUser = userService.getAuthenticatedUser();

        Long user_id = authUser.getId();
        List<Image> images = imageRepo.findByUser_id(user_id);
        if(images.isEmpty()) return null;

        return images;
    }

    @Override
    public Image ImageUserById(Long id) {

        return imageRepo.findOne(id);
    }

    @Override
    public boolean saveProfilePicture(MultipartFile file) {

        try {
            User authUser = userService.getAuthenticatedUser();

            // Making directory for profile picture for that username.
            String locationForSave = usersImgsDirectory + authUser.getUserUrl() + "/";
            String nameOfImage = new Date() + file.getOriginalFilename();

            Path location = Paths.get(locationForSave);
            if(!Files.exists(location)) Files.createDirectory(location);

            Files.copy(file.getInputStream(), location.resolve(nameOfImage));

            // Saves the image in images table.
            // If this profile picture is changed. Last profile picture wont be deleted.
            Image image = new Image();
            image.setOriginalName(file.getOriginalFilename());
            image.setImgName(nameOfImage);
            image.setUser(authUser);
            imageRepo.save(image);

            // Saves profile picture name in user table.
            authUser.setProfile_picture(nameOfImage);
            userRepo.save(authUser);

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void deleteUserImageById(Long id, String location) throws IOException {

        User authUser = userService.getAuthenticatedUser();
        Path locForDelete = Paths.get(usersImgsDirectory + authUser.getUserUrl());

        if(id == null){
            Files.deleteIfExists(locForDelete.resolve(location));
            return;
        }

        if(location == null) {
            String pictureName = imageRepo.getOne(id).getImgName();
            Files.deleteIfExists(locForDelete.resolve(pictureName));
            imageRepo.delete(id);
        }
    }

    @Override
    public void deletePostImage(String directory) throws IOException {

        Path path = Paths.get(postsImgsDirectory);
        Files.delete(path.resolve(directory));

    }

}
