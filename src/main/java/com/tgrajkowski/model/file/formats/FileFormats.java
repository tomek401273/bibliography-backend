package com.tgrajkowski.model.file.formats;

public enum FileFormats {
    TXT("txt"),
    DOC("doc"),
    DOCX("docx"),
    ODT("ODT");
    private final String fileFormat;

    FileFormats(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Override
    public String toString() {
        return "FileFormats{" +
                "fileFormat='" + fileFormat + '\'' +
                '}';
    }
}
