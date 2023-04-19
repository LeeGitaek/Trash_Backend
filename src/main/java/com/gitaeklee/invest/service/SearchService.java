package com.gitaeklee.invest.service;

import com.gitaeklee.invest.domain.entity.Search;
import com.gitaeklee.invest.repository.repo.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    // save search
    public Long saveKeyword(Search search) {
        if (search.getKeyword().length() == 0) {
            throw new IllegalStateException("validation: search keyword is empty.");
        }
        searchRepository.saveSearch(search);
        return search.getId();
    }

    public List<Search> searchByUserId(Long userId) {
        return searchRepository.findSearchByUserId(userId);
    }

    // find all search
    public List<Search> findAll() {
        return searchRepository.findSearchAll();
    }



    // ....
}
