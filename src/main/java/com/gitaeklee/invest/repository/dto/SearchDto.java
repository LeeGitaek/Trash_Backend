package com.gitaeklee.invest.repository.dto;

import com.gitaeklee.invest.domain.entity.Search;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Getter
public class SearchDto {

    private Long id;
    private String keyword;
    private LocalDateTime datetime;

    private Long userId;

    public SearchDto(Search search) {
        this.id = search.getId();
        this.keyword = search.getKeyword();
        this.datetime = search.getDatetime();
        this.userId = search.getUser().getId();
    }

}
