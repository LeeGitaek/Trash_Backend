package com.gitaeklee.invest.service;

import com.gitaeklee.invest.domain.entity.Tag;
import com.gitaeklee.invest.repository.repo.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    /* save tag */
    public Long saveTag(Tag tag) {

        tagRepository.saveTag(tag);
        return tag.getId();
    }
    /* find tag */
    public Tag findTag(Long id) {
        return tagRepository.findOne(id);
    }

    public Tag findTagByName(String tagName) {
        return tagRepository.findTagByName(tagName);
    }

    /* update tag name */
    @Transactional
    public void updateTag(Long tagId, String tagName) {
        Tag findTag = tagRepository.findOne(tagId);
        findTag.setTagName(tagName);
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }
}
