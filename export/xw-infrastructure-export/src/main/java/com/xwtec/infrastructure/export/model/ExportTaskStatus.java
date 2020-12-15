package com.xwtec.infrastructure.export.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author majunjie
 * @since 10/9/20.
 */
public enum ExportTaskStatus {
    INIT(0),
    RUN(1),
    ERROR(2),
    OK(4);

    private ExportTaskStatus(int status) {
        this._status = status;
    }

    private static final Map<Integer, ExportTaskStatus> _m = new HashMap<>(8);
    static {
        for (ExportTaskStatus status : ExportTaskStatus.values()) {
            _m.put(status.val(), status);
        }
    }

    public int val() {
        return _status;
    }

    public static ExportTaskStatus valueOf(int status) {
        return _m.get(status);
    }

    public static String name(int status) {
        return name(valueOf(status));
    }

    public static String name(ExportTaskStatus status) {
        switch (status) {
            case INIT: return "待开始";
            case RUN: return "开始";
            case ERROR: return "失败";
            case OK: return "成功";
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
    }

    private final int _status;
}
