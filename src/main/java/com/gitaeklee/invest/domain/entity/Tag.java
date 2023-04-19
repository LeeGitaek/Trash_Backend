package com.gitaeklee.invest.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "sv_tag")
public class Tag {

    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    @Column(name="tag_name", unique = true, nullable = false)
    private String tagName;

    /* entity setter & creator */
    public void Tag(String tagName) { this.tagName = tagName; }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
