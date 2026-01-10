package com.conferences.invoicing.driven.adapters;

import com.conferences.invoicing.application.ports.driven.SiteReadDatasourcePort;
import com.conferences.invoicing.views.SiteBoundariesView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
