package io.javabrains.courseapi.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    private List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic("spring", "Spring Framework","Spring Framework Description"),
            new Topic("java", "Core Java","Core Java Description"),
            new Topic("javascript", "JavaScript","JavaScript Description")
    ));

    public List<Topic> getAllTopics(){
        //return topics;
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Topic getTopic(String id){
      //  return topics.stream().filter(t->t.getId().equals(id)).findFirst().get();
        return topicRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id));
    }
    public void addTopic(Topic topic){
        topicRepository.save(topic);
    }
    public void updateTopic(Topic topic){
        /*
        for(int i =0;i<topics.size();i++){
            Course t = topics.get(i);
            if(t.getId().equals(id)){
                topics.set(i, topic);
                return;
            }
        }
        */
        topicRepository.save(topic);
    }
    public void deleteTopic(String id){
        /*for(int i = 0;i<topics.size();i++){
            Course t = topics.get(i);
            if(t.getId().equals(id)){
                topics.remove(t);
            }
        }
        */
        Topic topic = new Topic();
        topic = topicRepository.findById(id).orElse(null);
        topicRepository.delete(topic);

    }


}
