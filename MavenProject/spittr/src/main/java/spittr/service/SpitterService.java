package spittr.service;

import spittr.entity.Spitter;

public interface SpitterService {

	Spitter findByUserName(String userName);
	void addSpitter(Spitter spitter);
}
