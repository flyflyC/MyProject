package com.fly.controller;


import cn.dsna.util.images.ValidateCode;
import com.fly.pojo.User;
import com.fly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.List;


/**
 * @description:
 * @author: caifeifei
 * @createDate: 2020-9-24
 * @version: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/allUser")
    public String ListUser(Model model) {
        List<User> users = userService.selectAllUser();
        model.addAttribute("users", users);
        return "/user/showUser";
    }

    @RequestMapping("/toAddUser")
    public String toAddUser() {
        return "/user/addUser";
    }

    @RequestMapping("/addUser")
    public String AddUser(User user) {
        System.out.println(user);
        userService.addUser(user);
        return "redirect:/user/pageAllUser/0";
    }

    @RequestMapping(value = "/validate")
    @ResponseBody
    public void getValidate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //设置为图像模式
        resp.setContentType("image/jpeg");
        //禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        //180高，40宽，5个数字，50干扰线
        ValidateCode verifyCode = new ValidateCode(180,40,4,50);
        verifyCode.write(resp.getOutputStream());

        System.out.println("验证码为："+verifyCode.getCode());
        //将验证码保存在session中
        req.getSession().setAttribute("verifyCodeValue", verifyCode.getCode());
    }
   /* @RequestMapping("/login")
    public String login(@PathVariable("user_name") String user_name,@PathVariable("user_password") String user_password,
                       @PathVariable("vercode") String vercode, Model model){
        User user =userSercice.selectUserByname(user_name);
        System.out.println(user_name+"===="+user_password);
        if (user!=null && user.getUser_password().equals(user_password)){
            List<User> users = userSercice.selectAllUser();
            model.addAttribute("users", users);
            return "/user/showUser";
        }else {
            System.out.println("账户或密码错误！");
            model.addAttribute("msg","账户或密码错误！");
            return "/log_reg/login";
        }
    }*/
    // 登录功能
    @RequestMapping(value = "/loginPage")
    public String loginCon(HttpServletRequest request,HttpServletResponse response,Model model) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 取参数的方法，对应登录表单中的用户名name="user_name"
        String user_name = request.getParameter("user_name");
        String user_password = request.getParameter("user_password");
        System.out.println("user_name:" + user_name + "  user_password:" + user_password);

        // 从Session中获取验证码
        String verifyCodeValue = (String) request.getSession().getAttribute("verifyCodeValue");
        String code = request.getParameter("vercode");
        System.out.println("页面输入的验证码：" + code);
        System.out.println("前台写入session中的验证码：" + verifyCodeValue);

        if(!code.equals(verifyCodeValue)){
            String statusMsg = "验证码输入错误！";
            Integer statusCode = 201;
            model.addAttribute("msg","账户或密码错误！");
            return "/log_reg/login";
        }

        // 调用mapper层的登录的方法，从数据库中匹配用户名和密码，并放回用户名
        List<User> user1=  userService.QueryUserByName(user_name);
        System.out.println(user1.toString());
        User user = userService.selectUserByName(user_name);

        session.setAttribute("loginUser",user.getUser_name());
        System.out.println("user_name:" + user.getUser_name() + "user_password:" + user.getUser_password());
        if (user!=null && user.getUser_password().equals(user_password)){
            List<User> users = userService.selectAllUser();
            model.addAttribute("users", users);
            return "redirect:/user/pageAllUser/0";
        }else {
            System.out.println("账户或密码错误！");
            model.addAttribute("msg","账户或密码错误！");
            return "/log_reg/login";
        }
    }
    @RequestMapping("/toUpdateUser/{user_id}")
    public String toUpdateUser(Model model, @PathVariable("user_id") int id) {
        User user = userService.selectUserById(id);
        System.out.println(user);
        model.addAttribute("user", user);
        return "/user/updateUser";
    }

    @RequestMapping("/updateUser")
    public String updateUser(Model model, User user) {
        System.out.println(user);
        userService.updateUserById(user);
        User user1 = userService.selectUserById(user.getUser_id());
        model.addAttribute("user1", user1);
        return "redirect:/user/pageAllUser/0";
    }

    @RequestMapping("/deleteUser/{user_id}")
    public String deleteUser(@PathVariable("user_id") int id) {
        userService.deleteUserById(id);
        return "redirect:/user/pageAllUser/0";
    }

    @RequestMapping("/queryUserByName")
    public String QueryUserByName(String user_name, Model model) {
        System.out.println(user_name);
        List<User> userList = userService.QueryUserByName(user_name);
        System.out.println(userList);
        if (userList.size() == 0) {
            model.addAttribute("msg", "不存在" + user_name + "的账户");
        } else {
            model.addAttribute("users", userList);
        }
        return "/user/showUser";
    }

    @RequestMapping("/pageAllUser/{pageNumber}")
    public String PageUser(Model model, @PathVariable("pageNumber") String pageNumber) {
        String spPage = pageNumber;
        //设置每页条数
        int pageSize = 10;
        //页数，对页数进行判断设置
        int pageNo = 0;
        if (spPage == null) {
            pageNo = 1;
        } else {
            pageNo = Integer.valueOf(spPage);
            if (pageNo < 1) {
                pageNo = 1;
            }
        }
        //设置首页
        int minPage = 1;
        //设置最大页数
        List<User> userList = userService.selectAllUser();
        int totalCount = userList.size();    //获取总条数
        int maxPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        if (pageNo > maxPage) {
            pageNo = maxPage;
        }
        try {
            //分页查询
            List<User> users = userService.queryUserPage(pageNo, pageSize);
            //把信息放入model转发到页面
            model.addAttribute("users", users);
            model.addAttribute("pageNo", pageNo);
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("maxPage", maxPage);
            model.addAttribute("minPage", minPage);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "/user/showUser";
    }
}
