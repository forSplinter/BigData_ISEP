package com.isep.pool;

import com.jolbox.bonecp.BoneCPDataSource;

import javax.sql.DataSource;

public class BoneCPConnectionFactory {

    private static final BoneCPDataSource dataSource;

    static {
        dataSource = new BoneCPDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/lab_bigdata");
        dataSource.setUsername("forsplinter");
        dataSource.setPassword("postgres");
        dataSource.setMinConnectionsPerPartition(2);
        dataSource.setMaxConnectionsPerPartition(10);
        dataSource.setPartitionCount(1);
    }

    public static DataSource getInstance() {
        return dataSource;
    }
}
