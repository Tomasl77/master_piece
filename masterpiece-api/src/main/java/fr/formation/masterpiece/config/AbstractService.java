package fr.formation.masterpiece.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
