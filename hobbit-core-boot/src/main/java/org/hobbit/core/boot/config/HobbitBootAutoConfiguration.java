package org.hobbit.core.boot.config;

import org.hobbit.core.context.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import lombok.AllArgsConstructor;

/**
 * hobbit 自动装配
 * 
 * @author lhy
 * @version 1.0.0 2023/10/04
 */
@AutoConfiguration
@AllArgsConstructor
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@PropertySource(value = "classpath:hobbit-boot.yml", factory = YamlPropertySourceFactory.class)
public class HobbitBootAutoConfiguration {

}
