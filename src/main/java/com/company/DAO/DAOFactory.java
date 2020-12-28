package com.company.DAO;

public class DAOFactory {
    private static IMyDAO dao = null;

    public static IMyDAO getDAOInstance(TypeDAO type){
            if (type == TypeDAO.MySQL) {
                dao = new MyDAOSQL();
            } else if (type == TypeDAO.ListTest) {
                dao = new MyDAOList();
            }else if (type == TypeDAO.Mongo) {
                dao = new MyDAOMongo();
            }
        System.out.println("DAO type:" + dao.getClass());
        return dao;
    }
}
