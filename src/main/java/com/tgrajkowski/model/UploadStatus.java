package com.tgrajkowski.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UploadStatus {
    private boolean isUpload;
    private String message;
    private String exception;

    public UploadStatus(boolean isUpload, String message) {
        this.isUpload = isUpload;
        this.message = message;
    }
}
