package com.example.demo.usercrud.controller.web;

import com.example.demo.usercrud.domain.object.User;
import com.example.demo.usercrud.domain.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserControllerWeb {

    @Autowired
    UserService userService;

    /**
     * 一覧画面の表示
     * 
     * @param model Model
     * @return 一覧画面
     */
    @GetMapping(value = "/list")
    public String displayList(Model model) {

        String forwardName = "UserList";
        String modelKey = "userlist";

        // ユーザー情報を全件取得する
        model.addAttribute(modelKey, userService.selectAll());

        return forwardName;
    }

    /**
     * 詳細画面の表示
     * 
     * @param id    表示するユーザーID
     * @param model Model
     * @return 詳細画面
     */
    @GetMapping("{id}")
    public String displayView(Model model, @PathVariable("id") String id) {

        String forwardName = "UserView";
        String modelKey = "userView";

        // 指定したIDに基づくユーザー情報を取得する
        model.addAttribute(modelKey, userService.selectOne(id));

        return forwardName;
    }

    /**
     * 登録画面の表示
     * 
     * @param model Model
     * @return 登録画面
     */
    @GetMapping(value = "/add")
    public String displayAdd(Model model) {

        String forwardName = "UserAdd";
        String modelKey = "newUser";

        // ユーザー内容で新規作成する
        model.addAttribute(modelKey, new User());

        return forwardName;
    }

    /**
     * 登録処理
     * 
     * @param user ユーザー情報
     * @return 一覧画面
     */
    @PostMapping(value = "/create")
    public String create(@ModelAttribute User user) {

        String forwardName = "redirect:/user/list";

        // 指定したユーザー情報を登録する
        userService.insert(user);

        return forwardName;
    }

    /**
     * 変更画面の表示
     * 
     * @param model Model
     * @return 変更画面
     */
    @GetMapping("{id}/edit")
    public String displayEdit(Model model, @ModelAttribute("user") User user, @PathVariable("id") String id) {

        String forwardName = "UserEdit";
        String modelKey = "userEdit";

        // 指定したIDに基づくユーザー情報を取得する
        model.addAttribute(modelKey, userService.selectOne(id));
        return forwardName;
    }

    /**
     * 更新処理
     * 
     * @param model Model
     * @return 一覧画面
     */
    @PutMapping("{id}")
    public String update(@ModelAttribute User user, @PathVariable String id) {

        String forwardName = "redirect:/user/list";

        // 主キーに対する更新のためIDは固定
        user.setUserId(Integer.parseInt(id));

        // 指定した内容で変更する
        userService.updateOne(user);

        return forwardName;
    }

    /**
     * 削除処理
     * 
     * @param model Model
     * @return 一覧画面
     */
    @DeleteMapping("{id}")
    public String delete(@PathVariable String id) {

        String forwardName = "redirect:/user/list";

        // 指定したユーザーを削除する
        userService.deleteOne(id);

        return forwardName;
    }
}
