/**
 * Created by betty on 8/28/17.
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.*;
import java.lang.Object;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class myServer {

    /*public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/test", new MyHandler());
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

*/
    public static String getJSONFromResultSet(ResultSet rs,String keyName) {


        Map json = new HashMap();
        List list = new ArrayList<>();
        if(rs!=null)
        {
            try {
                ResultSetMetaData metaData = rs.getMetaData();
                while(rs.next())
                {
                    Map<String,Object> columnMap = new HashMap<String, Object>();
                    for(int columnIndex=1;
                        columnIndex<=metaData.getColumnCount();
                        columnIndex++)
                    {
                        if(rs.getString(metaData.getColumnName(columnIndex))!=null)
                            columnMap.put(metaData.getColumnLabel(columnIndex),rs.getString(metaData.getColumnName(columnIndex)));
                        else
                            columnMap.put(metaData.getColumnLabel(columnIndex), "");
                    }
                    list.add(columnMap);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            json.put(keyName, list);
        }
        return JSONValue.toJSONString(json);
    }
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/SampleDb", "root", "made2begr8");

                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String SQL = "SELECT * FROM Clearance";
                ResultSet rs =stmt.executeQuery(SQL);
                String result=getJSONFromResultSet(rs,"Clearance");
                t.sendResponseHeaders(200, result.length());
                OutputStream os = t.getResponseBody();
                os.write(result.getBytes());
                os.close();


            } catch (SQLException err) {
                System.out.println(err.getMessage());
            }
        }}
    public static void main(String[] args) throws Exception{
        HttpServer server = HttpServer.create(new InetSocketAddress(8089), 0);
        server.createContext("/test", new MyHandler());
        server.start();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}