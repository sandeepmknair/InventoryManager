package com.inventorymanagement.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.inventorymanagement.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "com.inventorymanagement.model")
public class InventoryConfiguration {

}
