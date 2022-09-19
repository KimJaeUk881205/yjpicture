package com.dgyj.yjpicture.domain.picture;

import com.dgyj.yjpicture.domain.BaseTimeEntity;
import com.dgyj.yjpicture.domain.listener.PictureEntityListener;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "YJ_TH_PICTURE")
@EntityListeners(value = PictureEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class Picture extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "picture_idx", nullable = false)
    private Long idx;

    @ColumnDefault("0")
    private String moving_cd;

    @ColumnDefault("0")
    private String music_cd;

    private String picture_path;

    private String picture_real_path;

    private String picture_name;

    @Builder
    public Picture(String moving_cd, String music_cd, String picture_name, String picture_real_path, String picture_path){
        this.moving_cd = moving_cd;
        this.music_cd = music_cd;
        this.picture_name = picture_name;
        this.picture_path = picture_path;
        this.picture_real_path = picture_real_path;
    }

}
