package com.spring.Memo;

import com.spring.Memo.domain.Memo;
import com.spring.Memo.service.MemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemoApplicationTests {

	@Autowired
	private MemoService memoService;

	@Test
	void contextLoads() {
		//given
		Memo memo = Memo.createMemo("A", "a");
		Memo memo1 = Memo.createMemo("B", "b");

		//when
		memoService.save(memo);
		memoService.save(memo1);

		//then
	}

}
