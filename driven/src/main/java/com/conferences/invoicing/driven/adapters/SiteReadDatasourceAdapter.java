package com.conferences.invoicing.driven.adapters;

import com.conferences.invoicing.application.ports.driven.InvoiceReadDatasourcePort;
import com.conferences.invoicing.application.ports.driven.SiteReadDatasourcePort;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.driven.mappers.InvoiceMapper;
import com.conferences.invoicing.driven.models.InvoiceMO;
import com.conferences.invoicing.views.InvoiceWithAvailableWarehousesView;
import com.conferences.invoicing.views.SiteBoundariesView;
import com.conferences.invoicing.views.WarehouseView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SiteReadDatasourceAdapter implements SiteReadDatasourcePort {

  @Override
  public Set<SiteBoundariesView> getSitesBoundaries(Set<String> sitesIds) {

    return Optional.ofNullable(sitesIds)
            .orElse(Set.of())
            .stream()
            .map(this::dummyBoundariesForSites)
            .collect(Collectors.toSet());
  }

  private SiteBoundariesView dummyBoundariesForSites(String siteId) {

    return new SiteBoundariesView(siteId, Set.of("1", "15", "78"));
  }
}
