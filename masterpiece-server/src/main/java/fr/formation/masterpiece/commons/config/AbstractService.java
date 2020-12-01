package fr.formation.masterpiece.commons.config;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Abstract implementation of a service, to be implemented by every service
 * implementation.
 * <p>
 * provides convenient bean injection of a {@code ModelMapper}.
 *
 * @author Tomas LOBGEOIS
 *
 */
@Service
public abstract class AbstractService {

    @Autowired
    protected ModelMapper modelMapper;

    /**
     * Merge a dto into specified entity
     *
     * @param <S>         source type
     * @param <D>         destination entity
     * @param source      dto having fields to merge
     * @param destination entity to update
     */
    protected <S, D> void merge(S source, D destination) {
	modelMapper.map(source, destination);
    }

    /**
     * Convert a source type into a destination type
     *
     * @param <S>         source type
     * @param <D>         destination class
     * @param source      object to convert
     * @param destination object converted into desired type
     * @return an object mapped into destination type
     */
    protected <S, D> D convert(S source, Class<D> destination) {
	return modelMapper.map(source, destination);
    }

    /**
     * Convert a {@code List} of objects into given destination {@code Class}.
     * <p>
     * utility generic method to handle {@code List} conversion.
     *
     * @param <S>         source type
     * @param <D>         destination class
     * @param source      the list to convert
     * @param destination the type in which the source is mapped
     * @return a {@code List} of given destination type.
     */
    public <S, D> List<D> convertList(List<S> source, Class<D> destination) {
	return source.stream()// -
	        .map(elt -> convert(elt, destination))// -
	        .collect(Collectors.toList());
    }
}
