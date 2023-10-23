package com.xw.consistency.enable;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ConsistencyTaskImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                ComponentScanConfig.class.getName(),
                ScheduleConfig.class.getName()};
    }

}
