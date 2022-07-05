package com.hexiang.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: 何翔
 * @date: 2022/7/2
 * @description:
 */

@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {
    private String dateFormat;
    private String envSharedValue;
}
