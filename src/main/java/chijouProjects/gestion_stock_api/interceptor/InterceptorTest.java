package chijouProjects.gestion_stock_api.interceptor;

public class InterceptorTest {
    public static void main(String[] args) {
        Interceptor interceptor = new Interceptor();

        // Test 1 : SELECT sans WHERE
        Interceptor.setCurrentEntrepriseId(1);
        String sql1 = "SELECT * FROM categorie";
        String result1 = interceptor.inspect(sql1);
        System.out.println(result1); // devrait ajouter WHERE identreprise = 5
        Interceptor.clear();

        // Test 2 : SELECT avec WHERE
        Interceptor.setCurrentEntrepriseId(3);
        String sql2 = "SELECT * FROM categorie WHERE code='cat3'";
        String result2 = interceptor.inspect(sql2);
        System.out.println(result2); // devrait ajouter AND identreprise = 10
        Interceptor.clear();

        // Test 3 : identreprise déjà présent
        String sql3 = "SELECT * FROM categorie WHERE identreprise = 2";
        String result3 = interceptor.inspect(sql3);
        System.out.println(result3); // ne doit rien changer
    }
}
