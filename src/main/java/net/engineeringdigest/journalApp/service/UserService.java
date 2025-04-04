package net.engineeringdigest.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import net.engineeringdigest.journalApp.repository.UserRepo;

@Component
public class UserService {
    @Autowired
    private UserRepo repo;

    public void saveUser(User user){
        // user.setDate(LocalDateTime.now());
        repo.save(user);
    }

    public List<User> getAll(){
        return repo.findAll();
    }

    public Optional<User> getUserById(ObjectId id){
        return repo.findById(id);
    }

    public User deleteUserById(ObjectId id){
        User ent = repo.findById(id).orElse(null);
        repo.deleteById(id);
        return ent;
    }

    public User getUserByUserName(String userName){
        return repo.findByUsername(userName);
    }
    public boolean updateUser(ObjectId id, User newUser){
        User olduser = repo.findById(id).orElse(null);
        boolean updated=false;
        if(olduser!=null){
            olduser.setUsername(newUser.getUsername()!=null && !newUser.getUsername().equals("")? newUser.getUsername():olduser.getUsername());
            olduser.setPassword(newUser.getPassword()!=null && !newUser.getPassword().equals("")? newUser.getPassword(): olduser.getPassword());
            saveUser(olduser);
            updated = true;
        }
        
        return updated;
    }
}
