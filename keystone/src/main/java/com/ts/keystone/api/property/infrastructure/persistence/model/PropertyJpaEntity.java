package com.ts.keystone.api.property.infrastructure.persistence.model;

import com.ts.keystone.api.property.infrastructure.persistence.model.details.*;
import com.ts.keystone.api.sharedKernel.domain.valuesObjects.PropertyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "properties")
@NoArgsConstructor
public class PropertyJpaEntity {

    @Id
    private UUID id;

    private boolean active;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ImageJpaEntity> images = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "house_details_id")
    private HouseDetails houseDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "condominium_house_details_id")
    private CondominiumHouseDetails condominiumHouseDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "condominium_plot_details_id")
    private CondominiumPlotDetails condominiumPlotDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "country_house_details_id")
    private CountryHouseDetails countryHouseDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "office_details_id")
    private OfficeDetails officeDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "others_details_id")
    private OthersDetails othersDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "penthouse_details_id")
    private PentHouseDetails pentHouseDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "plot_details_id")
    private PlotDetails plotDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "studio_details_id")
    private StudioDetails studioDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "warehouse_details_id")
    private WareHouseDetails wareHouseDetails;

    public PropertyJpaEntity(UUID id, boolean active, PropertyType type) {
        this.id = id;
        this.active = active;
        this.type = type;
    }

    public void addImage(ImageJpaEntity image) {
        this.images.add(image);
        image.setProperty(this);
    }

    public void removeImage(ImageJpaEntity image) {
        this.images.remove(image);
        image.setProperty(null);
    }
}
