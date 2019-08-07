package com.cuc2017.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cuc2017.model.Field;
import com.cuc2017.service.FieldService;

@Controller
public class FieldsController {
  private static final Logger log = LoggerFactory.getLogger(FieldsController.class);

  private FieldService fieldService;

  @RequestMapping(value = "fields", method = RequestMethod.GET)
  public String showFields(Model model, HttpServletRequest request) {
    List<Field> fields = getFieldService().getAllFields();
    model.addAttribute("fields", fields);
    return "fields";

  }

  public FieldService getFieldService() {
    return fieldService;
  }

  @Autowired
  public void setFieldService(FieldService fieldService) {
    this.fieldService = fieldService;
  }

}