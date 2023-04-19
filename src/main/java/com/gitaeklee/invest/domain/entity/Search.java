package com.gitaeklee.invest.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="sv_search")
public class Search {

    @Id @GeneratedValue
    @Column(name="search_id")
    private Long id;

    @Column(name="search_keyword")
    private String keyword;

    @Column(name="search_date")
    private LocalDateTime datetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    /* related method */
    public void setUser(User user) {
        this.user = user;
        user.getSearches().add(this);
    }
    /* entity setter & creator */

}
