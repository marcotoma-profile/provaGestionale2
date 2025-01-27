import com.gestionale.businesslogic.GlobalManager;

public class TestCreateUser {
    public static void main(String[] args) {

        // PersistenceManager.testSQLConnection();

        //User user = GlobalManager.getInstance().getUserManager();

        GlobalManager.getInstance().getUserManager().createNewUser("marco", "marco@email.com", true, "prova123");
        System.err.println(GlobalManager.getInstance().getUserManager().getCurrentUser());
    }
}
