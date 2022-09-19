package com.dgyj.yjpicture.web.picture;

import com.dgyj.yjpicture.service.picture.PictureService;
import com.dgyj.yjpicture.web.dto.PictureSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PictureRestController {

    private final PictureService pictureService;

    @PostMapping("/dg/picture")
    public Long insertPicture(@ModelAttribute PictureSaveDto pictureSaveDto, @RequestParam(value = "picture_img", required = false)MultipartFile imgFile){
        return pictureService.insertPicture(pictureSaveDto, imgFile);
    }

}
