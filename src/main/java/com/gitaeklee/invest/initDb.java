package com.gitaeklee.invest;

import com.gitaeklee.invest.domain.entity.*;
import com.gitaeklee.invest.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        private final UserService userService;
        public void dbInit1() {

            User user2 = new User();
            user2.setUserName("WendyDing");
            user2.setUserEmail("wendy22ding@gmail.com");
            user2.setPhone("408-921-9792");
            user2.setTrustPercent(90.0f);
            user2.setProfileImage("https://wwkdjfhwjk.wkjdhfk.wdfk");
            user2.setProfileIntro("Math Teacher");
            user2.setPassword("23r235");
            user2.setCrewMember(0);
            user2.setUserToken("aklsd5j2fjek");
            user2.setDatetime();
            user2.setAddress(new Address("San Jose", "CA", "5282 garrison cir.", "95123"));
            em.persist(user2);

            User user = new User();
            user.setUserName("GitaekLee");
            user.setUserEmail("gitaeklee96@gmail.com");
            user.setPhone("669-837-3181");
            user.setTrustPercent(90.0f);
            user.setProfileImage("https://wwkdjfhwjk.wkjdhfk.wdfk");
            user.setProfileIntro("software engineer");
            user.setPassword("23r235");
            user.setCrewMember(0);
            user.setUserToken("gishflawieuhfw");
            user.setDatetime();
            user.setAddress(new Address("San Jose", "CA", "5282 garrison cir.", "95123"));
            em.persist(user);

//            Mentor mentor1 = new Mentor();
//            mentor1.setUser(user);
//            mentor1.setPrice(62);
//            mentor1.setDatetime();
//            em.persist(mentor1);

            Tag tag = new Tag();
            tag.setTagName("#SideValley");
            em.persist(tag);

            Tag tag2 = new Tag();
            tag2.setTagName("#CoffeeChat");
            em.persist(tag2);

            Feed newFeed = new Feed();
            newFeed.setUser(user);
            newFeed.setTag(tag);
            newFeed.setFeedText("I'm looking for someone who is working with me");
            newFeed.setLikeCount();
            newFeed.setViewCount();
            newFeed.setThreadCount();
            newFeed.setFeedToken("sdfhwkjdhw"+"I'm looking for someone who is working with me");
            newFeed.setDatetime();
            em.persist(newFeed);

            FeedThread thread = new FeedThread();
            thread.setUser(user);
            thread.setFeed(newFeed);
            thread.setThreadText("hello I'm backend java engineer. I want to work with you for as making side project. give me a message ");
            thread.setDatetime();
            em.persist(thread);

            ChatRoom chat = new ChatRoom();
            chat.setRoomTitle("Coffee chat for refer @Meta");
            chat.setType(MessageType.PRIVATE);
            em.persist(chat);


            ChatRoom chat2 = new ChatRoom();
            chat2.setRoomTitle("Coffee chat for refer @School");
            chat2.setType(MessageType.GROUP);
            em.persist(chat2);

            Participants participant = new Participants();
            participant.setUser(user);
            em.persist(participant);

            Participants participant3 = new Participants();
            participant3.setUser(user);
            em.persist(participant3);

            Participants participant2 = new Participants();
            participant2.setUser(user2);
            em.persist(participant2);

            Message message = new Message();
            message.setMessageText("hello mentoring & consult chat is start.");
            message.setRoom(chat);
            message.setUser(user);
            message.setIsRead();
            message.setDatetime();
            message.setChatFile();
            message.setChatImage();
            message.setDatetime();
            em.persist(message);

            Message message2 = new Message();
            message2.setMessageText("hello mentoring & consult chat is start.");
            message2.setRoom(chat2);
            message2.setUser(user2);
            message2.setIsRead();
            message2.setDatetime();
            message2.setChatFile();
            message2.setChatImage();
            message2.setDatetime();
            em.persist(message2);

        }
    }

}
