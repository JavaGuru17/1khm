package uz.pdp.onekhm.service;

import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.Category;
import uz.pdp.onekhm.dto.request.CategoryDto;

import java.util.List;

@Service
public interface CategoryService {
    Category save(CategoryDto category);
    void delete(Long id);
    Category getById(Long id);
    List<Category> getAll();
    Category getByName(String name);
}
