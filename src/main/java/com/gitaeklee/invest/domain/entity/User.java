package com.gitaeklee.invest.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.core.lang.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(of = { "id", "userName", "email", "password", "phone",
        "address", "profileImage", "profileIntro", "datetime",
        "trustPercent", "isCrewMember" })
@Table(name = "sv_user")
public class User {

    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;
    @Column(nullable = false)
    private String userName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String phone;

    @JsonIgnore
    @Embedded // 내장 타입
    private Address address;

    @Column(name="profile_image", nullable = true)
    private String profileImage;

    @Column(name="profile_intro", nullable = true)
    private String profileIntro;

    @Column(name="register_date", nullable = true)
    private LocalDateTime datetime;

    @Column(name="trust_percent", nullable = false)
    private float trustPercent;

    @Column(name="is_crew_badge", nullable = false)
    private boolean isCrewMember;

    // user : feed = 1 : n
    @JsonIgnore
    @OneToMany(mappedBy = "user") // 매핑된 것.
    private List<Feed> feeds = new ArrayList<>();

    // user : thread = 1: n
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<FeedThread> threads = new ArrayList<>();

    // user : like = 1 : n
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    // user : participants = 1 : N
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Participants> participants = new ArrayList<>();

    // user: message = 1 : n
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();

    // user : card = 1 : n
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<PaymentCard> cards = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Search> searches = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Payment> payments = new ArrayList<>();

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Mentor mentor;

    @JsonIgnore
    @Column(name="access_token", nullable = true) // login access token
    private String userToken;

    /* related method */

    public void setMentor(Mentor mentor) {
        // tag setter in feed
        this.mentor = mentor;
    }

    public void addFeed(Feed feed) {
        feeds.add(feed);
        feed.setUser(this);
    }

    public void addThread(FeedThread feedThread) {
        threads.add(feedThread);
        feedThread.setUser(this);
    }

    public void addLike(Like like) {
        likes.add(like);
        like.setUser(this);
    }

    public void addParticipant(Participants participant) {
        participants.add(participant);
        participant.setUser(this);
    }

    public void addMessage(Message message) {
        messages.add(message);
        message.setUser(this);
    }

    public void addPaymentCard(PaymentCard card) {
        cards.add(card);
        card.setUser(this);
    }

    public void addSearchKeyword(Search search) {
        searches.add(search);
        search.setUser(this);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.setUser(this);
    }

    /* entity setter & creator */
    public void setTrustPercent(float percent) {
        this.trustPercent = percent;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.email = userEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setProfileIntro(String profileIntro) {
        this.profileIntro = profileIntro;
    }

    public void setCrewMember(int count) {
        this.isCrewMember = (count != 0);
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
    public void setDatetime() {
        this.datetime = LocalDateTime.now();
    }

}
