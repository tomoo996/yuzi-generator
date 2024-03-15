package com.yupi.maker.template.model;

import com.yupi.maker.mate.Meta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class TemplateMakerFileConfig {

    private List<FileInfoConfig> files;

    private FileGroupConfig fileGroupConfig;

    @Data
    @NoArgsConstructor
    public static class FileInfoConfig {
        private String path;

        private List<FileFilterConfig> filterConfigList;
    }

    @Data
    @NoArgsConstructor
    public static class FileGroupConfig {
        private String condition;

        private String groupKey;

        private String groupName;
    }

}
