package com.dgyj.yjpicture.service.picture;

import com.dgyj.yjpicture.util.FileUtil;
import com.dgyj.yjpicture.web.dto.PictureSaveDto;
import com.dgyj.yjpicture.domain.picture.PictureRepository;
import com.dgyj.yjpicture.domain.picture.PictureRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    @Transactional
    public Long insertPicture(PictureSaveDto pictureSaveDto, MultipartFile file) {

        HashMap<String, Object> filemap = null;
        Long idx = null;
        try{
           filemap = FileUtil.fileUpload("dgpicture",file);

           pictureSaveDto.setPicture_name(filemap.get("name").toString());
           pictureSaveDto.setPicture_path(filemap.get("path").toString());
           pictureSaveDto.setPicture_real_path(filemap.get("realPath").toString());

            idx = pictureRepository.save(pictureSaveDto.toEntity()).getIdx();
        }catch (IOException e){

        }catch( Exception e){

        }

        return idx;
    }
}
