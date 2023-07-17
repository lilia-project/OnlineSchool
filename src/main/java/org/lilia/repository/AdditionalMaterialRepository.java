package org.lilia.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lilia.constant.Constants;
import org.lilia.entity.AdditionalMaterial;
import org.lilia.serialization.FilePath;
import org.lilia.serialization.Serializer;
import org.lilia.util.ConsoleUtils;
import org.lilia.util.HibernateUtil;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AdditionalMaterialRepository extends ConnectionFactory {

    private final Map<Integer, List<AdditionalMaterial>> data = new HashMap<>();

    private static Comparator<AdditionalMaterial> getAdditionalMaterialComparator(AdditionalMaterial.SortField sortField) {
        Comparator<AdditionalMaterial> comparator = null;
        switch (sortField) {
            case ID -> comparator = new AdditionalMaterial.IdComparator();
            case LECTURE_ID -> comparator = new AdditionalMaterial.LectureIdComparator();
            case RESOURCE_TYPE -> comparator = new AdditionalMaterial.ResourceTypeComparator();
            default -> ConsoleUtils.print(Constants.ERROR);
        }
        return comparator;
    }

    public static Boolean save(final AdditionalMaterial material) {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(material);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<AdditionalMaterial> get(final Integer id) {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final javax.persistence.Query usersQuery = session.createQuery("from AdditionalMaterial where id =:id", AdditionalMaterial.class);
            usersQuery.setParameter("id", id);
            final AdditionalMaterial singleResult = (AdditionalMaterial) usersQuery.getSingleResult();
            return Optional.of(singleResult);
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean delete(final AdditionalMaterial material) {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(material);
            transaction.commit();
            return true;
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<List<AdditionalMaterial>> getByLectureId(int lectureId) {
        List<AdditionalMaterial> list = data.get(lectureId);
        return Optional.ofNullable(list);
    }

    public List<AdditionalMaterial> getAll() {
        List<AdditionalMaterial> list = new ArrayList<>();
        data.values().stream()
                .flatMap(Collection::stream)
                .forEach(list::add);
        return Optional.of(list).orElse(Collections.emptyList());
    }

    public void getAll(AdditionalMaterial.SortField sortField, int lectureId) {

        Optional<List<AdditionalMaterial>> list = getByLectureId(lectureId);

        if (list.isEmpty()) {
            System.out.println("in lecture " + lectureId + "additionMaterial not exist");
        } else {
            Comparator<AdditionalMaterial> comparator = getAdditionalMaterialComparator(sortField);
            list.get().stream()
                    .sorted(comparator)
                    .forEach(System.out::println);
        }
    }

    public void serializeMaterial() {
        List<AdditionalMaterial> list = getAll();
        Serializer.serialize(list, FilePath.FILE_PATH_ADDITION_MATERIAL);
        ConsoleUtils.print(Constants.SERIALIZATION_COMPLETED);
    }

    public void deserializeMaterial() {
        String filePath = FilePath.FILE_PATH_ADDITION_MATERIAL.getPath();
        Object deserialize = Serializer.deserialize(filePath);
        List<AdditionalMaterial> additionalMaterials = (List<AdditionalMaterial>) deserialize;
        ConsoleUtils.print(Constants.DESERIALIZATION_COMPLETED);

        for (AdditionalMaterial additionalMaterial : additionalMaterials) {
            saveAdditionMaterial(additionalMaterial);
        }
    }

    private void saveAdditionMaterial(AdditionalMaterial additionalMaterial) {
        if (data.containsKey(additionalMaterial.getLectureId())) {
            List<AdditionalMaterial> list = data.get(additionalMaterial.getLectureId());
            list.add(additionalMaterial);
        } else {
            List<AdditionalMaterial> list = new ArrayList<>();
            list.add(additionalMaterial);
            data.put(additionalMaterial.getLectureId(), list);
        }
    }

    public void printAddMaterialsGroupingByLectureId() {
        List<AdditionalMaterial> materials = getAll();
        Map<Integer, List<AdditionalMaterial>> collect = materials.stream()
                .collect(Collectors.groupingBy(AdditionalMaterial::getLectureId));
        System.out.println(collect);
    }
}
