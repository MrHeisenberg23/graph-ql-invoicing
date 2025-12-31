package com.conferences.invoicing.application.ports.driven;

import com.conferences.invoicing.views.SiteBoundariesView;

import java.util.Set;

public interface SiteReadDatasourcePort {

  Set<SiteBoundariesView> getSitesBoundaries(Set<String> sitesIds);
}
