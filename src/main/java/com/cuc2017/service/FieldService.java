package com.cuc2017.service;

import java.util.List;

import com.cuc2017.model.Field;

public interface FieldService {

  List<Field> getAllFields();

  void changeUsed(long fieldId, boolean toUse);
}
