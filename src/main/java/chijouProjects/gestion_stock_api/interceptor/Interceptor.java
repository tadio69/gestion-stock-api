package chijouProjects.gestion_stock_api.interceptor;


import org.hibernate.resource.jdbc.spi.StatementInspector;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interceptor implements StatementInspector {

    private static final ThreadLocal<Integer> currentEntrepriseId = new ThreadLocal<>();

    // Liste des tables à filtrer
    private static final Set<String> tablesAvecEntreprise = Set.of(
            "categorie", "article", "autre_table"
    );

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

        String lowerSql = sql.toLowerCase();

        // Ne modifier que les SELECT
        if (!lowerSql.startsWith("select") || lowerSql.contains("identreprise")) {
            return sql;
        }

        String modifiedSql = sql;

        // Pattern pour trouver "from <table> <alias>"
        Pattern fromPattern = Pattern.compile("from\\s+(\\w+)\\s+(\\w+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = fromPattern.matcher(sql);

        while (matcher.find()) {
            String table = matcher.group(1);
            String alias = matcher.group(2);

            if (tablesAvecEntreprise.contains(table.toLowerCase())) {
                // Si la requête contient déjà WHERE
                if (modifiedSql.toLowerCase().contains(" where ")) {
                    modifiedSql += " AND " + alias + ".identreprise = " + entrepriseId;
                } else {
                    modifiedSql += " WHERE " + alias + ".identreprise = " + entrepriseId;
                }
                break; // On filtre uniquement la première table correspondante
            }
        }

        return modifiedSql;
    }
}
