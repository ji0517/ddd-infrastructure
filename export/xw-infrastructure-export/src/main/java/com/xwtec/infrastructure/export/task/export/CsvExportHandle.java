package com.xwtec.infrastructure.export.task.export;

import com.xwtec.infrastructure.export.config.ExportProperties;
import com.xwtec.infrastructure.export.model.ExportTaskInfo;
import com.xwtec.infrastructure.export.model.PrintFormat;
import com.xwtec.infrastructure.export.task.ExportException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Component("csvExportHandle")
public class CsvExportHandle implements IExportHandle, ResultHandler<LinkedHashMap<String, Object>> {

    private final Logger logger = LoggerFactory.getLogger(CsvExportHandle.class);

    private ExportTaskInfo taskInfo;

    private CSVPrinter printer;

//    public CsvExportHandle() {
//        // do nothing
//    }

    @Override
    public void init(final ExportTaskInfo taskInfo, final ExportProperties properties) {
        if (null == taskInfo) {
            throw new ExportException("Invalid argument: taskInfo");
        }
        if (null == properties) {
            throw new ExportException("Invalid argument: properties");
        }
        this.taskInfo = taskInfo;

        if (StringUtils.isEmpty(taskInfo.getExportFileName())) {
            throw new ExportException("Invalid exportFileName");
        }
        if (StringUtils.isEmpty(properties.getFilePath())) {
            throw new ExportException("Invalid filePath");
        }

        CSVFormat csvFormat = CSVFormat.EXCEL;
        if (!StringUtils.isEmpty(taskInfo.getPrintFormat())) {
            if (PrintFormat.HEADER == PrintFormat.valueOf(taskInfo.getPrintFormat())) {
                csvFormat = csvFormat.withHeader(taskInfo.getSqlSelected().split(","));
            }
        }

        logger.info(csvFormat.toString());

        final Path path = Paths.get(properties.getFilePath(), taskInfo.getExportFileName());
        try {
            this.printer = new CSVPrinter(new FileWriter(path.toFile(), false), csvFormat);


        } catch (final IOException e) {
            throw new ExportException(e);
        }
    }

    @Override
    public void close() throws ExportException {
        if (this.printer != null) {
            try {
                this.printer.close(true);
            } catch (final IOException e) {
                throw new ExportException(e);
            }
        }

    }

    @Override
    public void export(SqlSessionTemplate sqlSessionTemplate, String sqlExportTemplate) {
        if (null == sqlSessionTemplate) {
            throw new ExportException("Invalid sqlSessionTemplate");
        }
        sqlSessionTemplate.select(sqlExportTemplate,
                this.taskInfo.getSqlStatement(),
                this);
    }

    @Override
    public void handleResult(ResultContext<? extends LinkedHashMap<String, Object>> resultContext) {
        try {
            final LinkedHashMap<String, Object> map = resultContext.getResultObject();
            printer.printRecord(map.values().stream().map(m1 ->
                    m1 == null ? "" : m1.toString()).collect(Collectors.toList()));
        } catch (final Exception e) {
            throw new ExportException(e);
        }
    }

}
