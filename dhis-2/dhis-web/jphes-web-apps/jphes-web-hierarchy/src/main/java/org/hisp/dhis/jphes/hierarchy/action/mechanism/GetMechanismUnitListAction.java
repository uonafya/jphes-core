package org.hisp.dhis.jphes.hierarchy.action.mechanism;

import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnitService;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnitService;
import org.hisp.dhis.paging.ActionPagingSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Created by afya on 05/01/17.
 */
public class GetMechanismUnitListAction extends ActionPagingSupport<MechanismUnit>
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private MechanismUnitService mechanismUnitService;

    @Autowired
    private AgencyUnitService agencyUnitService;

    // -------------------------------------------------------------------------
    // Input/Output
    // -------------------------------------------------------------------------

    private List<MechanismUnit> mechanismUnits;

    public List<MechanismUnit> getMechanismUnits(){ return  mechanismUnits; }

    private List<AgencyUnit> agencyUnits;

    public List<AgencyUnit> getAgencyUnits(){ return agencyUnits; }

    private String agencyUnit;

    public void setAgencyUnit(String agencyUnit){
        this.agencyUnit = agencyUnit;
    }
    public String getAgencyUnit(){
        return agencyUnit;
    }

    private String key;

    public void setKey( String key )
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
    }

    // -------------------------------------------------------------------------
    // Implementation
    // -------------------------------------------------------------------------
    @Override public String execute() throws Exception
    {
        //list of agency units
        agencyUnits = agencyUnitService.getAllAgencyUnit();


        // Filter on key only if set
        if ( isNotBlank( key ) )
        {
            this.paging = createPaging(mechanismUnitService.getMechanismUnitCountByName(key));

            mechanismUnits = mechanismUnitService.getMechanismUnitsBetweenByName(key, paging.getStartPos(), paging.getPageSize() );
        }

        if ( (! isNotBlank( key )) && (! isNotBlank( agencyUnit )))
        {
            this.paging = createPaging( mechanismUnitService.getMechanismUnitCount() );

            mechanismUnits = mechanismUnitService.getMechanismUnitsBetween( paging.getStartPos(), paging.getPageSize() );
        }

        if ( isNotBlank( agencyUnit ) )
        {
            AgencyUnit agency = agencyUnitService.getAgencyUnit( agencyUnit );

            this.paging = createPaging(agency.getMechanismUnits().size());

            mechanismUnits = (List<MechanismUnit>) agency.getMechanismUnits();
        }

        return SUCCESS;
    }
}
