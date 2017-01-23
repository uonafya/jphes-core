package org.hisp.dhis.jphes.hierarchy.action.donor;

import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnitService;
import org.hisp.dhis.paging.ActionPagingSupport;

import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * @author bangadennis on 05/01/17.
 */

public class GetDonorUnitListAction extends ActionPagingSupport<DonorUnit>
{ // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private DonorUnitService donorUnitService;

    public void setDonorUnitService(DonorUnitService donorUnitService){
        this.donorUnitService = donorUnitService;
    }

    // -------------------------------------------------------------------------
    // Input/Output
    // -------------------------------------------------------------------------

    private List<DonorUnit> donorUnits;

    public List<DonorUnit> getDonorUnits(){return donorUnits;}

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
        // Filter on key only if set
        if ( isNotBlank( key ) )
        {
            this.paging = createPaging(donorUnitService.getDonorUnitCountByName(key));

            donorUnits = donorUnitService.getDonorUnitsBetweenByName(key, paging.getStartPos(), paging.getPageSize() );
        }
        else
        {
            this.paging = createPaging( donorUnitService.getDonorUnitCount() );

            donorUnits = donorUnitService.getDonorUnitsBetween( paging.getStartPos(), paging.getPageSize() );
        }

        return SUCCESS;
    }
}