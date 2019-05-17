package spittr.web;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.activation.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spittr.config.DataSourceConfig;
import spittr.entity.Spitter;
import spittr.service.SpitterService;
import spittr.service.SqlHelper;


@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	private static final String MAX_LONG_AS_STRING=Long.MAX_VALUE+"";
	@Autowired
	@Qualifier("spitterService")
	public SpitterService spitterService;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String showRigistrationForm(Model model){
		model.addAttribute(new Spitter());
		return "registerForm";
	}
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String processRegistration(
			@RequestPart("profilePicture") MultipartFile profilePicture,
			@Valid Spitter spitter, //妫�楠岃緭鍏�
			Errors errors,
			RedirectAttributes model){
//		try {
//			System.out.println(Arrays.toString(profilePicture.getBytes()));
//			profilePicture.transferTo(new File("/"+profilePicture.getOriginalFilename()));
//			System.out.println(profilePicture.getOriginalFilename());
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			
		spitterService.addSpitter(spitter);
		model.addAttribute("userName", spitter.getUserName());
		//灏唖pitter澧炲姞鍒癛edirectAttribute涓�
		
		model.addFlashAttribute("spitter", spitter);
		
		
		// 閲嶅畾鍚戝埌鍩烘湰淇℃伅椤�
//		return "redirect:/spitter/"+spitter.getUserName();
		return "redirect:/spitter/{userName}";
	}
	@RequestMapping(value="/{userName}",method=RequestMethod.GET)
	public String showSpitterProfile(
			@PathVariable String userName,RedirectAttributes model){
		
//		model.addAttribute("spitter",spitterService.findByUsername(userName));
		if(!model.containsAttribute("spitter")){
			System.out.println("no");
			Spitter spitter = spitterService.findByUserName(userName);
			System.out.println(spitter);
			try {
				model.addAttribute("spitter",spitter);
			} catch (Exception e) {
			}
			System.out.println("exit");
		}
	
		return "profile";
	}
}
