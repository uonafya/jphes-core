package org.hisp.dhis.jphes.hierarchy.action.national;

import org.hisp.dhis.dataelement.DataElementCategoryService;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnit;
import org.hisp.dhis.jphes.hierarchy.national.NationalUnitService;
import org.hisp.dhis.paging.ActionPagingSupport;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupService;
import org.hisp.dhis.user.UserService;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by afya on 05/01/17.
 */
public class GetNationalUnitListAction extends ActionPagingSupport<NationalUnit>
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------
    private NationalUnitService nationalUnitService;

    public void setNationalUnitService(NationalUnitService nationalUnitService)
    {
        this.nationalUnitService =nationalUnitService;
    }

    // -------------------------------------------------------------------------
    // Input/Output
    // -------------------------------------------------------------------------

    private List<NationalUnit> nationalUnits;

    public List<NationalUnit> getNationalUnits(){return nationalUnits;}

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
            this.paging = createPaging(nationalUnitService.getNationalUnitCountByName(key));

            nationalUnits = nationalUnitService.getNationalUnitsBetweenByName(key, paging.getStartPos(), paging.getPageSize() );

            Collections.sort(nationalUnits);
        }
        else
        {
            this.paging = createPaging( nationalUnitService.getNationalUnitCount() );

            nationalUnits = nationalUnitService.getNationalUnitsBetween( paging.getStartPos(), paging.getPageSize() );

            Collections.sort(nationalUnits);
        }

        return SUCCESS;
    }
}
