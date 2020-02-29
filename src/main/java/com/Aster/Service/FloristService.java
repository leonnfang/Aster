package com.Aster.Service;

import com.Aster.Database.FloristDB;
import com.Aster.Model.Florist;
import com.Aster.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloristService {
    private FloristDB floristDB;
    @Autowired
    public FloristService(FloristDB floristDB) {
        this.floristDB = floristDB;
    }
    public int addFlorist(Florist florist) throws Exception {
        floristDB.addUser(florist);
        return 0;
    }
}
