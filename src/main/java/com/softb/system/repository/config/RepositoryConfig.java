package com.softb.system.repository.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableJpaRepositories(basePackages = { 
		"com.softb.system.security.repository"
		,"com.softb.farofino.account.repository"
		,"com.softb.farofino.categorization.repository"
		,"com.softb.farofino.patrimony.repository"
		,"com.softb.farofino.cashflow.repository"
		,"com.softb.farofino.preferences.repository"
})
@EntityScan(basePackages = { 
		"com.softb.system.security.model"
		,"com.softb.farofino.account.model"
		,"com.softb.farofino.categorization.model"
		,"com.softb.farofino.patrimony.model"
		,"com.softb.farofino.cashflow.model"
		,"com.softb.farofino.preferences.model"
})
public class RepositoryConfig  {

    private final Logger logger = LoggerFactory.getLogger(RepositoryConfig.class);
    @Autowired
    private Environment environment;
    
    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
    	URI dbUri = new URI(environment.getProperty("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = (dbUri.getUserInfo().split(":").length > 1 ? dbUri.getUserInfo().split(":")[1] : "");
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setDriverClassName("org.postgresql.Driver");

        return basicDataSource;
    }
}

