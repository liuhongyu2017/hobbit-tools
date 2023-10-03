package org.hobbit.core.launch.config;

import org.hobbit.core.launch.props.HobbitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author lhy
 * @version 1.0.0 2023/10/03
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(HobbitProperties.class)
public class HobbitPropertyConfiguration {

}
