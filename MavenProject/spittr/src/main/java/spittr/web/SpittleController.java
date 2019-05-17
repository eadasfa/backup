package spittr.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spittr.entity.Spittle;
import spittr.exception.SpittleNotFoundException;
import spittr.service.SpittleService;


@Controller
@RequestMapping("/spittles")
public class SpittleController {
	
	private static final String MAX_LONG_AS_STRING=Long.MAX_VALUE+"";
	@Autowired
	@Qualifier("spittleService")
	public SpittleService spittleService;
	
	@Autowired
	public SpittleController(
			SpittleService spittleService){
		this.spittleService = spittleService;
	}
	@RequestMapping(method=RequestMethod.GET)
	public String spittles(Model model){
		
		model.addAttribute("spittleList",
				spittleService.findSpittles(Long.MAX_VALUE,20));
		return "spittles";
	}
//	@RequestMapping(method=RequestMethod.GET)
//	public List<Spittle> spittles(
//			@RequestParam(value="max",defaultValue=MAX_LONG_AS_STRING) long max,
//			@RequestParam(value="count",defaultValue="20") int count){
//		return spittleService.findSpittles(max, count);
//	}
	@RequestMapping(value="/{spittleId}",method=RequestMethod.GET)
	public String spittle(
			@PathVariable long spittleId,
			Model model){
		
		Spittle spittle = spittleService.findOne(spittleId);
		if(spittle == null){
			throw new SpittleNotFoundException();
		}
		model.addAttribute(spittle);	
		return "spittle";
	}
}
