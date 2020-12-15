package com.xwtec.infrastructure.export.model;

/**
 * @author majunjie
 * @since 9/29/20.
 */
public enum ExportFormat {
    CSV,
    EXCEL;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
