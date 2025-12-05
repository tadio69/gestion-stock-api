package chijouProjects.gestion_stock_api.interceptor;


import org.hibernate.resource.jdbc.spi.StatementInspector;

public class Interceptor implements StatementInspector {

    @Override
    public String inspect (String sql){
        System.out.println("SQL intercepté : " + sql);
        return sql;
    }
}
