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
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/memos")
    public String memoList(Model model, HttpServletRequest httpServletRequest) {
        String search = httpServletRequest.getParameter("search");
        List<Memo> memos = memoService.findSearch(search);
        model.addAttribute("searchParam", !(search == null || search.isBlank()));
        model.addAttribute("search", search);
        model.addAttribute("memos", memos);
        return "memo_list";
    }

    //메모 생성
    @GetMapping("/memo")
    public String createForm() {
        return "new_memo";
    }

    @PostMapping("/memo")
    public String createMemo(Memo memo, RedirectAttributes redirectAttributes) {
        Long id = memoService.save(memo);
        redirectAttributes.addAttribute("id", id);
        redirectAttributes.addAttribute("create", true);    //생성했을때 메세지
        return "redirect:/memo/{id}";
    }

    //상세 로직
    @GetMapping("/memo/{id}")
    public String memo(@PathVariable Long id, Model model) {
        Memo memo = memoService.findOne(id);
        model.addAttribute("id", id);
        model.addAttribute("memo", memo);
        return "item/memo";
    }

    //수정 화면
    @GetMapping("/memo/{id}/edit")
    public String editFrom(@PathVariable Long id, Model model) {
        Memo memo = memoService.findOne(id);
        model.addAttribute("id", id);
        model.addAttribute("memo", memo);
        return "edit_memo";
    }

    //수정 로직
    @PostMapping("/memo/{id}/edit")
    public String edit(@PathVariable Long id, MemoDTO memoDTO) {
        memoService.update(id, memoDTO.title(), memoDTO.content());
        return "redirect:/memo/{id}";
    }

    //삭제 로직
    @PostMapping("/memo/{id}/remove")
    public String remove(@PathVariable Long id) {
        memoService.remove(id);
        return "redirect:/memos";
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
