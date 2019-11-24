package ua.nure.cs.neichev.usermanagement.domain.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ua.nure.cs.neichev.usermanagement.domain.User;

public class MockUserDao implements UserDao {
	private Long id = (long) 0;
	private Map users = new HashMap();
	
	@Override
	public User create(User user) throws DatabaseException {
		// TODO Auto-generated method stub
		Long currentId = new Long(++id);
		user.setId(currentId);
		users.put(currentId, user);
		return user;
	}

	@Override
	public void update(User user) throws DatabaseException {
		// TODO Auto-generated method stub
		Long currentId = user.getId();
		users.remove(currentId);
		users.put(currentId, user);
	}

	@Override
	public void delete(User user) throws DatabaseException {
		// TODO Auto-generated method stub
		Long currentId = user.getId();
		users.remove(currentId);
	}

	@Override
	public User find(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return (User) users.get(id);
	}

	@Override
	public Collection findAll() throws DatabaseException {
		// TODO Auto-generated method stub
		return users.values();
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}

}
