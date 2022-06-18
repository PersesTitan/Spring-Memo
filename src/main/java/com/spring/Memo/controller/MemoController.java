package com.spring.Memo.controller;

import com.spring.Memo.domain.Memo;
import com.spring.Memo.domain.MemoDTO;
import com.spring.Memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("index")
    public String memoList(Model model) {
        List<Memo> memos = memoService.findAll();

        memos.add(Memo.createMemo("a", "b"));
        memos.add(Memo.createMemo("a", "b"));
        System.out.println(memos);
        model.addAttribute("memos", memos);
        return "/";
    }

//    @GetMapping("save")
//    public String save() {
//        memoService.save(Memo.createMemo("a", "a"));
//        memoService.save(Memo.createMemo("b", "b"));
//        System.out.println(memoService.findAll());
//        return "redirect:/";
//    }

    @GetMapping("memo/{id}")
    public String memo(@PathVariable Long id, Model model) {
        Memo memo = memoService.findOne(id);
        model.addAttribute("id", memo);
        return "item/memo";
    }
}
