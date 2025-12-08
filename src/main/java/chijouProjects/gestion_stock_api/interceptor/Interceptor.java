package chijouProjects.gestion_stock_api.interceptor;


import org.hibernate.resource.jdbc.spi.StatementInspector;

public class Interceptor implements StatementInspector {

    private static final ThreadLocal<Integer> currentEntrepriseId = new ThreadLocal<>();

    public static void setCurrentEntrepriseId(Integer entrepriseId) {
        currentEntrepriseId.set(entrepriseId);
    }

    public static void clear() {
        currentEntrepriseId.remove();
    }

    @Override
    public String inspect(String sql) {
        if (sql == null) return null;

        Integer entrepriseId = currentEntrepriseId.get();
        if (entrepriseId == null) return sql;

        String lower = sql.toLowerCase();
        if (!lower.startsWith("select") || lower.contains("identreprise")) {
            return sql;
        }

        if (lower.contains(" where ")) {
            return sql + " AND identreprise = " + entrepriseId;
        } else {
            return sql + " WHERE identreprise = " + entrepriseId;
        }
    }
}
