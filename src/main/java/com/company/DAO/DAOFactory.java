package com.company.DAO;

import com.company.entities.Role.User;

public class DAOFactory {
    private static IMyDAO dao = null;
    private static User user=new User();


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IMyDAO getDAOInstance(TypeDAO type){
            if (type == TypeDAO.MySQL) {
                dao = new MySQLDAOProxy(user);
            } else if (type == TypeDAO.ListTest) {
                dao = new MyDAOList();
            }else if (type == TypeDAO.Mongo) {
                dao = new MyDAOMongo();
            }
        System.out.println("DAO type:" + dao.getClass());
        return dao;
    }
}
