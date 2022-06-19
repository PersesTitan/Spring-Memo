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

    @RequestMapping("index")
    public String mainPage(Model model) {
        List<Memo> memos = memoService.findAll();
        memos.add(Memo.createMemo("a", "A"));
        memos.add(Memo.createMemo("b", "B"));
        model.addAttribute("memos", memos);
        return "index";
    }

    @RequestMapping("list")
    public String memoList(Model model) {
        List<Memo> memos = memoService.findAll();
        memos.add(Memo.createMemo("a", "A"));
        memos.add(Memo.createMemo("b", "B"));
        model.addAttribute("memos", memos);
        return "memo_list";
    }

    @RequestMapping("create")
    public String create(Model model, MemoDTO memoDTO) {
        model.getAttribute("");
        Memo memo = Memo.createMemo(memoDTO.title(), memoDTO.content());

        return "new_list";
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

    @DeleteMapping("memo/{id}")
    public Long remove(@PathVariable Long id) {
        memoService.remove(id);
        return id;
    }

    @PutMapping("memo/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemoDTO memoDTO) {
        memoService.update(id, memoDTO.title(), memoDTO.content());
        return id;
    }
}
