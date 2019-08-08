package com.cuc2017.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

  @RequestMapping(value = "field" + "/{fieldId}", params = { "toUse" }, method = RequestMethod.POST)
  public ResponseEntity<?> showFields(Model model, @PathVariable("fieldId") long fieldId,
      @RequestParam(value = "toUse", required = true) boolean toUse, HttpServletRequest request) {
    try {
      getFieldService().changeUsed(fieldId, toUse);
      log.info("Change field: " + fieldId + " to: " + toUse);
      return ResponseEntity.accepted().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  public FieldService getFieldService() {
    return fieldService;
  }

  @Autowired
  public void setFieldService(FieldService fieldService) {
    this.fieldService = fieldService;
  }

}