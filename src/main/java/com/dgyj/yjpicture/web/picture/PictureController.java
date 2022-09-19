package com.dgyj.yjpicture.web.picture;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PictureController {

    @RequestMapping("/picture/pictureInfo")
    public String pictureInfo(){return "content/picture/pictureInfo";}
}
