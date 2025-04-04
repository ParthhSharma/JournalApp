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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String username){
        User user = userService.getUserByUserName(username);
        List<JournalEntry> all = user.getJournalentries();
        if(all!=null && all.size() > 0){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username){
        try{
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId myid){
        Optional<JournalEntry> entryById = journalEntryService.getEntryById(myid);
        if(entryById.isPresent()){
            return new ResponseEntity<>(entryById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myid){
        journalEntryService.deleteEntryById(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // @PutMapping("id/{myid}")
    // public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry){
    //     boolean updated = journalEntryService.updateEntry(myid, newEntry);
    //     if(updated){
    //         return new ResponseEntity<>(HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
}
