package spittr.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

import spittr.service.SpittleService;
import spittr.tools.Tools;

@Component
public class SpittleData implements SpittleService{

	List<Spittle> list = new ArrayList<Spittle>();
	public void initList(){
		for(int i=0;i<5;i++){
			Spittle s = new Spittle(Tools.generateString(), new Date(System.currentTimeMillis()));
			
			list.add(s);
		}
	}
	public SpittleData(){
		initList();
	}
	public List<Spittle> findSpittles(long max, int count) {
		// TODO Auto-generated method stub
		return list;
	}
	
	public Spittle findOne(long spittleId) {
		// TODO Auto-generated method stub
		
		for(Spittle spitter:list){
			if(spitter.getId()==spittleId){
				return spitter;
			}
		}
		return null;
	}
	
	
}
