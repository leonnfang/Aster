package com.Aster.Database;

import com.Aster.Repository.User;

public interface CollectionOfUserDBAPI {
    int AddUser(User user) throws Exception;
    String Get_username(String email) throws Exception;

}