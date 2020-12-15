package com.xwtec.infrastructure.export.dao;


import com.xwtec.infrastructure.export.model.ExportTaskInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ExportTaskMapper {
    int insert(final ExportTaskInfo task);
    int update(final ExportTaskInfo task);
    List<ExportTaskInfo> query(final ExportTaskInfo task);
    List<ExportTaskInfo> queryTasks(@Param("retryNum") int retryNum,
                                    @Param("states") int ... states);
}
