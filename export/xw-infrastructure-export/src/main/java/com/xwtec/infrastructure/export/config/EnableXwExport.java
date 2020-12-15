package com.xwtec.infrastructure.export.config;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SchedulingConfig.class})
public @interface EnableXwExport {
}
