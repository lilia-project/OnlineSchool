package org.lilia.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.lilia.dto.AdditionalMaterialDto;
import org.lilia.entity.AdditionalMaterial;
import org.lilia.entity.ResourceType;
import org.lilia.exception.NoSuchMaterialIdException;
import org.lilia.repository.AdditionalMaterialRepository;
import org.lilia.util.ConsoleUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AdditionalMaterialServiceTest {
    @Mock
    private AdditionalMaterialRepository materialRepository;

    @InjectMocks
    private AdditionalMaterialService target;

    @Test
    void createAdditionalMaterial() {
        target.isCreateAdditionalMaterial("Name", 1);

        AdditionalMaterial expected = new AdditionalMaterial("Name", 1);
        setInternalField(expected, "id", 1);
        Mockito.verify(materialRepository).save(expected);
    }

    private void setInternalField(Object object, String fieldName, int value) {
        try {
            Field f1 = object.getClass().getDeclaredField(fieldName);
            f1.setAccessible(true);
            f1.set(object, value);
        } catch (NoSuchFieldException | SecurityException | IllegalAccessException exception) {
            throw new RuntimeException("Could not access internal state", exception);
        }
    }

    @ParameterizedTest
    @EnumSource(ResourceType.class)
    void shouldCreateAdditionalMaterialDto(ResourceType resourceType) {
        AdditionalMaterialDto actual = target.createAdditionalMaterialDto(1, "dtoName", resourceType);

        AdditionalMaterialDto expected = new AdditionalMaterialDto(1, "dtoName", resourceType);
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteById() {
        Optional<AdditionalMaterial> expected = Optional.of(new AdditionalMaterial("Name", 1));
        Mockito.when(materialRepository.get(1)).thenReturn(expected);

        target.deleteById(1);

        Mockito.verify(materialRepository).delete(expected.get());
    }

    @Test
    void shouldThrowExceptionWhenDeleteById() {
        Optional<AdditionalMaterial> expected = Optional.empty();
        Mockito.when(materialRepository.get(3)).thenReturn(expected);

        NoSuchMaterialIdException exception = Assertions.assertThrows(NoSuchMaterialIdException.class, () -> target.deleteById(3));
        assertEquals("no such materialId exist 3", exception.getMessage());
    }

    @Test
    void shouldCheckAdditionalMaterialIdIsValid() {

        Optional<AdditionalMaterial> expected = Optional.of(new AdditionalMaterial("Name", 2));
        try (MockedStatic<ConsoleUtils> theMock = Mockito.mockStatic(ConsoleUtils.class)) {
            theMock.when(ConsoleUtils::readInteger).thenReturn(1);

            Mockito.when(materialRepository.get(1)).thenReturn(expected);

            int actual = target.additionalMaterialIdIsValid();

            assertEquals(expected.get().getId(), actual);
        }

    }

    @Test
    void shouldCheckAdditionalMaterialIdIsNotValid() {

        Optional<AdditionalMaterial> expected = Optional.of(new AdditionalMaterial("Name", 2));
        try (MockedStatic<ConsoleUtils> theMock = Mockito.mockStatic(ConsoleUtils.class)) {
            theMock.when(ConsoleUtils::readInteger).thenReturn(2);

            Mockito.when(materialRepository.get(1)).thenReturn(Optional.empty());

            theMock.when(ConsoleUtils::readInteger).thenReturn(1);

            Mockito.when(materialRepository.get(1)).thenReturn(expected);

            int actual = target.additionalMaterialIdIsValid();

            assertEquals(expected.get().getId(), actual);
        }

    }

    @Test
    void shouldGetRequireById() {
        Optional<AdditionalMaterial> expected = Optional.of(new AdditionalMaterial("Name", 2));
        Mockito.when(materialRepository.get(1)).thenReturn(expected);

        target.getRequireById(1);

        Mockito.verify(materialRepository).get(expected.get().getId());
    }

    @Test
    void shouldTrowExceptionWhenGetRequireById() {
        Optional<AdditionalMaterial> expected = Optional.empty();
        Mockito.when(materialRepository.get(3)).thenReturn(expected);

        NoSuchMaterialIdException exception;
        exception = Assertions.assertThrows(NoSuchMaterialIdException.class, () -> target.getRequireById(3));
        assertEquals("no such materialId exist 3", exception.getMessage());

    }
}