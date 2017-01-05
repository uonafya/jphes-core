package org.hisp.dhis.jphes.hierarchy.action;

import org.hisp.dhis.jphes.hierarchy.PartnerHierarchy;
import org.hisp.dhis.jphes.hierarchy.HierarchyService;
import org.hisp.dhis.paging.ActionPagingSupport;
import org.hisp.dhis.user.UserService;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by afya on 06/12/16.
 */

public class GetPartnerListAction extends ActionPagingSupport<PartnerHierarchy>
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private UserService userService;

    public void setUserService( UserService userService )
    {
        this.userService = userService;
    }

    private HierarchyService partnerService;

    public void setPartnerService(HierarchyService partnerService){this.partnerService = partnerService;}

    // -------------------------------------------------------------------------
    // Input & Output
    // -------------------------------------------------------------------------

    private List<PartnerHierarchy> partners;

    public List<PartnerHierarchy> getPartners(){return partners;}

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
    // Action implementation
    // -------------------------------------------------------------------------

    @Override
    public String execute()
        throws Exception
    {
        // Filter on key only if set
        if ( isNotBlank( key ) )
        {
            this.paging = createPaging( partnerService.getPartnerCountByName(key));

            partners = partnerService.getPartnersBetweenByName(key, paging.getStartPos(), paging.getPageSize() );
        }
        else
        {
            this.paging = createPaging( partnerService.getPartnerCount() );

            partners = partnerService.getPartnersBetween( paging.getStartPos(), paging.getPageSize() );
        }

        return SUCCESS;
    }
}