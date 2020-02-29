package com.Aster.Database;
import java.util.*;
import com.Aster.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDB implements CollectionOfUserDBAPI{
    Map<String,User> UserMap = new HashMap<>();
    @Override
    public int AddUser(User user) throws Exception {
        if(UserMap.containsKey(user.getEmail())){
            throw new Exception("User already exits");
        }
        UserMap.put(user.getEmail(), user);
        if(user != UserMap.get(user.getEmail())){
            System.out.println("User was not created successfully");
            return 1;
        }
        System.out.println("User was created successfully");
        System.out.println(user.getEmail());
        return 0;
    }
    @Override
    public String Get_username(String email) throws Exception{
        if(email == null){
            throw new Exception("Email is not valid");
        }else if(!UserMap.containsKey(email)){
            System.out.println("checking if the email exits or not"+email);
            throw new Exception("Email dose not exist");
        }else{
            return UserMap.get(email).getUser_name();
        }
    }
    public User Get_user(String email){
        return UserMap.get(email);
    }
    public int Delete_user(String email){
        return 0;
    }
}
