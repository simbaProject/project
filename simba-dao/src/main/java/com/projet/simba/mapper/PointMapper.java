package com.projet.simba.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.locationtech.jts.geom.*;


@Mapper(componentModel = "spring")
public interface PointMapper {

    @Named("extractLatitude")
    default Double extractLatitude(Point point) {
        return (point != null) ? point.getY() : null;
    }

    @Named("extractLongitude")
    default Double extractLongitude(Point point) {
        return (point != null) ? point.getX() : null;
    }

    default Point createPoint(Double longitude, Double latitude) {
        if (longitude == null || latitude == null) return null;
        GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326); // SRID 4326 pour WGS84
        return factory.createPoint(new Coordinate(longitude, latitude));
    }
}
