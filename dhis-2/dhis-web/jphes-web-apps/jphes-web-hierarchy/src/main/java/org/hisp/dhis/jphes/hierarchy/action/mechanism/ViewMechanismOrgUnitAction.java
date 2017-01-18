package org.hisp.dhis.jphes.hierarchy.action.mechanism;

import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnit;
import org.hisp.dhis.jphes.hierarchy.mechanism.MechanismUnitService;
import org.hisp.dhis.organisationunit.OrganisationUnit;
import org.hisp.dhis.organisationunit.OrganisationUnitService;
import org.hisp.dhis.paging.ActionPagingSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * @author bangadennis on 18/01/17.
 */
public class ViewMechanismOrgUnitAction extends ActionPagingSupport<OrganisationUnit>
{
    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private MechanismUnitService mechanismUnitService;

    // -------------------------------------------------------------------------
    // Input/Output
    // -------------------------------------------------------------------------

    private String uid;

    public void setUid(String uid){
        this.uid=uid;
    }

    private List<OrganisationUnit> organisationUnits;

    public List<OrganisationUnit> getOrganisationUnits(){
        return organisationUnits;
    }

    private MechanismUnit mechanismUnit;

    public MechanismUnit getMechanismUnit(){
        return mechanismUnit;
    }

    @Override public String execute() throws Exception
    {
        if(isNotBlank(uid)){

            mechanismUnit = mechanismUnitService.getMechanismUnit( uid );

            this.paging = createPaging(mechanismUnit.getCategoryOption().getOrganisationUnits().size());

            organisationUnits = new ArrayList<>(mechanismUnit.getCategoryOption().getOrganisationUnits());
        }
        else{

            return ERROR;
        }
        return SUCCESS;
    }
}
