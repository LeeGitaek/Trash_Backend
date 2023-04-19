package com.gitaeklee.invest.Controller;

import com.gitaeklee.invest.domain.entity.Tag;
import com.gitaeklee.invest.domain.entity.User;
import com.gitaeklee.invest.repository.dto.TagDto;
import com.gitaeklee.invest.repository.dto.request.TagRegisterRequest;
import com.gitaeklee.invest.service.TagService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/api/v2/tag/register")
    public TagResponse saveUserV2(@RequestBody @Validated TagRegisterRequest request) {
        Tag tag = new Tag();
        tag.setTagName(request.getTagName());

        Long id = tagService.saveTag(tag);
        return new TagResponse(id);
    }

    @Data
    @AllArgsConstructor
    static class TagResponse {
        private Long id;
    }

    /* update tag name */
    @Data
    static class UpdateTagRequest {
        private Long id;
        private String tagName;
    }

    @PostMapping("/api/v2/tag/update")
    public TagDto updateTagName(@RequestBody @Validated UpdateTagRequest request) {
        tagService.updateTag(request.id, request.tagName);
        Tag tag = tagService.findTag(request.id);
        return new TagDto(tag);
    }

    @GetMapping("/api/v2/tags")
    public List<TagDto> tagAllV2() {
        List<Tag> tags = tagService.findAll();
        return tags.stream()
                .map(o -> new TagDto(o))
                .collect(Collectors.toList());
    }
}
