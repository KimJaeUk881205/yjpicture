package com.dgyj.yjpicture.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
    private String fromAddress;
    private String toAddress;
    private String title;
    private String message;
    private File file;
    private String qnaCate;
    private String regDtime;
    private String subject;
    private String answerContents;
    private String answerDate;
    private String serverProfile;
    private String answerTitle;
    private String userName;
    private String nlString;
}
