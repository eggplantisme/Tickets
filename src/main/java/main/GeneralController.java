package main; 


import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;    
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bean.User;
import dao.UserDao;    
    
@Controller
//@SessionAttributes("username")
public class GeneralController {

    @RequestMapping(value={"/index", "/"})
    public String index_jsp(Model model){
        model.addAttribute("str0121", "Hellow world");    
        return "index";   
    }  
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)    
    public String login_jsp(Model model){   
        //System.out.println("login.jsp");
    	return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)    
    public String login(User user,  HttpSession session){
    	session.setAttribute("username", user.getUsername());
        System.out.println(user.getUsername() + "-" + user.getPassword());
        UserDao userDao = new UserDao();
        userDao.addUser(user);
    	return "redirect:welcome";
    }
    @RequestMapping(value="/regist")
    public String regist_jsp(Model model){   
        //System.out.println("regist.jsp");
    	return "regist";
    }
    @RequestMapping(value="/welcome")
    public String welcome_jsp(Model model, HttpSession session){  
        //System.out.println("regist.jsp");
    	
    	model.addAttribute("username", session.getAttribute("username"));
    	return "welcome";
    }
}    