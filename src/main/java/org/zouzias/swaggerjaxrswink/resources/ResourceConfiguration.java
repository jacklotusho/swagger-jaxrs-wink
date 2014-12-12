package org.zouzias.swaggerjaxrswink.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class ResourceConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        classes.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        classes.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        classes.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        classes.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);

        addRestResources(classes);

        return classes;
    }

    /**
     * Here you can add your extra resources.
     *
     * @param resources
     */
    private void addRestResources(Set<Class<?>> resources) {
        resources.add(PetResource.class);
        resources.add(PetStoreResource.class);
        resources.add(UserResource.class);

    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> s = new HashSet<>();

        // TODO : Is this the correct configuration???
        // Register the Jackson provider for JSON
        // Make (de)serializer use a subset of JAXB and (afterwards) Jackson annotations
        // See http://wiki.fasterxml.com/JacksonJAXBAnnotations for more information
        ObjectMapper mapper = new ObjectMapper();

        JaxbAnnotationModule module = new JaxbAnnotationModule();
        // configure as necessary
        mapper.registerModule(module);

        // Set up the provider
        JacksonJaxbJsonProvider jaxbProvider = new JacksonJaxbJsonProvider();
        jaxbProvider.setMapper(mapper);

        s.add(jaxbProvider);
        return s;
    }
}
