package com.spring.Memo.controller;

import com.spring.Memo.domain.Memo;
import com.spring.Memo.domain.MemoDTO;
import com.spring.Memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    //메인 페이지
    @RequestMapping({"/", "","index", "index.html"})
    public String mainPage() {
        return "redirect:/list";
    }

    @RequestMapping({"list", "memo_list", "memo_list.html"})
    public String memoList(Model model) {
        List<Memo> memos = memoService.findAll();
        model.addAttribute("memos", memos);
        return "memo_list";
    }

    //메모 생성
    @GetMapping("add")
    public String createForm() {
        return "new_memo";
    }

    @PostMapping("add")
    public String createMemo(Memo memo, RedirectAttributes redirectAttributes) {
        Long id = memoService.save(memo);
        redirectAttributes.addAttribute("id", id);
        redirectAttributes.addAttribute("create", true);    //생성했을때 메세지
        return "redirect:/memo/{id}";
    }

    //수정 로직
    @GetMapping("memo/{id}")
    public String memo(@PathVariable Long id, Model model) {
        Memo memo = memoService.findOne(id);
        model.addAttribute("title", memo.getTitle());
        model.addAttribute("content", memo.getContent());
        return "item/memo";
    }

    //삭제 로직
    @DeleteMapping("memo/{id}")
    public Long remove(@PathVariable Long id) {
        memoService.remove(id);
        return id;
    }

    //수정 로직
    @GetMapping("edit/{id}")
    public String editFrom(@PathVariable Long id, Model model) {
        Memo memo = memoService.findOne(id);
        model.addAttribute("memo", memo);
        return "edit_memo";
    }

    @PostMapping("edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute MemoDTO memoDTO) {
        memoService.update(id, memoDTO.title(), memoDTO.content());
        return "redirect:/memo/{id}";
    }

    //테스트용 생성 로직
    @PostConstruct
    public void init() {
        Memo memo1 = Memo.createMemo("a", "A");
        Memo memo2 = Memo.createMemo("b", "B");
        
        memoService.save(memo1);
        memoService.save(memo2);
    }
}
