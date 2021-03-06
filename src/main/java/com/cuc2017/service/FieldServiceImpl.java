package com.cuc2017.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuc2017.model.Field;
import com.cuc2017.repository.FieldRepository;

@Service
public class FieldServiceImpl implements FieldService {

  private FieldRepository fieldRepository;

  @Override
  public List<Field> getAllFields() {
    return (List<Field>) getFieldRepository().findAll();
  }

  @Override
  public void changeUsed(long fieldId, boolean toUse) {
    Field field = getFieldRepository().findOne(fieldId);
    field.setUseField(toUse);
    getFieldRepository().save(field);
  }

  @Override
  public Field addField(String fieldName) {
    Field field = new Field(fieldName, "");
    return getFieldRepository().save(field);
  }

  public FieldRepository getFieldRepository() {
    return fieldRepository;
  }

  @Autowired
  public void setFieldRepository(FieldRepository fieldRepository) {
    this.fieldRepository = fieldRepository;
  }

}
