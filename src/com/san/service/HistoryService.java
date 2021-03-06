package com.san.service;

import com.san.model.Grade;
import com.san.model.UseRecord;

import java.util.List;

public interface HistoryService {

     List<UseRecord> listUseRecord(int userId);
     List<UseRecord> listResourceUseRecord(int userId);
     List<Grade> listGradeByUser(int userId);
}
