package com.gitaeklee.invest.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitaeklee.invest.domain.entity.Feed;
import com.gitaeklee.invest.domain.entity.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Getter
public class TagDto {

    private Long tagId;
    private String tagName;

    public TagDto(Tag tag) {
        this.tagId = tag.getId();
        this.tagName = tag.getTagName();
    }
}
