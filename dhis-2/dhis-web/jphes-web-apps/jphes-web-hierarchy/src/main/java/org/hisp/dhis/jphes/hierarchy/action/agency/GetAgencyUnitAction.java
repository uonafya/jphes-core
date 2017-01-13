package org.hisp.dhis.jphes.hierarchy.action.agency;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.dataelement.CategoryOptionGroup;
import org.hisp.dhis.dataelement.CategoryOptionGroupSet;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnit;
import org.hisp.dhis.jphes.hierarchy.agency.AgencyUnitService;
import org.hisp.dhis.jphes.hierarchy.donor.DonorUnit;
import org.hisp.dhis.program.Program;
import org.hisp.dhis.user.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by @bangadennis on 13/01/17.
 */
public class GetAgencyUnitAction implements Action
{ // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    @Autowired
    private AgencyUnitService agencyUnitService;

    // -------------------------------------------------------------------------
    // Input
    // -------------------------------------------------------------------------

    private Integer id;

    public void setId( Integer id )
    {
        this.id = id;
    }

    // -------------------------------------------------------------------------
    // Output
    // -------------------------------------------------------------------------

    private AgencyUnit agencyUnit;

    public AgencyUnit getAgencyUnit(){return agencyUnit;}

    private List<Program> programList;

    public List<Program> getProgramList(){return programList;}

    private UserGroup userGroup;

    public UserGroup getUserGroup(){return userGroup;}

    private DonorUnit donorUnit;

    public DonorUnit getDonorUnit(){return donorUnit;}

    private CategoryOptionGroup categoryOptionGroup;

    public CategoryOptionGroup getCategoryOptionGroup(){
        return categoryOptionGroup;
    }


    // -------------------------------------------------------------------------
    // Action Implementation
    // -------------------------------------------------------------------------

    @Override public String execute() throws Exception
    {
        agencyUnit = agencyUnitService.getAgencyUnit( id );

        userGroup = agencyUnit.getUserGroup();

        donorUnit = agencyUnit.getDonorUnit();

        categoryOptionGroup = agencyUnit.getCategoryOptionGroup();

        programList = new ArrayList<>(agencyUnit.getPrograms());

        Collections.sort( programList );

        return SUCCESS;

    }
}
