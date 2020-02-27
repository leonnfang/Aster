package com.Aster.Database;
import java.util.*;
import com.Aster.Repository.User;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    public String Get_username(@JsonProperty String email) throws Exception{
        // Error!!! need to convert json object back to java object
        if(email == null){
            throw new Exception("Email is not valid");
        }else if(!UserMap.containsKey(email)){
            System.out.println("checking if the email exits or not"+email);
            throw new Exception("Email dose not exist");
        }else{
            return UserMap.get(email).getUser_name();
        }
    }
}
