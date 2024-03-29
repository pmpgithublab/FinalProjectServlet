package ua.training.model.dao.implementation;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import ua.training.model.util.DBPropertiesManager;
import ua.training.util.MessageUtil;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static final Logger log = Logger.getLogger(ConnectionPoolHolder.class);
    private static final String DB_URL = "url";
    private static final String USER_NAME = "userName";
    private static final String USER_PASSWORD = "userPassword";
    private static final String DRIVER_CLASS_NAME = "driverClassName";
    private static final String DB_TIMEOUT = "timeout";
    private static final String DB_INIT_ERROR = "DB init error";

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    try {

                        BasicDataSource basicDataSource = new BasicDataSource();

                        basicDataSource.setUrl(DBPropertiesManager.INSTANCE.getProperty(DB_URL));
                        basicDataSource.setUsername(DBPropertiesManager.INSTANCE.getProperty(USER_NAME));
                        basicDataSource.setPassword(DBPropertiesManager.INSTANCE.getProperty(USER_PASSWORD));
                        basicDataSource.setDriverClassName(DBPropertiesManager.INSTANCE.getProperty(DRIVER_CLASS_NAME));
                        basicDataSource.setMaxWaitMillis(Long.parseLong(DBPropertiesManager.INSTANCE.getProperty(DB_TIMEOUT)));

                        dataSource = basicDataSource;

                    } catch (Exception e) {
                        log.error(DB_INIT_ERROR);
                        log.error(MessageUtil.getRuntimeExceptionMessage(e));
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return dataSource;
    }
}
