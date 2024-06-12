package com.example.servidorrestinesmr.common;

import com.example.servidorrestinesmr.data.dao.DaoUbi;
import com.example.servidorrestinesmr.data.dao.connection.JPAUtil;
import com.example.servidorrestinesmr.data.dao.impl.DaoUbiImpl;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public JPAUtil jpaUtil(){
        return new JPAUtil();
    }
    @Bean
    public DaoUbi daoUbi(){
        return new DaoUbiImpl(jpaUtil());
    }
}
