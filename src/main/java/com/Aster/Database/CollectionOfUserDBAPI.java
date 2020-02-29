package com.Aster.Database;

import com.Aster.Model.User;

public interface CollectionOfUserDBAPI {
    int addUser(User user) throws Exception;
    String getUsername(String email) throws Exception;

}