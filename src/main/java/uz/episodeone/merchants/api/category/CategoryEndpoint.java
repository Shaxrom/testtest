package uz.episodeone.merchants.api.category;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.episodeone.merchants.dto.CategoryDTO;
import uz.episodeone.merchants.dto.ProviderDTO;
import uz.episodeone.merchants.helpers.Constants;
import uz.episodeone.merchants.service.CategoryService;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@RestController
@RequestMapping(Constants.API_BASE_PATH + "/categories")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class CategoryEndpoint {

    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }

    @GetMapping("/{id}/providers")
    public ResponseEntity<Page<ProviderDTO>> getProviders(@PathVariable("id") Long id,
                                                                  Pageable pageable) {
        return ResponseEntity.ok(categoryService.findProviders(id, pageable));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO entity) {
        return ResponseEntity.ok(categoryService.create(entity));
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO entity) {
        return ResponseEntity.ok(categoryService.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

}
