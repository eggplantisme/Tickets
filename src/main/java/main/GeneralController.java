package main; 


import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import javax.websocket.server.PathParam;

import org.aspectj.weaver.NameMangler;
import org.springframework.stereotype.Controller;    
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bean.Movie;
import bean.User;
import dao.MovieDao;
import dao.UserDao;    
    
@Controller
public class GeneralController {

    @RequestMapping(value={"/index", "/"})
    public String index_jsp(Model model,  HttpSession session){
        model.addAttribute("name", session.getAttribute("username"));
        return "index";
    }
    //登录
    @RequestMapping(value = "/login", method = RequestMethod.GET)    
    public String login_jsp(Model model){
    	return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)    
    public String login(User user,  HttpSession session) {
    	//session.setAttribute("phoneNumber", user.getPhoneNumber());
    	//ajax异步访问来确定数据库是否有重复用户，TODO
    	
    	session.setAttribute("username", user.getUsername());
    	return "redirect:index";
    }
    
    //注册
    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    public String regist_jsp(Model model){
    	return "regist";
    }
    @RequestMapping(value="/regist", method = RequestMethod.POST)
    public String regist(User user, Model model, HttpSession session) {
    	//没有做重复名字的处理
    	System.out.println(user.getUsername() + "-" + user.getPassword() + "-" + user.getPhoneNumber());
    	UserDao userDao = new UserDao();
        userDao.addUser(user);
        session.setAttribute("username", user.getUsername());
    	return "redirect:index";
    }
    
    //用户详情
    @RequestMapping(value="/info")
    public String info_jsp(Model model,  HttpSession session) {
        model.addAttribute("name", session.getAttribute("username"));
        return "info";
    }
    @RequestMapping(value="/info", method = RequestMethod.POST)
    public String info_(HttpServletRequest request,  HttpSession session, Model model) {
    	System.out.println(request.getParameter("name"));
    	UserDao userDao = new UserDao();
        userDao.UpdateUserName(request.getParameter("name"), (String)session.getAttribute("username"));
        session.setAttribute("username", request.getParameter("name"));
        model.addAttribute("name", session.getAttribute("username"));
        return "redirect:info";
    }

    //电影详情
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)    
    public String movie_jsp(@PathVariable int id, Model model, HttpSession session) {
    	MovieDao movieDao = new MovieDao();
    	Movie movie = movieDao.GetMovieFromID(id);
    	model.addAttribute("movie", movie);
    	model.addAttribute("name", session.getAttribute("username"));
    	return "movie";
    }
}