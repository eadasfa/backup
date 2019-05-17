package spittr.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import spittr.dao.SpittleRepository;
import spittr.entity.Spittle;

@Service(value="spittleService")
public class SpittleServiceImpl implements SpittleService{

	@Resource
	private SpittleRepository spittleDao;
	
	public List<Spittle> findSpittles(long max, int count) {
		return spittleDao.findAll();
	}

	public Spittle findOne(long spittleId) {
		return spittleDao.findOne(spittleId);
				
	}

}
