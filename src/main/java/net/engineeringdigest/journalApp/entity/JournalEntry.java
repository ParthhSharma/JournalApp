package net.engineeringdigest.journalApp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "journalentries")
// @Getter
// @Setter
// @AllArgsConstructor
// @ToString
// @EqualsAndHashCode
// @Builder
// @Slf4j
@NoArgsConstructor
@Data
public class JournalEntry {  // id , title, content

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    
    // public LocalDateTime getDate() {
    //     return date;
    // }
    // public void setDate(LocalDateTime date) {
    //     this.date = date;
    // }
    // public ObjectId getId() {
    //     return id;
    // }
    // public void setId(ObjectId id) {
    //     this.id = id;
    // }
    // public String getTitle() {
    //     return title;
    // }
    // public void setTitle(String title) {
    //     this.title = title;
    // }
    // public String getContent() {
    //     return content;
    // }
    // public void setContent(String content) {
    //     this.content = content;
    // }
}
