package uz.episodeone.merchants.api.category;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.episodeone.merchants.dto.CategoryDTO;
import uz.episodeone.merchants.dto.Filter;
import uz.episodeone.merchants.helpers.Constants;
import uz.episodeone.merchants.helpers.wrapper.EmptyResponse;
import uz.episodeone.merchants.helpers.wrapper.SuccessResponseWrapper;
import uz.episodeone.merchants.service.CategoryService;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;
import static uz.episodeone.merchants.helpers.utils.JsonUtils.emptyApiResponse;
import static uz.episodeone.merchants.helpers.utils.JsonUtils.successApiResponse;

@Slf4j
@RestController
@RequestMapping(Constants.API_BASE_PATH + "/category")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class CategoryEndpoint {

    CategoryService categoryService;

    @GetMapping
    public SuccessResponseWrapper<List<CategoryDTO>> getCategories(
            @PathVariable(value = "ids", required = false) List<Long> ids,
            @RequestParam(value = "filter") Filter filter) {
        return successApiResponse(categoryService.find(ids, filter));
    }

    @PostMapping
    public SuccessResponseWrapper<CategoryDTO> create(@RequestBody CategoryDTO entity) {
        return successApiResponse(categoryService.create(entity));
    }

    @PutMapping
    public SuccessResponseWrapper<CategoryDTO> update(@RequestBody CategoryDTO entity) {
        return successApiResponse(categoryService.update(entity));
    }

    @DeleteMapping("/{id}")
    public EmptyResponse delete(@PathVariable("id") long id) {
        categoryService.delete(id);
        return emptyApiResponse();
    }

}
