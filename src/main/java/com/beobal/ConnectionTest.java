package com.beobal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ConnectionTest 
{
    public static void main( String[] args ) throws Exception
    {

        String host = args[0];
        int port = Integer.valueOf(args[1]);
        String ks = args[2];
        String query = args[3];
        
        String url = String.format("jdbc:cassandra://%s:%d/%s", host, port, ks);
        if (args.length == 5)
        {
            url += "?transportFactory=" + args[4];
        }
        System.out.println("Connecting to " + url);
        
        Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
        Connection con = DriverManager.getConnection(url);

        System.out.println("Executing : " + query);
        PreparedStatement statement = con.prepareStatement(query);
        
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData md = rs.getMetaData();
        
        while (rs.next())
        {
            StringBuilder buf = new StringBuilder();
            for (int i=1;i<=md.getColumnCount();i++)
            {
                buf.append( md.getColumnLabel(i) + ":" + rs.getObject(i) + " | ");
            }
            System.out.println(buf.toString());
        }
        
        statement.close();
        con.close();
    }
}
