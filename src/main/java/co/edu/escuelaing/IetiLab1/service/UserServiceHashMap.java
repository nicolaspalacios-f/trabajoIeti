package co.edu.escuelaing.IetiLab1.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import co.edu.escuelaing.IetiLab1.entities.User;

public class UserServiceHashMap implements UserService {

    private HashMap<String, User> persistence = new HashMap<>();

    @Override
    public User create(User user) {
        persistence.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(String id) {
        return persistence.get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(persistence.values());
    }

    @Override
    public void deleteById(String id) {
        persistence.remove(id);
    }

    @Override
    public User update(User user, String userId) {
        persistence.put(userId, user);
        return user;
    }

    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryText) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findUsersCreatedAfter(Date startDate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }
}
