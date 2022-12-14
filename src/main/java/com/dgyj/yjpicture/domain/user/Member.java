package com.dgyj.yjpicture.domain.user;

import com.dgyj.yjpicture.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "YJ_TH_MEMBER")
public class Member extends BaseTimeEntity {
//https://asbnotebook.com/spring-boot-thymeleaf-form-validation-example/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID", nullable = false)
    private Integer id;

    @Column(name = "AUTH", length = 10)
    private String auth;

    @Column(name = "LOGIN_ID", nullable = false, length = 16)
    private String loginId;

    @Column(name = "NAME", length = 150)
    private String name;

    @Column(name = "PASSWORD", columnDefinition = "TEXT")
    private String password;

    @Column(name = "EMAIL_ID", length = 50)
    private String emailId;

    @Column(name = "EMAIL_DOMAIN", length = 50)
    private String emailDomain;

    @Column(name = "PNUM", length = 13)
    private String pnum;

    @Column(name = "RANKA", length = 30)
    private String rank;

    @Column(name = "USE_YN", length = 1)
    private String useYn;

    @Column(name = "MAIN_AUTHORITY", length = 1)
    private String mainAuthority;

    @Column(name = "NOTICE_AUTHORITY", length = 1)
    private String noticeAuthority;

    @Column(name = "LOG_AUTHORITY", length = 1)
    private String logAuthority;

    @Column(name = "LOGIN_LOCK")
    private String blocked;

    @Column(name = "LOGIN_CNT")
    private Integer failCount;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOGIN_DATE")
    private LocalDateTime loginDate;

    @Column(name = "PASSWORD_CHANGE_DATE")
    private LocalDateTime passwordChangeDate;

    @Transient
    private String mPassword;


//    @Builder
//    public Member(String auth, String loginId, String name, String password, String emailId,String emailDomain, String pnum, String rank, String useYn, String mainAuthority,String noticeAuthority){
//        this.auth = auth;
//        this.loginId = loginId;
//        this.name = name;
//        this.password = password;
//        this.emailId = emailId;
//        this.emailDomain = emailDomain;
//        this.pnum = pnum;
//        this.rank = rank;
//        this.useYn = useYn;
//        this.mainAuthority = mainAuthority;
//        this.noticeAuthority = noticeAuthority;
//
//    }
}
