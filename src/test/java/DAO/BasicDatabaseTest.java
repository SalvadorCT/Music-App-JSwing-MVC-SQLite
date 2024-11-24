package DAO;


import org.junit.jupiter.api.Test;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BasicDatabaseTest {
    @Test
    public void testConnection() throws Exception {
        Connection connection = TestDatabaseSetup.getTestConnection();
        assertNotNull(connection);
        connection.close();
    }
}
