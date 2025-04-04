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
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo repo;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String username){
        System.out.println("username passed to journal entry service save method:"+username);
        journalEntry.setDate(LocalDateTime.now());
        User user = userService.getUserByUserName(username);

        JournalEntry savedEntry= repo.save(journalEntry);
        user.getJournalentries().add(savedEntry);
        userService.saveUser(user);

    }

    public List<JournalEntry> getAll(){
        return repo.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return repo.findById(id);
    }

    public JournalEntry deleteEntryById(ObjectId id){
        JournalEntry ent = repo.findById(id).orElse(null);
        repo.deleteById(id);
        return ent;
    }

    // public boolean updateEntry(ObjectId id, JournalEntry newEntry){
    //     JournalEntry oldent = repo.findById(id).orElse(null);
    //     boolean updated=false;
    //     if(oldent!=null){
    //         oldent.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle():oldent.getTitle());
    //         oldent.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent(): oldent.getContent());
    //         saveEntry(oldent);
    //         updated = true;
    //     }
        
    //     return updated;
    // }
}
