package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*@Controller
@RequestMapping("/login")*/
public class LoginController {
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value="error",required=false) String error,
			@RequestParam(value="logout",required=false) String logout){
		System.out.println("LLLLLLLLLLLLL");
		ModelAndView model = null;
		try {
			model = new ModelAndView("login");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(error!=null){
			model.addObject("error","用户名和密码错误！");
		}
		if(logout!=null){
			model.addObject("msg","你已经成功退出！");
		}
		return model;
	}
}
