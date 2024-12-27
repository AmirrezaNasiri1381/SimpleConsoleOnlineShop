public interface User {
    boolean login(String username, String password) throws InvalidLoginException;
    void register(String username, String password);
}
