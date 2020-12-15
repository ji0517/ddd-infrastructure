package com.xwtec.infrastructure.uid.impl;

import com.xwtec.infrastructure.uid.UidGenerator;
import com.xwtec.infrastructure.uid.utils.SpringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.factory.internal.DefaultIdentifierGeneratorFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Properties;

public class JpaIdGenerator implements  IdentifierGenerator, Configurable {

    public final static String STRATEGY = "com.xwtec.infrastructure.uid.impl.JpaIdGenerator";

    public final static String NAME = "JPA_SF_ID_GEN";


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        synchronized (JpaIdGenerator.class) {
            return ((UidGenerator)SpringUtils.getBean("cachedUidGenerator")).getUID();
        }
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {

    }
}
