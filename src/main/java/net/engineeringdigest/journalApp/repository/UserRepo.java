package net.engineeringdigest.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import net.engineeringdigest.journalApp.entity.User;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
}
