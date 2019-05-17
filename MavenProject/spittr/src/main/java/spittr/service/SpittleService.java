package spittr.service;

import java.util.List;

import spittr.entity.Spittle;

public interface SpittleService {
	List<Spittle> findSpittles(long max, int count);
	Spittle findOne(long spittleId);
}
