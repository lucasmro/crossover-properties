package com.crossover.trial.properties;

public class PropertyFile {

    private String filename;
    private String content;
    private FileFormat format;

    public PropertyFile() {
    }

    public PropertyFile(String filename, String content, FileFormat format) {
        this.filename = filename;
        this.content = content;
        this.format = format;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FileFormat getFormat() {
        return format;
    }

    public void setFormat(FileFormat format) {
        this.format = format;
    }
}
