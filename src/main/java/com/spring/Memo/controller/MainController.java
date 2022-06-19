package com.spring.Memo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    //메인 페이지
    @RequestMapping({"/", "","index", "index.html"})
    public String mainPage() {
        return "redirect:/memos";
    }

}
