package pl.wojcik.taskwithmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pl.wojcik.taskwithmongo.model.message.Message;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    @Query("{ 'email' : ?0 }")
    List<Message> findMessagesByEmail(String email);

    Message save(Message request);

    @Query("{ 'magicNumber' : ?0 }")
    List<Message> findAllByMagicNumber(Integer number);

}
