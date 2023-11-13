package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  @GetMapping("hello")
  public String hello(Model model) {    // model -> 데이터를 뷰로 넘김
    model.addAttribute("data", "hello!!!");
    return "hello";
  }
}
