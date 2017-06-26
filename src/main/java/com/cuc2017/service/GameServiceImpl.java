package com.cuc2017.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuc2017.model.Field;
import com.cuc2017.model.Game;
import com.cuc2017.repository.FieldRepository;
import com.cuc2017.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {

	private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

	private FieldRepository fieldRepository;
	private GameRepository gameRepository;

	@Override
	public Game getGame(Long fieldId) {
		Field field = getFieldRepository().findOne(fieldId);
		log.info("Field is:" + field);
		return getGameRepository().findByField(field);
	}

	public FieldRepository getFieldRepository() {
		return fieldRepository;
	}

	@Autowired
	public void setFieldRepository(FieldRepository fieldRepository) {
		this.fieldRepository = fieldRepository;
	}

	public GameRepository getGameRepository() {
		return gameRepository;
	}

	@Autowired
	public void setGameRepository(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

}
