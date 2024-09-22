package com.fjb.sunrise.services.impl;

import com.fjb.sunrise.dtos.requests.CategoryCreateDto;
import com.fjb.sunrise.dtos.requests.CategoryUpdateDto;
import com.fjb.sunrise.dtos.responses.CategoryResponseDto;
import com.fjb.sunrise.exceptions.DuplicatedException;
import com.fjb.sunrise.exceptions.NotFoundException;
import com.fjb.sunrise.mappers.CategoryMapper;
import com.fjb.sunrise.models.Category;
import com.fjb.sunrise.repositories.CategoryRepository;
import com.fjb.sunrise.services.CategoryService;
import com.fjb.sunrise.utils.Constants;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponseDto createCategory(CategoryCreateDto categoryCreateDto) {
        if (categoryRepository.existsByNameIgnoreCase(categoryCreateDto.getName())) {
            throw new DuplicatedException(Constants.ErrorCode.CATEGORY_ALREADY_EXISTED);
        }
        Category category = categoryMapper.toCategory(categoryCreateDto);
        return categoryMapper.toCategoryResponseDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponseDto updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {
        if (categoryRepository.existsByNameIgnoreCase(categoryUpdateDto.getName())) {
            throw new DuplicatedException(Constants.ErrorCode.CATEGORY_ALREADY_EXISTED);
        }
        Category category = categoryRepository.findById(id)
            .map(c -> {
                var categoryUpdate = categoryMapper.updateCategory(c, categoryUpdateDto);
                categoryRepository.save(categoryUpdate);
                return categoryUpdate;
            }).orElseThrow(() -> new NotFoundException(Constants.ErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toCategoryResponseDto(category);
    }

    @Override
    @Transactional
    public void disableCategory(Long id) {
        categoryRepository.findById(id).ifPresent(x -> {
            x.setActivate(false);
            categoryRepository.save(x);
        });
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
            .stream().map(categoryMapper::toCategoryResponseDto)
            .toList();
    }

    @Override
    public CategoryResponseDto getCategory(Long id) {
        return categoryRepository.findById(id)
            .map(categoryMapper::toCategoryResponseDto)
            .orElseThrow(() -> new NotFoundException(Constants.ErrorCode.CATEGORY_NOT_FOUND));
    }
}
