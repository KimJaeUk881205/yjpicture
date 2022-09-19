package com.dgyj.yjpicture.web.dto;

import com.dgyj.yjpicture.domain.picture.Picture;
import lombok.Getter;

@Getter
public class PictureResponseDto {

    private Long idx;
    private String moving_cd;
    private String music_cd;
    private String picture_path;
    private String picture_real_path;
    private String picture_name;

    public PictureResponseDto(Picture entity){
        this.idx = entity.getIdx();
        this.moving_cd = entity.getMoving_cd();
        this.music_cd = entity.getMusic_cd();
        this.picture_name = entity.getPicture_name();
        this.picture_path = entity.getPicture_path();
        this.picture_real_path = entity.getPicture_real_path();
    }

}
