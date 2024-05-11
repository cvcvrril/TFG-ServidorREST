package com.example.servidorrestinesmr.common;

import com.example.servidorrestinesmr.data.dao.DaoUbi;
import com.example.servidorrestinesmr.data.dao.DaoUser;
import com.example.servidorrestinesmr.data.dao.connection.JPAUtil;
import com.example.servidorrestinesmr.data.dao.impl.DaoUbiImpl;
import com.example.servidorrestinesmr.data.dao.impl.DaoUserImpl;
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
    @Bean
    public DaoUser daoUser(){
        return new DaoUserImpl(jpaUtil());
    }

}
