package spittr.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import spittr.dao.SpitterRepository;
import spittr.entity.Spitter;

@Service(value="spitterService")
public class SpitterServiceImpl implements SpitterService{

	@Resource
	private SpitterRepository spitterDao;
	
	public Spitter findByUserName(String username) {
//		return spitterDao.findOne(username);
		return spitterDao.findByUserNameBinary(username);
	}

	public void addSpitter(Spitter spitter) {
		spitterDao.save(spitter);
	}


}
