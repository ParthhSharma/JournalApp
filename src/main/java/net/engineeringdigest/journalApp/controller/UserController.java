package net.engineeringdigest.journalApp.controller;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> all = userService.getAll();
        if(all.size() > 0){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User myEntry){
        try{
            userService.saveUser(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId myid){
        Optional<User> entryById = userService.getUserById(myid);
        if(entryById.isPresent()){
            return new ResponseEntity<>(entryById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId myid){
        userService.deleteUserById(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<User> updateUserById(@PathVariable ObjectId myid, @RequestBody User newUser){
        boolean updated = userService.updateUser(myid, newUser);
        if(updated){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
