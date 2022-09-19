package com.dgyj.yjpicture.domain.picture;

import com.dgyj.yjpicture.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "YJ_TH_PICTURE_HISTORY")
public class PictureHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "picture_history_idx", nullable = false)
    private Long idx;

    @Column(name = "picture_idx", nullable = false)
    private Long picture_idx;

    private String moving_cd;

    private String music_cd;

    private String picture_path;

    private String picture_real_path;

    private String picture_name;

    @Column(name="ORG_REG_ID")
    private String orgRegId;

    @Column(name="ORG_UPD_ID")
    private String orgUpdId;

    @Column(name="ORG_REG_DTIME")
    private LocalDateTime orgRegDtime;

    @Column(name="ORG_UPD_DTIME")
    private LocalDateTime orgUpdDtime;

    @Builder
    public PictureHistory(Long idx, String moving_cd, String music_cd, String picture_path, String picture_real_path, String picture_name, String regId, LocalDateTime regDtime, String updId, LocalDateTime updDtime){

        this.picture_idx = idx;
        this.moving_cd = moving_cd;
        this.music_cd = music_cd;
        this.picture_path = picture_path;
        this.picture_real_path = picture_real_path;
        this.picture_name = picture_name;
        this.orgRegId = regId;
        this.orgUpdId = updId;
        this.orgRegDtime = regDtime;
        this.orgUpdDtime = updDtime;

    }

}
