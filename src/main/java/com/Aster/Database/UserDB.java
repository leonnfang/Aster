package com.Aster.Database;
import java.util.*;
import com.Aster.Repository.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDB implements CollectionOfUserDBAPI{
    List<User> list = new ArrayList<>();
    Map<String,User> UserMap = new HashMap<>();
    @Override
    public int AddUser(User user) {
        UserMap.put(user.getEmail(), user);
        if(user != UserMap.get(user.getEmail())){
            System.out.println("User was not created successfully");
            return 1;
        }
        System.out.println("User was created successfully");
        return 0;
    }
    public int Getusername(String email) throws Exception {
        if(email == null){
            throw new Exception("NullPointerException");
        }
        if(!UserMap.containsKey(email)){
            throw new Exception("UserNotFound");
        }
        String res = UserMap.get(email).getUser_name();
        return res!=null?0:1;
    }
}
