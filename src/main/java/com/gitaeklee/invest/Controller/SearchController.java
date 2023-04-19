package com.gitaeklee.invest.Controller;

import com.gitaeklee.invest.domain.entity.Search;
import com.gitaeklee.invest.repository.dto.SearchDto;
import com.gitaeklee.invest.repository.repo.SearchRepository;
import com.gitaeklee.invest.service.SearchService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @PostMapping("/api/v2/search")
    public SearchResponse saveSearch(@RequestBody @Validated Search search) {
        Long id = searchService.saveKeyword(search);
        return new SearchResponse(id);
    }

    @Data
    @AllArgsConstructor
    static class SearchResponse {
        private Long id;
    }

    @PostMapping("/api/v2/search/list/{userId}")
    public List<SearchDto> findSearchByUserId(@PathVariable("userId") Long userId) {
        List<Search> searches = searchService.searchByUserId(userId);
        List<SearchDto> result = searches.stream()
                .map(o -> new SearchDto(o))
                .collect(Collectors.toList());

        return result;
    }

    /* all search keyword list api for admin. */
}
