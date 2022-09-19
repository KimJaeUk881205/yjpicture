package com.dgyj.yjpicture.web.dto;

import com.dgyj.yjpicture.domain.picture.Picture;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
public class PictureSaveDto {

    //@NotEmpty(message = "영상은 필수입니다.")
    private String moving_cd;


    //@NotEmpty(message = "음악은 필수입니다.")
    private String music_cd;


    //@NotEmpty(message = "이미지경로는 필수입니다.")
    private String picture_path;


    //@NotEmpty(message = "이미지실제경로는 필수입니다.")
    private String picture_real_path;


    //@NotEmpty(message = "이미지명은 필수입니다.")
    private String picture_name;

    //싱귤러 사용시 빌드시에  list를 한번에 추가하는게 아니라 add처럼 한개씩 빌드 가능하다.
//    @Singular
//    private List<Integer> score;

    public Picture toEntity(){
        return Picture.builder()
                .moving_cd(moving_cd)
                .music_cd(music_cd)
                .picture_name(picture_name)
                .picture_path(picture_path)
                .picture_real_path(picture_real_path)
                .build();
    }


}
