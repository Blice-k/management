package com.library.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // 返回login.html模板
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password) {
        // 处理登录逻辑
        return "redirect:/dashboard"; // 登录成功后重定向
    }
}