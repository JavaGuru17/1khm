package uz.pdp.onekhm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.Category;
import uz.pdp.onekhm.dto.request.CategoryDto;
import uz.pdp.onekhm.exception.AlreadyExistsException;
import uz.pdp.onekhm.exception.NotFoundException;
import uz.pdp.onekhm.repo.CategoryRepository;
import uz.pdp.onekhm.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category save(CategoryDto category) {
        if (categoryRepository.findByName(category.getName().toUpperCase()).isPresent()) {
            throw new AlreadyExistsException("Category with name " + category.getName().toUpperCase());
        }
        return categoryRepository.save(CategoryDto.toEntity(category));
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id))
            throw new NotFoundException("Category with id " + id);
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getById(Long id) {
       return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with id " + id));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(() -> new NotFoundException("Category with name " + name));
    }
}
