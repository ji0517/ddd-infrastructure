package com.xwtec.infrastructure.uid;

import com.xwtec.infrastructure.uid.impl.CachedUidGenerator;
import com.xwtec.infrastructure.uid.impl.DefaultUidGenerator;
import com.xwtec.infrastructure.uid.impl.JpaIdGenerator;
import com.xwtec.infrastructure.uid.impl.UidProperties;
import com.xwtec.infrastructure.uid.utils.SpringUtils;
import com.xwtec.infrastructure.uid.worker.DisposableWorkerIdAssigner;
import com.xwtec.infrastructure.uid.worker.WorkerIdAssigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.jar.JarEntry;

/**
 * UID 的自动配置
 *
 * @author wujun
 * @date 2019.02.20 10:57
 */
@Configuration
@ConditionalOnClass({ DefaultUidGenerator.class, CachedUidGenerator.class })
@EnableConfigurationProperties(UidProperties.class)
public class UidAutoConfigure {

	@Autowired
	private UidProperties uidProperties;

	@Bean
	@ConditionalOnMissingBean
	@Lazy
	DefaultUidGenerator defaultUidGenerator() {
		return new DefaultUidGenerator(uidProperties);
	}

	@Bean
	@ConditionalOnMissingBean
	@Lazy
	CachedUidGenerator cachedUidGenerator() {
		return new CachedUidGenerator(uidProperties);
	}

	@Bean
	@ConditionalOnMissingBean
	WorkerIdAssigner workerIdAssigner() {
		return new DisposableWorkerIdAssigner();
	}

	@Bean
	@ConditionalOnMissingBean
	SpringUtils springUtils() {
		return  new SpringUtils();
	}

	@Bean
	@ConditionalOnMissingBean
	JpaIdGenerator jpaIdGenerator() {
		return  new JpaIdGenerator();
	}
}
