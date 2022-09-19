package com.dgyj.yjpicture.domain.listener;

import com.dgyj.yjpicture.common.entity.BeanUtil;
import com.dgyj.yjpicture.domain.picture.Picture;
import com.dgyj.yjpicture.domain.picture.PictureHistory;
import com.dgyj.yjpicture.domain.picture.PictureHistoryRepository;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class PictureEntityListener {

    @PrePersist
    @PreUpdate
    public void prePersistAndPreUpdate(Object o){

        PictureHistoryRepository pictureHistoryRepository = BeanUtil.getBean(PictureHistoryRepository.class);

        Picture picture = (Picture) o;

        PictureHistory pictureHistory = PictureHistory.builder()
                .idx(picture.getIdx())
                .moving_cd(picture.getMoving_cd())
                .music_cd(picture.getMusic_cd())
                .picture_name(picture.getPicture_name())
                .picture_path(picture.getPicture_path())
                .picture_real_path(picture.getPicture_real_path())
                .regDtime(picture.getRegDtime())
                .regId(picture.getRegId())
                .updDtime(picture.getUpdDtime())
                .updId(picture.getUpdId())
                .build();

        pictureHistoryRepository.save(pictureHistory);

    }
}
