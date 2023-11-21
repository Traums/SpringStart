package ru.mart.Hero.util;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import ru.mart.Hero.domain.Hero;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public  class Validation implements Validator{
    Map<String,String> errorMap;
    @Override
    public boolean supports(Class<?> aClass) {
        return Hero.class.equals(aClass);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        Hero hero = (Hero) target;
        if (hero.getName().length() > 14)
            errors.rejectValue("name","01","Имя слишком длинное");
        if (hero.getName().length() < 4)
            errors.rejectValue("name","02", "Имя слишком короткое");
        if (hero.getLevel() < 0)
            errors.rejectValue("level","03","Уровень ниже допустимого");
        if (hero.getLevel() > 30)
            errors.rejectValue("level","04","Уровень выше допустимого");
    }
}

