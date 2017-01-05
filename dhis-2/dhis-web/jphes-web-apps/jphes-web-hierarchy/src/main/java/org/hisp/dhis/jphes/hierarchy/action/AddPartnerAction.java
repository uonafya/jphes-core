package org.hisp.dhis.jphes.hierarchy.action;

import com.opensymphony.xwork2.Action;
import org.apache.commons.lang.StringUtils;
import org.hisp.dhis.jphes.hierarchy.PartnerHierarchy;
import org.hisp.dhis.jphes.hierarchy.HierarchyService;
import org.hisp.dhis.user.UserService;

/**
 * Created by afya on 06/12/16.
 */
public class AddPartnerAction implements Action
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

    public void setPartnerService(HierarchyService partnerService){ this.partnerService = partnerService; }

    // -------------------------------------------------------------------------
    // Input
    // -------------------------------------------------------------------------

    private String name;

    public void setName( String rolename )
    {
        this.name = rolename;
    }

    private String code;

    public void setCode(String code){ this.code = code; }

    private String displayName;

    public void setDisplayName(String displayName){ this.displayName = displayName; }


    // -------------------------------------------------------------------------
    // Action implementation
    // -------------------------------------------------------------------------


    @Override public String execute() throws Exception
    {
        PartnerHierarchy partner = new PartnerHierarchy();

        partner.setName( StringUtils.trimToNull( name ) );
        partner.setCode( StringUtils.trimToNull( code ) );
        partner.setDisplayName( StringUtils.trimToNull( displayName) );

        partnerService.savePartner( partner );

        return SUCCESS;
    }
}
