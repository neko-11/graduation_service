package cn.edu.ahut.Mappers;

import cn.edu.ahut.Entity.Record;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RecordMapper extends Mapper<Record> {

    List<Record> listRecord(Map map);

    Record getRecordById(int id);

    int updateRecord(Map map);
}